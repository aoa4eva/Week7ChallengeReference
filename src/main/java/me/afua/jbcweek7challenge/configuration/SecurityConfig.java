package me.afua.jbcweek7challenge.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    BCryptPasswordEncoder encoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/css/**").permitAll()
                .antMatchers("/json/**","/h2/**").hasAuthority("ADMIN")
                .antMatchers("/**").hasAnyAuthority("USER","ADMIN")
                .and().formLogin()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

        //To see H2 database with Spring Security
        http.headers().frameOptions().disable().and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password(encoder().encode("password")).authorities("USER")
                .and().withUser("admin").password(encoder().encode("password")).authorities("ADMIN")
                .and().passwordEncoder(encoder());
    }
}
