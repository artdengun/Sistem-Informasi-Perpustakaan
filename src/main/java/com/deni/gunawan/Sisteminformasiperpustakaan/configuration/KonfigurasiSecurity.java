package com.deni.gunawan.Sisteminformasiperpustakaan.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class KonfigurasiSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }

    @Bean
    public PasswordEncoder bcryptEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(bcryptEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/signin").permitAll()
                .antMatchers("/confirm/**", "/verify_fail/**", "/verify_success/**", "/register_success").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/anggota", "/peminjaman", "/buku","/pengembalian").permitAll()
                .antMatchers("/anggota/tambahData", "/anggota/findById","/anggota/update/**","/anggota/delete/**").permitAll()
                .antMatchers("/buku/tambahData", "/buku/findById","/buku/update/**","/buku/delete/**").permitAll()
                .antMatchers("/peminjaman/tambahData", "/peminjaman/findById","/peminjaman/update/**","/peminjaman/delete/**").permitAll()
                .antMatchers("/pengembalian/tambahData", "/pengembalian/findById","/pengembalian/update/**","/pengembalian/delete/**").permitAll()
                .antMatchers("/anggota/report/pdf/LaporanAnggota","/anggota/report/excel/LaporanAnggota").permitAll()
                .antMatchers("/buku/report/pdf/LaporanBuku","/buku/report/excel/LaporanBuku").permitAll()
                .antMatchers("/peminjaman/report/pdf/LaporanPeminjaman","/peminjaman/report/excel/LaporanPeminjaman").permitAll()
                .antMatchers("/pengembalian/report/pdf/LaporanPengembalian","/pengembalian/report/excel/LaporanPengembalian").permitAll()
                .and()
                .formLogin()
                .loginPage("/signin").failureUrl("/signin?error=true")
                .loginProcessingUrl("/authentication")
                .defaultSuccessUrl("/dashboard")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .rememberMe()
                .tokenValiditySeconds(60 * 60)
                .and()
                .exceptionHandling().accessDeniedPage("/auth/access_denied");
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
