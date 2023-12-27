package com.example.FoodDeliveryApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {
    /*
     * public static void main(String[] args) {
     * String p = "Test@123";
     * BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
     * String encodedPassword = new
     * String(Base64.getEncoder().encode(p.getBytes()));
     * String bcryptEncoded = bCryptPasswordEncoder.encode(p);
     * System.out.println(encodedPassword);
     * System.out.println(bcryptEncoded);
     * System.out.println(bCryptPasswordEncoder.matches(p, bcryptEncoded));
     * }
     */
    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager(UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder, HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return builder.build();
    }

    /*
    @Bean
    public SecurityFilterChain configure(AuthenticationManager authenticationManager, HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.csrf().disable().authorizeRequests().requestMatchers("/user/**", "/resturant/**", "/rider/**", "/order/**", "/admin/**").permitAll()
                // .requestMatchers(HttpMethod.POST, "/Resturant").permitAll().requestMatchers(HttpMethod.POST, "/rider")
                // .permitAll().requestMatchers(HttpMethod.GET, "/admin/**").hasAuthority("ADMIN")
                // .requestMatchers(HttpMethod.PATCH, "/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated().and()
                .authenticationManager(authenticationManager).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
    */

    @Bean
    public SecurityFilterChain configure(AuthenticationManager authenticationManager, HttpSecurity httpSecurity)
            throws Exception {
                return
        httpSecurity.csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers(HttpMethod.POST, "/login", "/**")
        .permitAll()
        .requestMatchers(HttpMethod.GET, "/**").permitAll()
        .requestMatchers(HttpMethod.PUT, "/**").permitAll()
        .anyRequest()
        .authenticated()
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authenticationManager(authenticationManager).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
    }





}
