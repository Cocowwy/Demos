package cn.cocowwy.springgraphql.graphql.progect;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Controller
public class ProjectController {

    private final SpringProjectsClient client;

    public ProjectController(SpringProjectsClient client) {
        this.client = client;
    }

    @QueryMapping
    public Project project(@Argument String slug) {
        return client.fetchProject(slug);
    }

    @SchemaMapping
    public List<Release> releases(Project project) {
        return client.fetchProjectReleases(project.getSlug());
    }

}