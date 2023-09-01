package utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	
//	response 메소드를 호출하면 새로운 빌더를 생성
	public static ResponseUtilBuilder response(HttpServletResponse response) {
		return new ResponseUtilBuilder(response);
	}

	public static class ResponseUtilBuilder {
		private HttpServletResponse response;
		
		public ResponseUtilBuilder(HttpServletResponse response) {
			this.response = response;
		}
		
		public ResponseUtilBuilder of(int satausCode) {
			response.setStatus(satausCode);
			return this;
		}
		
		public void body(Object body) throws IOException {
			// 객체를 json으로 변경해서 내보낼때는 타입과 인코딩을 꼭 지정해줘야함
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(body);
		}
	}
}