package pl.sedzimierz.lovepets.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.stream.Stream;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static Optional<String> getCurrentUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return Optional.ofNullable(authentication.getName());
        }
        return Optional.empty();
    }

    public static boolean hasCurrentUserAuthority(String authority) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null &&
                getAuthorities(authentication).anyMatch(authority::equals);
    }

    private static Stream<String> getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority);
    }
}
