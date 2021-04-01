package com.deni.gunawan.Sisteminformasiperpustakaan.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    void injectDependency(BCryptPasswordEncoder bCryptPasswordEncoder, DataSource dataSource) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //.antMatchers("/dashboard/**").hasAuthority("ADMIN").anyRequest()
        //.authenticated().and().csrf().disable()
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/resources/**", "/css/**", "/styles/**", "/js/**", "/img/**","/scss/**", "/vendor/**").permitAll()
                .antMatchers("/dashboard").authenticated()
                .antMatchers("/anggota/tambahData","/anggota/findById","/anggota/update","/anggota/delete").hasAuthority("ADMIN")
                .antMatchers("/buku/tambahData","/buku/findById","/buku/update","/buku/delete").hasAuthority("ADMIN")
                .antMatchers("/peminjaman/tambahData","/peminjaman/findById","/peminjaman/update","/peminjaman/delete").hasAuthority("ADMIN")
                .antMatchers("/pengambilan/tambahData","/pengambilan/findById","/pengambilan/update","/pengambilan/delete").hasAuthority("ADMIN")
                .antMatchers("/anggota/report/excel/LaporanAnggota","anggota/report/pdf/LaporanAnggota").hasAnyAuthority("ADMIN")
                .antMatchers("/buku/report/excel/LaporanBuku","buku/report/pdf/LaporanBuku").hasAnyAuthority("ADMIN")
                .antMatchers("/peminjaman/report/excel/LaporanPeminjaman","peminjaman/report/pdf/LaporanPeminjaman").hasAnyAuthority("ADMIN")
                .antMatchers("/pengambilan/report/excel/LaporanPengambilan","pengambilan/report/pdf/LaporanPengambilan").hasAnyAuthority("ADMIN")
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60 * 60)
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String USERS_QUERY = "select email, password, active from user where email=?";
        String ROLES_QUERY = "select u.email, r.role from user u inner join user_role ur on (u.id = ur.user_id) inner join role r on (ur.role_id=r.role_id) where u.email=?";
        auth.jdbcAuthentication()
                .usersByUsernameQuery(USERS_QUERY)
                .authoritiesByUsernameQuery(ROLES_QUERY)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

}
