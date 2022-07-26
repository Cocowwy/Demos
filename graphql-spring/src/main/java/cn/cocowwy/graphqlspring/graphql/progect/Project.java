package cn.cocowwy.graphqlspring.graphql.progect;

import lombok.Data;

import java.util.List;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Data
public class Project {
    private String slug;

    private String name;

    private String repositoryUrl;

    private ProjectStatus status;

    private List<Release> releases;
}
