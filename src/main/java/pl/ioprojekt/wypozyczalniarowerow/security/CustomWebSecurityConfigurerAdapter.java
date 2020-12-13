package pl.ioprojekt.wypozyczalniarowerow.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public CustomWebSecurityConfigurerAdapter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "http://25.76.204.208", "http://25.76.48.113", "http://25.76.204.208:8080", "http://25.76.48.113:8080", " http://127.0.0.1/", "http://127.0.0.1:8080/"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials", "true"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "POST", "GET", "OPTIONS", "DELETE", "PUT"))
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                .and()
                .authorizeRequests()
                .antMatchers("/registration/admin").hasRole("ADMIN")
                .antMatchers("/registration/employee").hasRole("ADMIN")
                .antMatchers("/registration").permitAll()
                .antMatchers(HttpMethod.GET, "/mails").hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.DELETE, "/mails/*").hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.POST, "/mails").permitAll()
                .antMatchers(HttpMethod.POST, "/bikes").hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.PUT, "/bikes").hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/bikes").permitAll()
                .antMatchers("/bikes/*").permitAll()
                .antMatchers(HttpMethod.POST, "/reservations").authenticated()
                .antMatchers(HttpMethod.PUT, "/reservations").authenticated()
                .antMatchers("/reservations/byUser/*").authenticated()
                .antMatchers("/reservations/*").authenticated()
                .antMatchers("/reservations").permitAll()
                .antMatchers(HttpMethod.POST, "/equipment").hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.PUT, "/equipment").hasRole("EMPLOYEE")
                .antMatchers("/equipment").permitAll()
                .antMatchers("/equipment/*").permitAll()
                .antMatchers("/repairs").permitAll()
                .antMatchers("/repairs/*").permitAll()
                .antMatchers("/employees").hasRole("ADMIN")
                .antMatchers("/user").authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf()
                .disable();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();

        manager.setDataSource(dataSource);

        return manager;
    }

    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("http://25.76.204.208", "http://25.76.48.113", "http://25.76.204.208:8080", "http://25.76.48.113:8080", " http://127.0.0.1/", "http://127.0.0.1:8080/"));
        configuration.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.HEAD.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name()));
        configuration.setMaxAge(1800L);
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
