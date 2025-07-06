package id.my.hendisantika.authserversample;

import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationContext;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by IntelliJ IDEA.
 * Project : auth-server-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 06/07/25
 * Time: 16.21
 * To change this template use File | Settings | File Templates.
 */
public class RealmValidator implements Consumer<OAuth2AuthorizationCodeRequestAuthenticationContext> {

    private final Map<String, Set<String>> allowedRealmsForClient = Map.of(
            "pkce-client", Set.of("invoice-realm", "order-realm", "product-realm"));
}
