
package com.project.shopapp.security;

import com.project.shopapp.Service.EmailService;
import com.project.shopapp.Service.FacebookService;
import com.project.shopapp.Service.GithubService;
import com.project.shopapp.Service.imp.EmailServiceImlp;
import com.project.shopapp.dto.EmailDTO;
import com.project.shopapp.dto.FacebookDTO;
import com.project.shopapp.dto.GitDTO;
import com.project.shopapp.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebMvc
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;

    private final EmailService EmailService;

    private final FacebookService FacebookService;

    private final GithubService GithubService;

    @Value("${api.prefix}")
    private String apiPrefix;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests
                            // ------------------------users--------------------//
                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//

                            .requestMatchers(
                                    String.format("%s/users/register", apiPrefix),
                                    String.format("%s/users/login", apiPrefix),
                                    String.format("%s/users/login/oauth2**",
                                            apiPrefix))
                            .permitAll()

                            .requestMatchers(PUT,
                                    String.format("%s/users/update/pass/**",
                                            apiPrefix))
                            .permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/users/checkactive",
                                            apiPrefix))
                            .permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/users**", apiPrefix))
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/users/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/users/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(POST,
                                    String.format("%s/users/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(POST,
                                    String.format("%s/users**", apiPrefix))
                            .permitAll()

                            .requestMatchers(DELETE,
                                    String.format("%s/users/**", apiPrefix))
                            .permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/Role**", apiPrefix))
                            .permitAll()
                            .requestMatchers(POST,
                                    String.format("%s/Role**", apiPrefix))
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/comments**", apiPrefix))
                            .permitAll()
                            .requestMatchers(POST,
                                    String.format("%s/comments**", apiPrefix))
                            .permitAll()
                            .requestMatchers(PUT,
                                    String.format("%s/comments/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(DELETE,
                                    String.format("%s/comments/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/comments/**", apiPrefix))
                            .permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/Comemtyt/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/Comemtyt**", apiPrefix))
                            .permitAll()
                            .requestMatchers(POST,
                                    String.format("%s/Comemtyt**", apiPrefix))
                            .permitAll()
                            .requestMatchers(PUT,
                                    String.format("%s/Comemtyt/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(DELETE,
                                    String.format("%s/Comemtyt/**", apiPrefix))
                            .permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/Author/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/Author**", apiPrefix))
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/Song/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/Song**", apiPrefix))
                            .permitAll()
                            .requestMatchers(POST,
                                    String.format("%s/feed**", apiPrefix))
                            .permitAll()
                            .requestMatchers(PUT,
                                    String.format("%s/feed**", apiPrefix))
                            .permitAll()

                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
                            // ------------------------SONG-GENRE--------------------//
                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
                            .requestMatchers(GET,
                                    String.format("%s/SongGenre/**", apiPrefix))
                            .permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/SongGenre**", apiPrefix))
                            .permitAll()

                            .requestMatchers(DELETE,
                                    String.format("%s/SongGenre/**", apiPrefix))
                            .permitAll()
                            ///-----------

                            .requestMatchers(DELETE,
                                    String.format("%s/SongSinger/delete-by-song/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(PUT,
                                    String.format("%s/SongGenre**", apiPrefix))
                            .permitAll()
                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
                            // ------------------------SONG-SINGER--------------------//
                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
                            .requestMatchers(GET,
                                    String.format("%s/SongSinger/**", apiPrefix))
                            .permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/SongSinger**", apiPrefix))
                            .permitAll()

                            .requestMatchers(DELETE,
                                    String.format("%s/SongSinger/**", apiPrefix))
                            .permitAll()

                            .requestMatchers(PUT,
                                    String.format("%s/SongSinger**", apiPrefix))
                            .permitAll()
                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
                            // ------------------------SONG-AUTHOR--------------------//
                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
                            .requestMatchers(GET,
                                    String.format("%s/SongAuthor/**", apiPrefix))
                            .permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/SongAuthor**", apiPrefix))
                            .permitAll()

                            .requestMatchers(DELETE,
                                    String.format("%s/SongAuthor/**", apiPrefix))
                            .permitAll()

                            .requestMatchers(PUT,
                                    String.format("%s/SongAuthor**", apiPrefix))
                            .permitAll()
                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
                            // ------------------------SINGER-ALBUM--------------------//
                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
                            .requestMatchers(GET,
                                    String.format("%s/singerAlbum/**", apiPrefix))
                            .permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/singerAlbum**", apiPrefix))
                            .permitAll()

                            .requestMatchers(DELETE,
                                    String.format("%s/singerAlbum/**", apiPrefix))
                            .permitAll()

                            .requestMatchers(PUT,
                                    String.format("%s/singerAlbum**", apiPrefix))
                            .permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/album/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/album**", apiPrefix))
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/Singer/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/Singer**", apiPrefix))
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/Genre/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/Genre**", apiPrefix))
                            .permitAll()

                            .requestMatchers(String
                                    .format("%s/oauth2/login/google", apiPrefix))
                            .permitAll()
                            .requestMatchers(String.format("%s/oauth2/login/facebook",
                                    apiPrefix))
                            .permitAll()

                            .requestMatchers(String.format("%s/emails/users/**",
                                    apiPrefix))
                            .permitAll()
                            .requestMatchers(String.format("%s/emails/users", apiPrefix))
                            .permitAll()

                            .requestMatchers(String.format("%s/facebooks/users/**",
                                    apiPrefix))
                            .permitAll()
                            .requestMatchers(String.format("%s/facebooks/users", apiPrefix))
                            .permitAll()
                            .requestMatchers(String.format("%s/githubs/users/**",
                                    apiPrefix))
                            .permitAll()
                            .requestMatchers(String.format("%s/githubs/users", apiPrefix))
                            .permitAll()

                            .requestMatchers(GET, String.format("%s/facebooks/users/**",
                                    apiPrefix))
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/facebooks/users", apiPrefix))
                            .permitAll()
                            .requestMatchers(GET, String.format("%s/githubs/users/**",
                                    apiPrefix))
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/githubs/users", apiPrefix))
                            .permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/emails/users/**", apiPrefix))
                            .permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/emails/users", apiPrefix))
                            .permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/users/email/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/favoriteAlbum/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(POST,
                                    String.format("%s/users/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(POST,
                                    String.format("%s/listen/**", apiPrefix))
                            .permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/listen/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(DELETE,
                                    String.format("%s/listen/**", apiPrefix))
                            .permitAll()



                            .requestMatchers(POST,
                                    String.format("%s/statictical/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/statictical/**", apiPrefix))
                            .permitAll()
                            .requestMatchers(DELETE,
                                    String.format("%s/statictical/**", apiPrefix))
                            .permitAll()

                            .anyRequest().authenticated();

                })
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2Login(Customizer.withDefaults());
        http.cors(Customizer.withDefaults());
        http.oauth2Login(oauth2 -> oauth2.successHandler(authenticationSuccessHandler()));
        http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(List.of("*"));
                configuration.setAllowedMethods(
                        Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(Arrays.asList("Origin", "Authorization",
                        "content-type",
                        "x-auth-token"));
                configuration.setExposedHeaders(List.of("x-auth-token"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                httpSecurityCorsConfigurer.configurationSource(source);
            }
        });
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            Long id = 0L;
            String type = "";

            Object principal = authentication.getPrincipal();

            if (principal instanceof OidcUser) {
                OidcUser oidcUser = (OidcUser) principal;
                String name = oidcUser.getFullName();
                String email = oidcUser.getEmail();
                String picture = oidcUser.getPicture();
                EmailService.createUser(EmailDTO.builder()
                        .email(email)
                        .name(name)
                        .picture(picture)
                        .build());

                id = this.EmailService.getUserByEmail(email).getId();
                type = "email";
            } else {
                if (authentication.getPrincipal() instanceof OAuth2User) {
                    OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
                    String name = oauth2User.getAttribute("name");
                    String email = oauth2User.getAttribute("email");
                    Integer idg = oauth2User.getAttribute("id");
                    String idgit = idg + "";
                    type = "github";

                    GithubService.createUser(GitDTO.builder()
                            .githubId(idgit)
                            .email(email)
                            .name(name)
                            .build());
                    id = this.GithubService.getGithubByEmail(email).getId();
                }
            }

            if (id != 0) {
                response.sendRedirect("http://localhost:4200/onesound/home/users/update?id="
                        + id
                        + "&type=" + type);

            } else {
                response.sendRedirect("http://localhost:4200");

            }
        };
    }

}

// private final JwtTokenFilter jwtTokenFilter;
// @Value("${api.prefix}")
// private String apiPrefix;

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
// Exception {
// http
// .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
// .authorizeHttpRequests(requests -> requests
// .anyRequest().permitAll())
// .csrf(AbstractHttpConfigurer::disable)
// .cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
// @Override
// public void customize(CorsConfigurer<HttpSecurity>
// httpSecurityCorsConfigurer) {
// CorsConfiguration configuration = new CorsConfiguration();
// configuration.setAllowedOrigins(List.of("*"));
// configuration.setAllowedMethods(
// Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE",
// "OPTIONS"));
// configuration.setAllowedHeaders(
// Arrays.asList("authorization", "content-type",
// "x-auth-token"));
// configuration.setExposedHeaders(List.of("x-auth-token"));
// UrlBasedCorsConfigurationSource source = new
// UrlBasedCorsConfigurationSource();
// source.registerCorsConfiguration("/**", configuration);
// httpSecurityCorsConfigurer.configurationSource(source);
// }
// });

// return http.build();
// }
// }