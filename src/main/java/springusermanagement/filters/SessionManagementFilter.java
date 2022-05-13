package springusermanagement.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/userHome" })
public class SessionManagementFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws java.io.IOException, ServletException {
        System.out.println("Filter called");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("index");
            System.out.println("null session");
        } else {

            response.setHeader("Cache-Control", "no-store"); // Directs caches not to store the page under any
                                                             // circumstance
            response.setHeader("Cache-Control", "no-cache"); // Forces caches to obtain a new copy of the page from the
                                                             // origin server
            response.setDateHeader("Expires", 0); // Causes the proxy cache to see the page as "stale"
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0 backward compatibility
            System.out.println("removed cache");

            chain.doFilter(request, response);
        }
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
}
