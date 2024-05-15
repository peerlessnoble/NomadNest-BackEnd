package org.authmicroservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service @AllArgsConstructor
public class JwtService implements IJwtService {
    private final CustomUserDetailsService customUserDetailsService;
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    private static final long JWT_EXPIRATION_MS = 1000 * 60 * 60; // 1 hour
    private static final long REFRESH_TOKEN_EXPIRATION_MS = 1000 * 60 * 60 * 24 * 7; // 7 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public String generateToken(String email) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        Collection<? extends GrantedAuthority> authorieze = userDetails.getAuthorities();
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails);
    }

    public List<String> getRoles(String email) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        List<String> roles = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        Pattern pattern = Pattern.compile("role=(\\w+)");
        Matcher matcher = pattern.matcher(authorities.toString());
        while (matcher.find()) {
            String role = matcher.group(1);
            roles.add(role);
        }
        return roles;
    }

    public String createToken(Map<String, Object> claims, UserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);
        Date refreshTokenExpiryDate = new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION_MS);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuer(userDetails.getAuthorities().iterator().next().getAuthority())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token);
    }

    public String getUsernameFromToken(String token) {
        return parseClaims(token).getBody().getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return parseClaims(token).getBody().getExpiration();
    }

    public boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

}
