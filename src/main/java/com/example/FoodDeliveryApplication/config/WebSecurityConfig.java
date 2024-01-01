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
         * @Bean
         * public SecurityFilterChain configure(AuthenticationManager
         * authenticationManager, HttpSecurity httpSecurity)
         * throws Exception {
         * httpSecurity.csrf().disable().authorizeRequests().requestMatchers("/user/**",
         * "/resturant/**", "/rider/**", "/order/**", "/admin/**").permitAll()
         * // .requestMatchers(HttpMethod.POST,
         * "/Resturant").permitAll().requestMatchers(HttpMethod.POST, "/rider")
         * // .permitAll().requestMatchers(HttpMethod.GET,
         * "/admin/**").hasAuthority("ADMIN")
         * // .requestMatchers(HttpMethod.PATCH, "/admin/**").hasAuthority("ADMIN")
         * .anyRequest().authenticated().and()
         * .authenticationManager(authenticationManager).httpBasic(Customizer.
         * withDefaults());
         * return httpSecurity.build();
         * }
         */

        @Bean
        public SecurityFilterChain configure(AuthenticationManager authenticationManager, HttpSecurity httpSecurity)
                        throws Exception {
                return httpSecurity.csrf().disable()
                                .authorizeHttpRequests()
                                .requestMatchers(HttpMethod.POST, "/login", "/user", "/rider", "resturant").permitAll()
                                .requestMatchers(HttpMethod.GET, "/admin/user",
                                                "/address",
                                                "/address/getAddressesByCity/*",
                                                "/address/getAddressesByState/*",
                                                "/address/getAdressesByPincode/*",
                                                "/allUserPayments",
                                                "/rider",
                                                "/riderPaymemt",
                                                "/riderPayment/transactionId/*",
                                                "/riderRating",
                                                "/admin/allResturants",
                                                "/admin/getResturantByPincode/*",
                                                "/resturant/*",
                                                "/admin/menu",
                                                "/resturantPayment",
                                                "/resturantRating",
                                                "/admin/order",
                                                "/admins")
                                .hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/resturantPayment/*",
                                                "/resturantPayment/resturantId/*",
                                                "/resturantPayment/amount/*",
                                                "/resturant/*/order",
                                                "/order/*/*",
                                                "/order/*/checkPendingOrders")
                                .hasAnyAuthority("ADMIN", "RESTURANT")
                                .requestMatchers(HttpMethod.GET, "/rider/*",
                                                "/rider/*/changeProfilePicture",
                                                "/rider/*/getProfilePicture",
                                                "/rider/updateLocation",
                                                "/rider/*/makeRiderAvailable",
                                                "/rider/submitDl/*",
                                                "/rider/*/getDl",
                                                "/riderPayment/riderId/*",
                                                "/riderRating/*",
                                                "/riderRating/rider/*",
                                                "/order/*/getOrderForRiderToPickUp",
                                                "/order/*/getOrderForRider/*")
                                .hasAnyAuthority("ADMIN", "RIDER")
                                .requestMatchers(HttpMethod.GET, "/resturantPayment/*",
                                                "/resturantPayment/resturantId/*",
                                                "/resturantPayment/amount/{amount}",
                                                "/resturant/*/order",
                                                "/order/*/*",
                                                "/order/*/checkPendingOrders")
                                .hasAnyAuthority("ADMIN", "RESTURANT")
                                .requestMatchers(HttpMethod.GET, "/resturantPayment/*",
                                                "/resturantPayment/resturantId/*",
                                                "/resturantPayment/amount/*",
                                                "/resturant/*/order",
                                                "/order/*/*",
                                                "/order/*/checkPendingOrders")
                                .hasAnyAuthority("ADMIN", "RESTURANT", "RIDER")
                                .requestMatchers(HttpMethod.POST, "/riderPayment",
                                                "/menu/createFromCSV")
                                .hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/resturant/menu")
                                .hasAnyAuthority("ADMIN", "RESTURANT")
                                .requestMatchers(HttpMethod.PATCH, "/user/*/setUserAsAdmin",
                                "/user/*/removeUserAsAdmin",
                                "/admin/user/block/*",
                                                "/admin/user/unblock/*",
                                                "/rider/block/*",
                                                "/rider/unblock/*",
                                                "/admin/resturant/block/*",
                                                "/admin/resturant/unblock/*")
                                .hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/resturant/menu")
                                .hasAnyAuthority("ADMIN", "RESTURANT")
                                .requestMatchers(HttpMethod.PATCH, "/rider/*/changeProfilePicture",
                                                "/rider/updateLocation",
                                                "/rider/*/makeRiderAvailable",
                                                "/rider/submitDl/*")
                                .hasAnyAuthority("ADMIN", "RIDER")
                                .requestMatchers(HttpMethod.DELETE, "/address/*").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers(HttpMethod.POST, "/user/*",
                                "/address",
                                "/order").hasAnyAuthority("USER")
                                .requestMatchers(HttpMethod.PUT, "/user",
                                "/address").hasAnyAuthority("USER")
                                .requestMatchers(HttpMethod.PATCH, "/user/*/changeProfilePicture",
                                "/userPayment",
                                "/riderRating/complete",
                                "/completeResturantRating").hasAnyAuthority("USER")
                                .requestMatchers(HttpMethod.POST, "/rider",
                                "/resturant",
                                "/menu").hasAnyAuthority("RESTURANT")
                                .requestMatchers(HttpMethod.PUT, "/resturant").hasAnyAuthority("RESTURANT")
                                .requestMatchers(HttpMethod.DELETE, "/resturant/menu/delete/*").hasAnyAuthority("RESTURANT")

                                // .requestMatchers(HttpMethod.GET, "/**").permitAll()
                                // .requestMatchers(HttpMethod.PUT, "/**").permitAll()
                                // .requestMatchers(HttpMethod.PATCH, "/**").permitAll()
                                // .requestMatchers(HttpMethod.DELETE, "/**").permitAll()
                                .anyRequest()
                                .authenticated()
                                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                                .authenticationManager(authenticationManager)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
        }

}
