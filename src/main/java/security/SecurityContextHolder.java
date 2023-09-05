package security;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SecurityContextHolder {
	
	private static List<Authentication> authentications = new ArrayList<>();
	
	// 로그인과 동시에 user의정보가 담긴 리스트와, 토큰이 authentications이 authentications 리스트에 담긴다.
	public static void addAuth(Authentication authentication) {
		authentications.add(authentication);
	}
	
	// token을 받아와서 인증이되었는지 안되었는지 확인
	public static Boolean isAuthenticated(String token) {
		for(Authentication authentication : authentications) {
			if(Objects.equals(authentication.getToken(), token)) {
				return true;
			}
		}
		return false;
	}
	
	// 토큰의 중복을 확인하고 중복이면 토큰을 반환하고 아니면 null로 반환
	// objects.equals를 사용하는 이유는 equals는 둘중하나 null 이면 NullPointerException 이라는 오류가 발생하지만
	// objects.equals를 사용하면 둘중하나가 null이라도 오류가 발생하지않고 값비교를 할 수 있기 때문이다.
	public static Authentication findAuthenticationByToken(String token) {
		for(Authentication authentication : authentications) {
			if(Objects.equals(authentication.getToken(), token)) {
				return authentication;
			}
		}
		return null;
	}
	
	public static void removeAuth(String token) {
		for(Authentication authentication : authentications) {
			if(Objects.equals(authentication.getToken(), token)) {
				authentications.remove(authentication);
				break;
			}
		}
	}
	
	
}
