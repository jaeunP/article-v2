package aticle.articlev2.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


//    @Autowired
//    private WebAccessDeniedHandler webAccessDeniedHandler;
//
//    @Autowired
//    private WebAuthenticationEntryPoint webAuthenticationEntryPoint;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/", "/signup", "/login", "/css/**").permitAll()
                .antMatchers("/member/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN");

        http.formLogin().loginPage("/login").defaultSuccessUrl("/", true);

        http.userDetailsService(userDetailsService);

    }
}
