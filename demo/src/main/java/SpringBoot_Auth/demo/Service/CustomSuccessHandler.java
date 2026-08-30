package SpringBoot_Auth.demo.Service;

import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority
                        .getAuthority().equals("ADMIN"));

        if (isAdmin) {
            getRedirectStrategy().sendRedirect(request, response, "/admin/admin-home");

        } else {
            getRedirectStrategy().sendRedirect(request, response, "/user/user-home");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

}