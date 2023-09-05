package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserData;
import entity.User;
import security.Authentication;
import security.SecurityContextHolder;
import utils.JsonParseUtil;
import utils.ResponseUtil;

@WebServlet("/mypage/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getHeader("Authorization");
		
		System.out.println(token);
		
		// SecurityContextHolder 에서 findAuthenticationByToken 이것을 꺼내어 User에 담는다.
		User user = SecurityContextHolder.findAuthenticationByToken(token).getUser();
		
		// 마찬가지로 응답을 Json으로 변형해서 넘겨준다.
		ResponseUtil.response(response).of(200).body(JsonParseUtil.toJson(user));
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> profileMap = JsonParseUtil.toMap(request.getInputStream()); // Json형태를 map으로 가져옴
		
		Authentication authentication = SecurityContextHolder.findAuthenticationByToken(request.getHeader("Authorization"));
		// 클라이언트에서 받아온 토큰과 일치하는 사용자의 객체를 찾아서 반환해 oldUser에 집어넣는다.
		User oldUser = authentication.getUser();
		
		System.out.println(oldUser);
		
		// 
		List<User> userList = UserData.userList;
		
		System.out.println(userList);
		
		// 변경된 user
		User user = User.builder()
				.UserId(oldUser.getUserId())	//아이디를 증가시키면안됨 유지해야함
				.username((String)profileMap.get("username"))
				.password((String) profileMap.get("password"))
				.name((String) profileMap.get("name"))
				.email((String) profileMap.get("email"))
				.build();
		
		//
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getUserId() == user.getUserId()) {
				userList.set(i, user);	//회원가입 정보 변경 
				authentication.setUser(user);	//로그인 정보 변경 
				ResponseUtil.response(response).of(200).body(true);
			}
		}
		ResponseUtil.response(response).of(200).body(false);
	}
}