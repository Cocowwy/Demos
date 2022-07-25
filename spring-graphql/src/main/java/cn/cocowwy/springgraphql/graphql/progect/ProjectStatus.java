package cn.cocowwy.springgraphql.graphql.progect;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public enum ProjectStatus {

    ACTIVE, COMMUNITY, INCUBATING, ATTIC;

    @JsonCreator
    public static ProjectStatus fromName(String name) {
        return Arrays.stream(ProjectStatus.values())
                .filter(type -> type.name().equals(name))
                .findFirst()
                .orElse(ProjectStatus.ACTIVE);
    }

}