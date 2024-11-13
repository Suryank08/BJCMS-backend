package com.bjcms.config.authentication;

import com.bjcms.entity.user.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    private final Key secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    public JwtService() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Use HS512 for a strong security level
}
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("email", user.getEmail());
        claims.put("mobileNumber",user.getMobileNumber());
        claims.put("roles", user.getAuthorities()); // Assuming your User class has a method to get roles

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail()) // or user.getUsername() if you're using a username
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .setIssuer("bjcms")  // Add issuer to verify where the token came from
                .setAudience("web-app") // Set audience for the token
                .setId(UUID.randomUUID().toString())
                .signWith(secretKey, SignatureAlgorithm.HS512) // Use getSignInKey and HS512
                .compact();
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public long getExpirationTime() {
        return jwtExpiration;
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .setIssuer("bjcms") // Ensure issuer is the same across the app
                .setAudience("web-app") // Ensure audience is the same across the app
                .signWith(secretKey, SignatureAlgorithm.HS512) // Use HS512 consistently
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public boolean validateToken(String token, String username) {
        try {
            // Extract username from the token
            final String extractedUsername = extractUsername(token);

            // Check if the token is expired
            return (extractedUsername.equals(username) && !isTokenExpired(token));
        } catch (ExpiredJwtException e) {
            return false; // Token is expired
        }catch (JwtException e) {
            return false; // JWT parsing or other issues
        }  catch (Exception e) {
            return false; // Token is invalid
        }
    }

}
