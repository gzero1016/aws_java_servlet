package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import security.SecurityContextHolder;
import utils.ResponseUtil;

@WebFilter("*")
public class SecurityFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String rootPath = "/servlet_study_jiyoung";
		String[] antMatchers = {"/auth"};	//인증이 필요없는 path들
		
		String uri = req.getRequestURI();
		
		// 인증이 필요 없는 경우
		for(String antMatcher : antMatchers) {
			if(uri.startsWith(rootPath + antMatcher)) {	// "/servlet_study_jiyoung/auth" 는 인증을 하지않고 넘어간다.
				chain.doFilter(request, response);
				return;
			}
		}
		
		String token = req.getHeader("Authorization");
		
		// equalsIgnoreCase: 대소문자 상관없이 비교하는거
		// options 을 if문에 추가하는 이유는 클라이언트가 서버에게 options , doget두개의 요청을 보냄
		// options가 if문 안에서 return을 계속 받고있으니 doget이 들어올 자리가 없어진다.
		// options에 not을 걸어 빼주고 doget을 if문 안에 넣어서 걸러주는 원리
		// 클라이언트가 doget요청만 보낼경우 앞 options가 필요없음 (options, doget 두개보내서문제!)
		if(!req.getMethod().equalsIgnoreCase("options") && !SecurityContextHolder.isAuthenticated(token)) {
			ResponseUtil.response(resp).of(401).body("인증 실패");
			return;
		}		
		chain.doFilter(request, response);
	}
}