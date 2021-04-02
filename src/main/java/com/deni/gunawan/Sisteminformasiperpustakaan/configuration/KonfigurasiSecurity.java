package com.deni.gunawan.Sisteminformasiperpustakaan.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

import javax.sql.DataSource;

@EnableWebSecurity
public class KonfigurasiSecurity extends WebSecurityConfigurerAdapter {

    private static final String SQL_LOGIN
            = "select u.username, up.password, u.active " +
            "from s_users_passwords up " +
            "inner join s_users u on u.id = up.id_user " +
            "where u.username = ?";

    private static final String SQL_PERMISSION =
            "select u.username, p.value as authority " +
                    "from s_users u " +
                    "inner join s_roles r on r.id = u.id_role " +
                    "inner join s_roles_permissions rp on rp.id_role = r.id " +
                    "inner join s_permissions p on rp.id_permission = p.id " +
                    "where u.username = ?";




    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(SQL_LOGIN)
                .authoritiesByUsernameQuery(SQL_PERMISSION)
                .passwordEncoder(bcryptEncoder());
    }

    @Bean
    public SwitchUserFilter switchUserFilter() throws Exception {
        SwitchUserFilter filter = new SwitchUserFilter();
        filter.setUserDetailsService(userDetailsService());
        filter.setSwitchUserUrl("/switchuser/form");
        filter.setExitUserUrl("/switchuser/exit");
        filter.setTargetUrl("/switch/anggota/list");
        return filter;
    }
    @Bean
    public PasswordEncoder bcryptEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/switchuser/exit")
                .hasAuthority(SwitchUserFilter.ROLE_PREVIOUS_ADMINISTRATOR)
                .mvcMatchers("/switchuser/select", "/switchuser/form")
                .hasAuthority("ADMIN")
                .antMatchers("/anggota").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout().permitAll()
                .and().formLogin()

                .defaultSuccessUrl("/dashboard", true);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/css/**")
                .antMatchers("/img/**")
                .antMatchers("/js/**")
                .antMatchers("/scss/**")
               .antMatchers("/vendor/**");
    }
}
