package basic.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

import io.jsonwebtoken.Jwts;

/**
 *  jwt验证
 */
@Component
public class JwtUtil implements Serializable  {

    @Value("${jwtSecretCode}")
    String jwtSecretCode;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecretCode).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     *  create token
     */
    public String createToken(Long userId, String userName) {
        System.out.println("secret : "+ jwtSecretCode);
        return JWT.create().withExpiresAt(new Date(System.currentTimeMillis())).withAudience(String.valueOf(userId))
                .withClaim("name", userName).withSubject(String.valueOf(userId)).sign(Algorithm.HMAC256(jwtSecretCode));
    }

    /**
     *  decode token
     * @param token
     * @param userId
     * @return
     */
    public void decodeToken(String token, Long userId) {
        String tokenUserId = JWT.decode(token).getAudience().get(0);
        //if (userId.equals(tokenUserId)) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtSecretCode)).build();
        jwtVerifier.verify(token);
        //} else {
        //return ResultResponse.fail(1,"验证失败");
        //}
    }

}

