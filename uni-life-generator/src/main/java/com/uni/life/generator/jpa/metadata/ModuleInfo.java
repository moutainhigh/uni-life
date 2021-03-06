package com.uni.life.generator.jpa.metadata;

import lombok.Data;

import java.util.List;

/**
 * @Author lait.zhang@gmail.com
 * @Tel 15801818092
 * @Date 6/27/2020
 * @Description ${Description}
 */
@Data
public class ModuleInfo {

    private String moduleName;

    private List<EntityInfo> entityInfos;
}
