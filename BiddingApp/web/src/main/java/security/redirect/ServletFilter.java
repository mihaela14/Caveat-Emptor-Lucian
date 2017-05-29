package security.redirect;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biddingapp.ejbeans.UserLoginBean;

@WebFilter(urlPatterns = { "/*" })
public class ServletFilter implements Filter {

	@Inject
	 UserLoginBean user;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String path = request.getRequestURI().substring(request.getContextPath().length());

		boolean notAllowedPages = path.contains("/templates");
		
		if(path.contains("resources") || path.contains("resource")){
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}

		if (!user.isLoggedIn()) {
			if (notAllowedPages) {
				response.sendRedirect(request.getContextPath() + "/login.xhtml");
				return;
			}

		} else {
			if (!notAllowedPages) {			
				response.sendRedirect(request.getContextPath() + "/templates/caveatEmptor.xhtml");
				return;			
			}
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
