package sn.niit.gestionbudget.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/budgets/**", "/categories/**", "/utilisateurs/**", "/departements/**").hasRole("ADMIN")
                        .requestMatchers("/depenses/**").hasRole("EMPLOYE")
                        .requestMatchers("/home/**").hasAnyRole("ADMIN", "EMPLOYE")
                        .anyRequest().authenticated()
                )
                .formLogin()
                //.loginPage("/login").permitAll()
                //.failureUrl("/login?error=true")
                .successHandler(customAuthenticationSuccessHandler())
                .permitAll()
                .and()
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                )
                .exceptionHandling()
                .accessDeniedPage("/403")
                .and()
                .logout()
                .permitAll();
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

                if (roles.contains("ROLE_ADMIN")) {
                    response.sendRedirect("/home");
                } else if (roles.contains("ROLE_EMPLOYE")) {
                    response.sendRedirect("/home");
                } else {
                    response.sendRedirect("/403");
                }
            }
        };
    }





    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin1 = User.withUsername("adminH")
                .password(passwordEncoder.encode("adminh123"))
                .roles("ADMIN")
                .build();

        UserDetails admin2 = User.withUsername("adminB")
                .password(passwordEncoder.encode("adminb123"))
                .roles("ADMIN")
                .build();

        UserDetails admin3 = User.withUsername("adminL")
                .password(passwordEncoder.encode("adminl123"))
                .roles("ADMIN")
                .build();

        UserDetails admin4 = User.withUsername("adminX")
                .password(passwordEncoder.encode("mdp123"))
                .roles("ADMIN")
                .build();



        UserDetails employe = User.withUsername("employe")
                .password(passwordEncoder.encode("employe123"))
                .roles("EMPLOYE")
                .build();

        UserDetails employe2 = User.withUsername("employe2")
                .password(passwordEncoder.encode("emp123"))
                .roles("EMPLOYE")
                .build();

        UserDetails employe3 = User.withUsername("employe3")
                .password(passwordEncoder.encode("emp221"))
                .roles("EMPLOYE")
                .build();

        return new InMemoryUserDetailsManager(admin1, admin2, admin3, admin4, employe, employe2, employe3);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
