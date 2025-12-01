package events.organizerservice.config.security;

import events.organizerservice.config.security.dto.SimpleAuthDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    final String jwt = authHeader.substring(7);
    final String email = jwtService.pobierzEmail(jwt);
    final String rola = jwtService.pobierzRole(jwt);
    final String userId = jwtService.pobierzUserId(jwt);
    UserDetails userDetails = User.builder()
        .username(email)
        .password("")
        .roles(rola)
        .build();
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
        userDetails,
        null,
        userDetails.getAuthorities());
    authToken.setDetails(new SimpleAuthDetails(UUID.fromString(userId)));
    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      if (jwtService.isTokenValid(jwt)) {
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    filterChain.doFilter(request, response);
  }

}
