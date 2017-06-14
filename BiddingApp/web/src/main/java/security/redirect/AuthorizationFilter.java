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
public class AuthorizationFilter implements Filter {

	@Inject
	UserLoginBean user;


	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}


	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		try{
			
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			
			String path = request.getRequestURI().substring(request.getContextPath().length());

			boolean allowedPages = path.contains("/login") || path.contains("/register") || path.contains("/activate") || path.contains("/need-help");

			if(path.contains("resources") || path.contains("resource")){
				filterChain.doFilter(servletRequest, servletResponse);
				return;
			}

			if (!user.isLoggedIn()) {
				if (!allowedPages) {
					response.sendRedirect(request.getContextPath() + "/login.xhtml");
					return;
				}

			} else {
				if (allowedPages) {	
					return;			
				}
			}
			filterChain.doFilter(servletRequest, servletResponse);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@Override
	public void destroy() {
	}


	public AuthorizationFilter() {
	}

}
