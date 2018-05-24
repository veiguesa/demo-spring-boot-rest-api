package market.survey.core.utils;

import java.util.Collection;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SecurityUtils {
	public static final String ROLE_ADMIN = "ADMIN";
	public static boolean checkAdminRole(Authentication authentication) {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		boolean authorized = authorities.contains(new SimpleGrantedAuthority(ROLE_ADMIN));
		return authorized;
		//if (!authorized)
			//throw new AccessDeniedException(null);
	}
}
