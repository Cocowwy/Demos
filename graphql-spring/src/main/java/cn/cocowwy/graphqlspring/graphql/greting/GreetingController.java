package cn.cocowwy.graphqlspring.graphql.greting;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Controller
public class GreetingController {
    @QueryMapping
    public String greeting() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return "Hello " + attributes.getAttribute(RequestAttributeFilter.NAME_ATTRIBUTE, SCOPE_REQUEST);
    }
}
