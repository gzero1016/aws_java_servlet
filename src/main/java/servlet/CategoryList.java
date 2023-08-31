package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/category")
public class CategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String[] categoryArray = {
			"한식",
			"체험관광",
			"카페",
			"자연명소",
			"양식",
			"문화예술"
	};
	
	private class Feed {
		private String feedName;
		private String categoryName;
		
		public Feed(String feedName, String categoryName) {
			this.feedName = feedName;
			this.categoryName = categoryName;
		}
		
		public String getCategoryName() {
			return categoryName;
		}
		
		public String getFeedInfo() {
			return "feedName" + feedName + ", categoryName: " + categoryName + "\n";
		}
	}
	
	private Feed[] feedArray = {
		new Feed("1번피드", "한식"),
		new Feed("2번피드", "한식"),
		new Feed("3번피드", "한식"),
		new Feed("4번피드", "체험관광"),
		new Feed("5번피드", "체험관광"),
		new Feed("6번피드", "카페"),
		new Feed("7번피드", "자연명소"),
		new Feed("8번피드", "카페"),
		new Feed("9번피드", "자연명소"),
		new Feed("10번피드", "체험관광"),
		new Feed("11번피드", "문화예술"),
		new Feed("12번피드", "문화예술"),
		new Feed("13번피드", "카페")	
	};
	
	// doGet, goPost 는 void로 반환값이 없음 response로 응답을 해주는것
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURI());
		System.out.println(request.getMethod());
		String categoryName = request.getParameter("categoryName");	//Parameter를 거치면 무조건 String으로 넘어옴
		
		if(!checkCategory(categoryName)) {
			response.setCharacterEncoding("UTF-8");	//UTF-8로 바꿔주지않으면 한글 다깨지니 잘잡아주는것도 중요하다.
			response.setStatus(400);
			response.getWriter().println("해당 카테고리는 존재하지 않는 카테고리입니다.");
			return;
		}
		
//		//AtomicReference: 전역으로 주소공유(주소가 바뀌는것은 아님, 람다형식으로 for문을 돌릴때 참고해서 사용하면 좋다.)
//		AtomicReference<String> responsData = new AtomicReference<String>("");
//		
//		findFeedByCategory(categoryName).forEach(feed -> {
//			responsData.set(responsData.get() + feed.getFeedInfo());
//		});
//		
//		response.setCharacterEncoding("UTF-8");
//		
//		//getWriter: response에 있는 데이터 가져와서 클라이언트에게 전송(응답하려면 getWriter이 무조건 있어야함)
//		response.getWriter().println(responsData.getPlain());
		
		Gson gson = new Gson();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); //타입까지 같이 알려줘야함(json타입을 알려주는것)
		//리스트를 통으로 gson(키,값)으로 변경해줌
		response.getWriter().println(gson.toJson(findFeedByCategory(categoryName)).toString());
	}
	
	private boolean checkCategory(String categoryName) {
		for(int i = 0; i < categoryArray.length; i++) {
			if(categoryArray[i].equals(categoryName)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	private List<Feed> findFeedByCategory(String categoryName) {
		List<Feed> feeds = new ArrayList();
		for(int i = 0; i < feedArray.length; i++) {
			if(feedArray[i].getCategoryName().equals(categoryName)) {
				feeds.add(feedArray[i]);
			}
		}
		
		return feeds;
	}
}














