package com.example.lab6.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import javax.sql.DataSource;

@Configuration
public class ConfigWebSec {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    final DataSource dataSource;

    public ConfigWebSec(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource){
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        String sql1 = "SELECT correo, password, enabled FROM usuario WHERE correo = ?";
        String sql2 = "SELECT u.correo, r.idrol FROM usuario u "
                + "INNER JOIN rol r ON (u.idrol = r.idrol) "
                + "WHERE u.correo = ? and u.enabled = 1";

        users.setUsersByUsernameQuery(sql1);
        users.setAuthoritiesByUsernameQuery(sql2);
        return users;
    }


    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        http.formLogin()
                .successHandler((request, response, authentication) -> {

                    DefaultSavedRequest defaultSavedRequest =
                            (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");

                    if (defaultSavedRequest != null) {
                        String targetURL = defaultSavedRequest.getRedirectUrl();
                        redirectStrategy.sendRedirect(request, response, targetURL);
                    } else {
                        String rol = "";
                        for (GrantedAuthority role : authentication.getAuthorities()) {
                            rol = role.getAuthority();
                            break;
                        }
                        if (rol.equals("ADMIN")) {
                            response.sendRedirect("/dispositivos");
                        } else {
                            response.sendRedirect("/dispositivos");
                        }
                    }
                });



        http.authorizeHttpRequests()
                .requestMatchers("/dispositivos", "/dispositivos/**").hasAnyAuthority("ADMIN","USUARIO","PROFESOR")
                .requestMatchers("/prestamos", "prestamos/**").hasAnyAuthority("USUARIO","PROFESOR");


        http.logout();

        return http.build();
    }

}
