package cn.cocowwy.graphqlspring.graphql.greting;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Component
public class RequestAttributeFilter implements Filter {

    public static final String NAME_ATTRIBUTE = RequestAttributeFilter.class.getName() + ".name";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setAttribute(NAME_ATTRIBUTE, "007");
        chain.doFilter(request, response);
    }

}