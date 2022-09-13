package cn.cocowwy.graphqlspring.graphql.progect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Hop;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.server.core.TypeReferences;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class SpringProjectsClient {

    private static final TypeReferences.CollectionModelType<Release> releaseCollection =
            new TypeReferences.CollectionModelType<Release>() {
            };

    private final Traverson traverson;

    public SpringProjectsClient(RestTemplateBuilder builder) {
        List<HttpMessageConverter<?>> converters = Traverson.getDefaultMessageConverters(MediaTypes.HAL_JSON);
        RestTemplate restTemplate = builder.messageConverters(converters).build();
//        this.traverson = new Traverson(URI.create("https://spring.io/api/"), MediaTypes.HAL_JSON);
        this.traverson = new Traverson(URI.create("localhost/api/"), MediaTypes.HAL_JSON);
        this.traverson.setRestOperations(restTemplate);
    }

    public Project fetchProject(String projectSlug) {
        return this.traverson.follow("projects")
                .follow(Hop.rel("project").withParameter("id", projectSlug))
                .toObject(Project.class);
    }

    public List<Release> fetchProjectReleases(String projectSlug) {
        CollectionModel<Release> releases = this.traverson.follow("projects")
                .follow(Hop.rel("project").withParameter("id", projectSlug)).follow(Hop.rel("releases"))
                .toObject(releaseCollection);
        return new ArrayList(releases.getContent());
    }
}

@Service
class Test implements CommandLineRunner {

    @Autowired
    private SpringProjectsClient SpringProjectsClient;

    @Override
    public void run(String... args) throws Exception {
        SpringProjectsClient.fetchProject("spring-boot");
    }
}