package servlet;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ResponseUtil;


@WebServlet("/auth/signup/duplicate/username")
public class DuplicateUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String[] usernames = { "aaa", "bbb", "ccc" };
    
//	get요청일때는 Parameter로 넘어온다
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String username = request.getParameter("username");
		
		for(int i = 0; i < usernames.length; i++) {
			// username으로 비교를해버리면 값이 없을때는 null이기때문에 nullpointException이 뜨기때문에 
			// Objects.equals를 사용해서 nullpoint를 안뜨게한다.
			if(Objects.equals(usernames[i], username)) {
				ResponseUtil.response(response).of(400).body(true);
				return;
			}
		}
		
		ResponseUtil.response(response).of(200).body(false);
	}
}
