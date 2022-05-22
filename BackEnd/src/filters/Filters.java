package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class Filters implements Filter {

    public Filters(){}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res= (HttpServletResponse) servletResponse;

        filterChain.doFilter(servletRequest,servletResponse);

        res.addHeader("Access-Control-Allow-Origin","*");
        res.addHeader("Access-Control-Allow-Methods","DELETE,PUT");
        res.addHeader("Access-Control-Allow-Headers","Content-Type");
    }

    @Override
    public void destroy() {

    }
}
