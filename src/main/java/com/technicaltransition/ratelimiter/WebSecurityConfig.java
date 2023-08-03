package com.technicaltransition.ratelimiter;

import com.technicaltransition.ratelimiter.filters.RateLimitterFilter;
import com.technicaltransition.ratelimiter.utils.SiteCounter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public SiteCounter siteCounter() {
        return new SiteCounter();
    }

	/*@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}*/

    @Bean
    public FilterRegistrationBean<RateLimitterFilter> loggingFilter() {
        FilterRegistrationBean<RateLimitterFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RateLimitterFilter());
        registrationBean.addUrlPatterns("/customers/*");
        registrationBean.setOrder(3);

        return registrationBean;
    }
}
