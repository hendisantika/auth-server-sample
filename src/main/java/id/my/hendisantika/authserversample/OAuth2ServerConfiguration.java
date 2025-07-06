package id.my.hendisantika.authserversample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.StringUtils;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Created by IntelliJ IDEA.
 * Project : auth-server-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 06/07/25
 * Time: 16.16
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class OAuth2ServerConfiguration {
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = OAuth2AuthorizationServerConfigurer
                .authorizationServer();

        http.securityMatcher(authorizationServerConfigurer.getEndpointsMatcher());
        http.with(authorizationServerConfigurer, (authorizationServer) ->
                authorizationServer
                        .authorizationEndpoint(authorizationEndpoint ->
                                authorizationEndpoint.authenticationProviders(configureAuthenticationValidator())
                        )
        );
        http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated());
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(withDefaults());
        http.oauth2ResourceServer((resourceServer) -> resourceServer.jwt(withDefaults()));
        http.exceptionHandling((exceptions) -> exceptions.defaultAuthenticationEntryPointFor(
                new LoginUrlAuthenticationEntryPoint("/login"), createRequestMatcher()));


        return http.build();
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer() {
        return (context) -> {
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
                context.getClaims().claims((claims) -> {
                    OAuth2AuthorizationRequest authorizationRequest = context.getAuthorization().getAttribute(OAuth2AuthorizationRequest.class.getName());
                    String realm = (String) authorizationRequest.getAdditionalParameters().get("realm");
                    if (StringUtils.hasText(realm)) {
                        claims.put("realm", realm);
                    }
                });
            }
        };
    }
}
