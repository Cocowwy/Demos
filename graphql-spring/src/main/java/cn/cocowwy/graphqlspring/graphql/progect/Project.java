package cn.cocowwy.graphqlspring.graphql.progect;

import java.util.List;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */

public class Project {
    private String slug;

    private String name;

    private String repositoryUrl;

    private ProjectStatus status;

    private List<Release> releases;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public List<Release> getReleases() {
        return releases;
    }

    public void setReleases(List<Release> releases) {
        this.releases = releases;
    }
}
