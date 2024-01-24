package com.Binusa.BawasluServer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            // API controller
            "/login", "/register",
            // Berita
            "/bawaslu/api/berita/all",
            "/bawaslu/api/berita/terbaru",
            "/bawaslu/api/berita/search",
            "/bawaslu/api/berita/arsip",
            "/bawaslu/api/berita/get/{id}",
            "/bawaslu/api/berita/by-tags",
            "/bawaslu/api/berita/by-category",
            "/bawaslu/api/berita/terbaru-by-category",
            "/bawaslu/api/berita/related-berita/by-id-berita",
            // Category Berita
            "/bawaslu/api/category-berita/all",
            "/bawaslu/api/category-berita/all-limit-7",
            "/bawaslu/api/category-berita/get/{id}",
            // Tags Berita
            "/bawaslu/api/tags/all",
            // Pengumuman
            "/bawaslu/api/pengumuman",
            "/bawaslu/api/pengumuman/get/{id}",
            "/bawaslu/api/pengumuman/related-pengumuman/by-id-pengumuman",
            "/bawaslu/api/pengumuman/search",
            // Jenis Regulasi
            "/bawaslu/api/jenis-regulasi/all",
            "/bawaslu/api/jenis-regulasi/get-by-id/{id}",
            // Menu Regulasi
            "/bawaslu/api/menu-regulasi/all",
            "/bawaslu/api/menu-regulasi/get/{id}",
            "/bawaslu/api/menu-regulasi/get-by-jenis-regulasi",
            // Regulasi
            "/bawaslu/api/regulasi/all",
            "/bawaslu/api/regulasi/get/{id}",
            "/bawaslu/api/regulasi/get-by-menu-regulasi",
            // Permohonan Informasi
            "/bawaslu/api/permohonan-informasi",
            "/bawaslu/api/permohonan-informasi/get/{id}",
            "/bawaslu/api/permohonan-informasi/add",
            // Permohonan Keberatan
            "/bawaslu/api/permohonan-keberatan",
            "/bawaslu/api/permohonan-keberatan/add",
            "/bawaslu/api/permohonan-keberatan/get/{id}",
            // Jenis Informasi
            "/bawaslu/api/jenis-informasi/all",
            "/bawaslu/api/jenis-informasi/getBy/{id}",
            "/bawaslu/api/jenis-informasi/getByIdWithKeterangan",
            // Jenis Keterangan Informasi
            "/bawaslu/api/jenis-keterangan/all",
            "/bawaslu/api/jenis-keterangan/getBy/{id}",
            "/bawaslu/api/jenis-keterangan/{jenisKeterangan}/isi-informasi",
            "/bawaslu/api/jenis-keterangan/all",
            // Isi Informasi
            "/bawaslu/api/isi-keterangan-informasi/all",
            "/bawaslu/api/isi-keterangan-informasi/getBy/{id}",
            // Tabel Regulasi
            "/bawaslu/api/tabel-regulasi/all",
            "/bawaslu/api/tabel-regulasi/all-terbaru",
            "/bawaslu/api/tabel-regulasi/all-by-daftar-regulasi",
            // Tabel DIP
            "/bawaslu/api/tabel-dip/all",
            "/bawaslu/api/tabel-dip/all-terbaru",
            "/bawaslu/api/tabel-dip/all-by-daftar-dip",
            // Tabel SOP
            "/bawaslu/api/tabel-sop/all",
            "/bawaslu/api/tabel-sop/all-terbaru",
            "/bawaslu/api/tabel-sop/all-by-daftar-sop",
            // Tabel Carousel
            "/bawaslu/api/carousel/ById/{id}",
            "/bawaslu/api/carousel/all",
            "/bawaslu/api/carousel/terbaru",
            // Library
            "/bawaslu/api/library/ById/{id}",
            "/bawaslu/api/library/all",
    };

    private static final String[] AUTH_AUTHORIZATION = {
            "/bawaslu/api/berita/**",
            "/bawaslu/api/pengumuman/**",
            "/bawaslu/api/isi-keterangan-informasi/**",
            "/bawaslu/api/jenis-informasi/**",
            "/bawaslu/api/jenis-keterangan/**",
            "/bawaslu/api/permohonan-informasi/**",
            "/bawaslu/api/permohonan-keberatan/**",
            "/bawaslu/api/tags/**",
            "/bawaslu/api/jenis-regulasi/**",
            "/bawaslu/api/menu-regulasi/**",
            "/bawaslu/api/regulasi/**",
            "/bawaslu/api/category-berita/**",
            "/bawaslu/api/tabel-regulasi/**",
            "/bawaslu/api/tabel-dip/**",
            "/bawaslu/api/tabel-sop/**",
            "/bawaslu/api/carousel/**",
            "/bawaslu/api/library/**",
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers(AUTH_AUTHORIZATION).hasRole("ADMIN")
                .antMatchers(AUTH_AUTHORIZATION).hasAnyRole( "ADMIN")
                .anyRequest()
                .authenticated().and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}