package netchill.API.Gateway.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
public class ExempleFilter extends AbstractGatewayFilterFactory<ExempleFilter.Config> {

    public ExempleFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config){
        return (exchange,chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            if (!headers.containsKey("Authorization")|| !config.verify(headers.getFirst("Authorization")))
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            return chain.filter(exchange).then(Mono.fromRunnable( ()-> {
                //après
            }));
        };
    }

    public static class Config{

        private static Algorithm jwtAlgo = Algorithm.HMAC256("super_secret");;
        private static JWTVerifier verifier = JWT.require(jwtAlgo).withIssuer("auth0").build();

        public boolean verify(String token) {
            if (token == null || token.equals("none")) {
                return false;
            }
            try{
                verifier.verify(token);
            }catch(Exception e){
                return false;
            }
            return true;
        }
    }
}


