package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

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

@WebServlet("/auth/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> signinUser = JsonParseUtil.toMap(request.getInputStream());
		Map<String, String> responseData = new HashMap<>();
		
		for(User user : UserData.userList) {
			if(Objects.equals(user.getUsername(), signinUser.get("username"))
					&& Objects.equals(user.getPassword(), signinUser.get("password"))) {
				
				String token = UUID.randomUUID().toString();	//로그인 될때마다 새로운 토큰 만들기
				SecurityContextHolder.addAuth(new Authentication(user, token));	//SecurityContextHolder addAuth에 user, token을 넣어준다.
				responseData.put("token", token); // responseData에 토큰을 집어넣어둔다. 현재 responseData에는 토큰의 값이 저장되어있음
				break;
			}
		}
		
		// token을 json으로 변경해서 응답을줌
		ResponseUtil.response(response).of(200).body(JsonParseUtil.toJson(responseData));	
	}
}