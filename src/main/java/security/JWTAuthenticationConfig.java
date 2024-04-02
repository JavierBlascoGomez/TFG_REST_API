package security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import models.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class JWTAuthenticationConfig {
    private static final String TOKEN_BEARER_PREFIX = "Bearer ";

    @Value("$(jwt.secret)")
    private String secretKey;

    @Value("$(jwt.expiration)")
    private long expiration;

    public String getJWTToken(User user) {

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(String.join(",", user.getRolesAssociated())
                        .stream()
                        .map(role -> role.getName())
                        .collect(Collectors.toList())
                );

        String token = Jwts
                .builder()
                .setId(String.valueOf(user.getId()))
                .setSubject(user.getUsername())
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return TOKEN_BEARER_PREFIX + token;
    }
}
