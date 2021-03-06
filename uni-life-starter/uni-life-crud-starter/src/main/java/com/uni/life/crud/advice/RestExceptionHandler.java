package com.uni.life.crud.advice;

import com.uni.life.crud.dto.ResponseDto;
import com.uni.life.crud.ex.BaseException;
import com.uni.life.crud.ex.BusinessException;
import com.uni.life.crud.ex.constant.ArgumentResponseEnum;
import com.uni.life.crud.ex.constant.CommonResponseEnum;
import com.uni.life.crud.ex.constant.ServletResponseEnum;
import com.uni.life.crud.ex.i18n.UnifiedMessageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/***
 * 统一封装异常、统一处理出参
 *
 */
@RestControllerAdvice
public class RestExceptionHandler implements ResponseBodyAdvice<Object> {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * 生产环境
     */
    private final static String ENV_PROD = "prod";

    @Value("${spring.application.name:}")
    private String serviceName;

    /**
     * 当前环境
     */
    @Value("${spring.profiles.active:}")
    private String profile;

    @Autowired
    private UnifiedMessageSource unifiedMessageSource;

    /**
     * 业务异常
     *
     * @param request  请求参数
     * @param response 响应参数
     * @param e        异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseDto<?> handleBusinessException(HttpServletRequest request, HttpServletResponse response,
                                                  BaseException e) {
        log.error("调用={}服务出现业务调用发生异常，请求的url是={}，请求的方法是={}，原因={}", serviceName, request.getRequestURL(),
                request.getMethod(), e.getMessage(), e);
        return ResponseDto.error(e.getResponseEnum().getCode(), getMessage(e));
    }

    /**
     * 自定义异常
     *
     * @param request  请求参数
     * @param response 响应参数
     * @param e        异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BaseException.class)
    public ResponseDto<?> handleBaseException(HttpServletRequest request, HttpServletResponse response,
                                              BaseException e) {
        log.error("调用={}服务出现自定义异常，请求的url是={}，请求的方法是={}，原因={}", serviceName, request.getRequestURL(),
                request.getMethod(), e.getMessage(), e);
        return ResponseDto.error(e.getResponseEnum().getCode(), getMessage(e));
    }

    /**
     * Controller上一层相关异常
     *
     * @param request  请求参数
     * @param response 响应参数
     * @param e        异常
     * @return 异常结果
     */
    @ExceptionHandler({ NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class, HttpMediaTypeNotAcceptableException.class,
            MissingPathVariableException.class, MissingServletRequestParameterException.class,
            TypeMismatchException.class, HttpMessageNotReadableException.class, HttpMessageNotWritableException.class,
            ServletRequestBindingException.class, ConversionNotSupportedException.class,
            MissingServletRequestPartException.class, AsyncRequestTimeoutException.class })
    public ResponseDto<?> handleServletException(HttpServletRequest request, HttpServletResponse response,
                                                 Exception e) {
        log.error("调用={}服务出现controller的上层出现异常，请求的url是={}，请求的方法是={}，原因={}", serviceName, request.getRequestURL(),
                request.getMethod(), e);
        int code = CommonResponseEnum.SERVER_ERROR.getCode();
        try {
            ServletResponseEnum servletExceptionEnum = ServletResponseEnum.valueOf(e.getClass().getSimpleName());
            code = servletExceptionEnum.getCode();
        } catch (IllegalArgumentException e1) {
            log.error("class [{}] not defined in enum {}", e.getClass().getName(), ServletResponseEnum.class.getName());
        }

        if (ENV_PROD.equals(profile)) {
            // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如404.
            code = CommonResponseEnum.SERVER_ERROR.getCode();
            BaseException baseException = new BaseException(CommonResponseEnum.SERVER_ERROR);
            String message = getMessage(baseException);
            return ResponseDto.error(code, message);
        }

        return ResponseDto.error(code, e.getMessage());
    }

    /**
     * 参数绑定异常
     *
     * @param request  请求参数
     * @param response 响应参数
     * @param e        异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BindException.class)
    public ResponseDto<?> handleBindException(HttpServletRequest request, HttpServletResponse response,
                                              BindException e) {
        log.error("调用={}服务出现参数绑定校验异常，请求的url是={}，请求的方法是={}，原因={}", serviceName, request.getRequestURL(),
                request.getMethod(), e);
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 参数校验(Valid)异常，将校验失败的所有异常组合成一条错误信息
     *
     * @param request  请求参数
     * @param response 响应参数
     * @param e        异常
     * @return 异常结果
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseDto<?> handleValidException(HttpServletRequest request, HttpServletResponse response,
                                               MethodArgumentNotValidException e) {
        log.error("调用={}服务出现方法参数校验异常，请求的url是={}，请求的方法是={}，原因={}", serviceName, request.getRequestURL(),
                request.getMethod(), e);
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 其他未定义的异常
     *
     * @param request  请求参数
     * @param response 响应参数
     * @param e        异常
     * @return 异常结果
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseDto<?> resolveException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error("调用={}服务出现异常了，请求的url是={}，请求的方法是={}，原因={}", serviceName, request.getRequestURL(), request.getMethod(),
                e);
        if (ENV_PROD.equals(profile)) {
            // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如数据库异常信息.
            int code = CommonResponseEnum.SERVER_ERROR.getCode();
            BaseException baseException = new BaseException(CommonResponseEnum.SERVER_ERROR);
            String message = getMessage(baseException);
            return ResponseDto.error(code, message);
        }

        return ResponseDto.error(CommonResponseEnum.SERVER_ERROR.getCode(), e.getMessage());
    }

    /***
     * 统一封装返回值，如果返回值是void,自动构造请求成功的返回值<br>
     * 如果返回值本身是ResponseDto，不做处理<br>
     * 其他的统一做添加请求成功的返回码。
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> arg3, ServerHttpRequest arg4, ServerHttpResponse arg5) {
        // 1.获取返回参数的全类名，如：com.rong.entity.User
        final String returnTypeName = returnType.getParameterType().getName();

        if (Objects.equals("void", returnTypeName)) {
            return ResponseDto.success(null);
        }
        if (body instanceof ResponseDto || Objects.equals(ResponseDto.class.getName(), returnTypeName)) {
            return body;
        }

        return ResponseDto.success(body);
    }

    /**
     * 是否支持做的统一返回值处理，<br>
     * 如果返回false，将不会进入beforeBodyWrite方法
     */
    @Override
    public boolean supports(MethodParameter arg0, Class<? extends HttpMessageConverter<?>> arg1) {
        return true;
    }

    /**
     * 获取国际化消息
     *
     * @param e 异常
     * @return
     */
    public String getMessage(BaseException e) {
        String code = "response." + e.getResponseEnum().toString();
        String message = unifiedMessageSource.getMessage(code, e.getArgs());

        if (StringUtils.isEmpty(message)) {
            return e.getMessage();
        }

        return message;
    }

    /**
     * 包装绑定异常结果
     *
     * @param bindingResult 绑定结果
     * @return 异常结果
     */
    private ResponseDto<?> wrapperBindingResult(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();

        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(", ");
            if (error instanceof FieldError) {
                msg.append(((FieldError) error).getField()).append(": ");
            }
            msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());

        }

        return ResponseDto.error(ArgumentResponseEnum.VALID_ERROR.getCode(), msg.substring(2));
    }
}
