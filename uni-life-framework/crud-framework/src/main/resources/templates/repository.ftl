package ${packageName};

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.uni.framework.crud.base.BaseRepository;

import ${entity.packageName}.${entity.className};

/**
 * repository for ${entity.className} generated by jpa-codegen
 * ${comments}
 *
 * @author ${author}
 * Created On ${date}.
 */
@RepositoryRestResource(path = "${entity.className?uncap_first}", itemResourceRel = "resource", collectionResourceRel = "resources")
public interface ${className} extends BaseRepository<${entity.className}, ${entity.id.className}> {


}