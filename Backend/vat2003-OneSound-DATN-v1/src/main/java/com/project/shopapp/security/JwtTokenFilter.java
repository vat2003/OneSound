
package com.project.shopapp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.shopapp.entity.Account;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    @Value("${api.prefix}")
    private String apiPrefix;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        try {
            if (this.isBypassToken(request)) {
                filterChain.doFilter(request, response);
                return;
            }
            final String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                final String token = authHeader.substring(7);
                final String phoneNumber = jwtTokenUtil.extractEmail(token);
                if (phoneNumber != null
                        && SecurityContextHolder.getContext().getAuthentication() == null) {
                    Account userDetails = (Account) userDetailsService.loadUserByUsername(phoneNumber);
                    if (jwtTokenUtil.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }

    private boolean isBypassToken(@NonNull HttpServletRequest request) {
        final List<Pair<String, String>> bypassTokens = Arrays.asList(
                Pair.of(String.format("%s/comments", apiPrefix), "PUT"),
                Pair.of(String.format("%s/comments", apiPrefix), "DELETE"),
                Pair.of(String.format("%s/comments", apiPrefix), "GET"),
                Pair.of(String.format("%s/comments", apiPrefix), "POST"),
                Pair.of(String.format("%s/comments/", apiPrefix), "GET"),
                Pair.of(String.format("%s/Comemtyt/", apiPrefix), "GET"),
                Pair.of(String.format("%s/Comemtyt", apiPrefix), "DELETE"),
                Pair.of(String.format("%s/Comemtyt", apiPrefix), "GET"),
                Pair.of(String.format("%s/Comemtyt", apiPrefix), "POST"),
                Pair.of(String.format("%s/Comemtyt/", apiPrefix), "PUT"),
                Pair.of(String.format("%s/Role", apiPrefix), "GET"),
                Pair.of(String.format("%s/Role", apiPrefix), "POST"),
                Pair.of(String.format("%s/Author", apiPrefix), "GET"),
                Pair.of(String.format("%s/Author/", apiPrefix), "GET"),
                Pair.of(String.format("%s/Song", apiPrefix), "GET"),
                Pair.of(String.format("%s/SongGenre", apiPrefix), "GET"),
                Pair.of(String.format("%s/SongAuthor", apiPrefix), "GET"),
                Pair.of(String.format("%s/singerAlbum", apiPrefix), "GET"),

                Pair.of(String.format("%s/album", apiPrefix), "GET"),
                Pair.of(String.format("%s/Genre", apiPrefix), "GET"),
                Pair.of(String.format("%s/Singer", apiPrefix), "GET"),
                Pair.of(String.format("%s/users", apiPrefix), "GET"),
                Pair.of(String.format("%s/users", apiPrefix), "POST"),
                Pair.of(String.format("%s/users/register", apiPrefix), "POST"),
                Pair.of(String.format("%s/users/checkactive", apiPrefix), "POST"),
                Pair.of(String.format("%s/users/update/pass", apiPrefix), "PUT"),
                Pair.of(String.format("%s/users/login", apiPrefix), "POST"),
                Pair.of(String.format("%s/users/", apiPrefix), "DELETE"),
                Pair.of(String.format("%s/users/", apiPrefix), "GET"),
                Pair.of(String.format("https://www.googleapis.com/youtube/v3/search/**", apiPrefix), "GET"),
                Pair.of(String.format("%s/users/feed", apiPrefix), "POST"),
                Pair.of(String.format("%s/emails", apiPrefix), "GET"),
                Pair.of(String.format("%s/facebooks", apiPrefix), "GET"),
                Pair.of(String.format("%s/githubs", apiPrefix), "GET"),
                Pair.of(String.format("%s/emails/users", apiPrefix), "GET"),
                Pair.of(String.format("%s/facebooks/users", apiPrefix), "GET"),
                Pair.of(String.format("%s/githubs/users", apiPrefix), "GET"),

                Pair.of(String.format("%s/favoriteAlbum/", apiPrefix), "POST"),
                Pair.of(String.format("%s/favoriteAlbum/", apiPrefix), "GET"),
                Pair.of(String.format("%s/favoriteAlbum/", apiPrefix), "DELETE"),

                Pair.of(String.format("%s/listen/", apiPrefix), "POST"),
                Pair.of(String.format("%s/listen/", apiPrefix), "GET"),
                Pair.of(String.format("%s/listen/", apiPrefix), "DELETE"),
                Pair.of(String.format("%s/SongSinger/", apiPrefix), "DELETE"),
                Pair.of(String.format("%s/SongAuthor/", apiPrefix), "DELETE"),
                Pair.of(String.format("%s/SongGenre/", apiPrefix), "DELETE"),

                Pair.of(String.format("%s/statictical/", apiPrefix), "POST"),
                Pair.of(String.format("%s/statictical", apiPrefix), "GET"),
                Pair.of(String.format("%s/statictical/", apiPrefix), "DELETE")
                // Pair.of(String.format("%s/oauth2/login/google", apiPrefix), "GET"),
                // Pair.of(String.format("%s/oauth2/login/facebook", apiPrefix), "GET"),
                // Pair.of(String.format("%s/users/login/google", apiPrefix), "GET"),
                // Pair.of(String.format("%s/oauth2/get/info/google", apiPrefix), "GET")
        );

        for (Pair<String, String> bypassToken : bypassTokens) {
            if (request.getServletPath().contains(bypassToken.getFirst()) &&
                    request.getMethod().equals(bypassToken.getSecond())) {
                return true;
            }
        }

        return false;
    }
}

// package com.project.shopapp.security;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.NonNull;
// import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Value;
// import
// org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import
// org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.project.shopapp.entity.Account;

// import java.io.IOException;

// @Component
// @RequiredArgsConstructor
// public class JwtTokenFilter extends OncePerRequestFilter {
// @Value("${api.prefix}")
// private String apiPrefix;
// private final UserDetailsService userDetailsService;
// private final JwtTokenUtil jwtTokenUtil;

// @Override
// protected void doFilterInternal(@NonNull HttpServletRequest request,
// @NonNull HttpServletResponse response,
// @NonNull FilterChain filterChain)
// throws ServletException, IOException {
// try {
// final String authHeader = request.getHeader("Authorization");
// if (authHeader != null && authHeader.startsWith("Bearer ")) {
// final String token = authHeader.substring(7);
// final String phoneNumber = jwtTokenUtil.extractEmail(token);
// if (phoneNumber != null
// && SecurityContextHolder.getContext().getAuthentication() == null) {
// Account userDetails = (Account)
// userDetailsService.loadUserByUsername(phoneNumber);
// if (jwtTokenUtil.validateToken(token, userDetails)) {
// UsernamePasswordAuthenticationToken authenticationToken = new
// UsernamePasswordAuthenticationToken(
// userDetails,
// null,
// userDetails.getAuthorities());
// authenticationToken.setDetails(new
// WebAuthenticationDetailsSource().buildDetails(request));
// SecurityContextHolder.getContext().setAuthentication(authenticationToken);
// }
// }
// }
// filterChain.doFilter(request, response);
// } catch (Exception e) {
// response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
// }
// }
// }
