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
import javax.servlet.http.HttpServletResponse;


@WebFilter("*")	//옆 경로 요청만 들어오니 전체로 잡아줘야 모든요청이 필터를 거친다.
public class CorsFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 필터로 들어온 친구들은 업캐스팅 되어서 들어오기때문에 다운캐스팅을 해줘야 setHeader를 사용할 수 있다.
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");	//Origin: 요청을 보낸쪽 (http://localhost:3000 여기만 요청을 받는다.)
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "authorization, x-requested-with, origin, content-type, accept");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
		httpServletResponse.setHeader("Access-Control-Max-Age", "3600");	//3600: 1시간을 의미

		// 톰캣 -> 전 처리필터 -> 서블릿 -> 후 처리필터 -> 응답
		// 서블릿 호출
		chain.doFilter(request, response);
	}
}