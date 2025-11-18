package events.authservice.config.security;

import events.authservice.domain.exception.UzytkownikExceptionUtil;
import events.authservice.domain.model.Email;
import events.authservice.domain.model.Uzytkownik;
import events.authservice.domain.ports.driven.UzytkownikRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration {

  private final JwtAuthFilter jwtAuthFilter;
  private final UzytkownikRepositoryPort uzytkownikRepositoryPort;

  public WebSecurityConfiguration(@Lazy JwtAuthFilter jwtAuthFilter, UzytkownikRepositoryPort uzytkownikRepositoryPort) {
    this.jwtAuthFilter = jwtAuthFilter;
    this.uzytkownikRepositoryPort = uzytkownikRepositoryPort;
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity filterChain) throws Exception {
    return filterChain
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(sesion -> sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**", "/error").permitAll()
            .anyRequest().authenticated())
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return email -> {
      Uzytkownik uzytkownik = uzytkownikRepositoryPort.findByEmail(new Email(email))
          .orElseThrow(UzytkownikExceptionUtil.nieZnalezionoUzytkownikaException(UzytkownikExceptionUtil.EMAIL, email));
      return new SzczegolyUzytkownika(uzytkownik);
    };
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

}
