package tr.com.metasoft.meta_pdks.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tr.com.metasoft.meta_pdks.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Autowired
//    private MyUserDetailsService myUserDetailsService;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new MyUserDetailsService();
//    }

//    @Autowired
//    private CustomAuthenticationProvider authProvider;

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder encodePassword(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(encodePassword());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

        auth.authenticationProvider(authenticationProvider());

//        auth.inMemoryAuthentication().withUser("username").password("password").roles("ADMIN");
//        auth.userDetailsService(myUserDetailsService).passwordEncoder(encodePassword());

//        auth.jdbcAuthentication().passwordEncoder(encodePassword())
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, enabled from user where username=?")
//                .authoritiesByUsernameQuery("select username, role from user where username=?");
//
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

                .authorizeRequests()
                .antMatchers("/status/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated();


//                .cors()
//                .and()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/status/**").hasAnyRole("ADMIN").anyRequest().authenticated();



//                .anyRequest()
//                .authenticated().and().csrf().disable().formLogin();

//        http.
//                authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/registration").permitAll()
//                .antMatchers("/status/**").hasAuthority("ADMIN").anyRequest()
//                .authenticated().and().csrf().disable().formLogin();
//                .loginPage("/login").failureUrl("/login?error=true")
//                .defaultSuccessUrl("/admin/home")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .and().logout();
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login").and().exceptionHandling();
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
//    }


}
