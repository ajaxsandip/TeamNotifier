package com.team.team.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.team.team.repository.TeamRepository;

// This generally overrides the default auth Security configuration and now the default pass is no longer seen while startup

@Configuration
public class ApplicationConfiguration {
    private final TeamRepository teamRepository;

    public ApplicationConfiguration(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    };

    @Bean
    UserDetailsService userDetailsService(){
        return userName -> teamRepository.findByEmailID(userName)
                                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
