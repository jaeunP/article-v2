package aticle.articlev2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/", "/signup", "/login", "/css/**", "/api/**").permitAll()
                .antMatchers("/member/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN");

        http.formLogin()
                .loginPage("/loginForm.html")
                .defaultSuccessUrl("/")
                .usernameParameter("username")
                .passwordParameter("password");


//        http.formLogin().loginPage("/login").defaultSuccessUrl("/", true);

        http.userDetailsService(userDetailsService);

    }
}
