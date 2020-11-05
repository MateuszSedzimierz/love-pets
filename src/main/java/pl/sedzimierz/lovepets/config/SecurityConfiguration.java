package pl.sedzimierz.lovepets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sedzimierz.lovepets.security.AuthoritiesConstants;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // For testing
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);

        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password(getPasswordEncoder().encode("admin"))
                .authorities(AuthoritiesConstants.ADMIN);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .exceptionHandling()
                .accessDeniedPage("/access-denied")
        .and()
            .authorizeRequests()
            .antMatchers("/", "/about", "/support").permitAll()
            .antMatchers("/register", "/users/new").permitAll()
            .antMatchers("/img/**", "/css/**", "/js/**", "/font/**").permitAll()
            .anyRequest().authenticated()
        .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
        .and()
            .logout()
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .permitAll();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
