package br.com.gft.gftmilhas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                    .antMatchers(HttpMethod.GET, "/css/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/cadastrar", "/recuperar/**", "/novasenha/*").permitAll()
                    .antMatchers(HttpMethod.POST, "/recuperar/**", "/novasenha/*").permitAll()
                    .antMatchers("/ranking", "/").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/grupo/**", "/atividade/**", "/evento/**", "/atividadeParticipante/**", "/presenca/**","/participante/**", "/cadastrarAdm")
                        .hasRole("ADMIN")
                    .anyRequest().permitAll())
            
            .formLogin(form -> form
            .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll())

            .logout(logout -> logout.logoutUrl("/logout"));
            
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
