package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import utils.JsonParseUtil;
import utils.ResponseUtil;

/*
 * 회원가입 -> 사용자 정보 데이터의 추가를 의미
 * 추가 -> Create, 데이터 베이스에 정보를 insert -> POST 요청
 * 
 * POST 메소드의 특징
 * 1. 요청시 서버로 전달되어지는 데이터가 주소창에 표시되지 않는다.
 * 	  -> GET: http://localhost:8080/category?categoryName=한식 -> GET은 Params
 * 	  -> POST: http://localhost:8080/category (BODY에 데이터를 담아서 서버로 전송) -> POST는 BODY(raw JSON)
 * 2. 전송 데이터의 크기 제한이 없다.
 */

@WebServlet("/auth/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		// 클라이언트에게 받아오는것
//		InputStream inputStream = request.getInputStream();
//		BufferedReader bufferedReader = 
//				new BufferedReader(new InputStreamReader(inputStream));
//		
//		StringBuilder requestBody = new StringBuilder("");
//		
//		// while로 반복을돌려서 한줄씩 가져온다. 가져올값이 null 이면 멈춤.
//		while(true) {
//			// readLine 한줄씩 가져옴 (readLine은 원래 try/catch 를 사용해야하지만 doPOST가 예외를 미루고있어서 그냥 사용한거)
//			String data = bufferedReader.readLine();
//			if(data == null) {
//				break;
//			}
//			requestBody.append(data);
//		}
//		
//		// 한줄씩 가져온 문자를 toString으로 합친다.
//		System.out.println(requestBody.toString());
//		
//		Gson gson = new Gson();
//		// Json 을 객체로 만들어주는거
//		// gson.fromJson: (앞에는 바꿀대상, 바꿔줄타입)
//		Map<String, String> userMap = gson.fromJson(requestBody.toString(), Map.class);
		
		
		
		
		Map<String, Object> userMap = JsonParseUtil.toMap(request.getInputStream());	//utils에 만든 메소드를 호출해서 userMap에 넣는다
		
		// Map으로 만들어졌는지 확인
		System.out.println(userMap);
		
//		// Map 객체로 만들어놨기 때문에 하나씩 get을 할 수 있음
//		System.out.println(userMap.get("username"));
//		System.out.println(userMap.get("passowrd"));
//		System.out.println(userMap.get("name"));
//		System.out.println(userMap.get("email"));
		
		System.out.println("회원가입");
		
		
		// 응답
		ResponseUtil.response(response).of(200).body("회원가입 성공");
	}
}