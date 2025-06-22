package com.loginApp.spring_security_6.config;

import com.loginApp.spring_security_6.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf-> csrf.disable()) //Used to disable csrf to mod requests
                .authorizeHttpRequests(
                request->request
                                            .requestMatchers("register","login").permitAll()  //Added to permit at register
                                            .anyRequest().authenticated() //USed to Auth requests
                )
              //.formLogin(Customizer.withDefaults()) //uses form based login
                .httpBasic(Customizer.withDefaults()); //uses basic login
        return httpSecurity.build();
    }


    public UserDetailsService userDetailsService(){
        UserDetails rohan = User.withUsername("rohan")
                            .password("{noop}password") //Not to be used in prod - this tells that no pwd encoder present
                            .roles("USER")
                            .build();
        UserDetails mogu = User.withUsername("monglu")
                            .password("{noop}pwdmogu") //Not to be used in prod - this tells that no pwd encoder present
                            .roles("USER")
                            .build();
        return new InMemoryUserDetailsManager(rohan,mogu);
    };

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(14);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        //provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception{
        return configuration.getAuthenticationManager();
    }
}
