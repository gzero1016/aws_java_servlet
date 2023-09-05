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
	
	// 로그인과 동시에 user의정보가 담긴 리스트와, 토큰이 authentications에 담긴다.
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
