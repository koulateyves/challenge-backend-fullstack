package com.bankin.challengebackend.services.ipml;

import com.bankin.challengebackend.clients.BridgeClient;
import com.bankin.challengebackend.model.AuthenticateResponse;
import com.bankin.challengebackend.model.GetAccountResponse;
import com.bankin.challengebackend.services.BridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

/**
 * Bridge is Bankin's SaaS API. This service is where the calls to the API should be implemented.
 * <p>
 * The "doSomething" method doesn't actually do anything yet and needs to be modified to fit the exercice's needs.
 */
@Component
public class BridgeServiceImpl implements BridgeService {

    // these are hardcoded for simplicity's sake
    private static final String USER_EMAIL = "user1@mail.com";
    private static final String USER_PASSWORD = "a!Strongp#assword1";
    public static final String BEARER = "Bearer ";
    public static final String LIMIT = "10";

    @Autowired
    private BridgeClient bridgeClient;

    @Value("${bridge.api.version}")
    private String version;
    @Value("${bridge.api.client-id}")
    private String clientId;
    @Value("${bridge.api.client-secret}")
    private String clientSecret;

    /**
     * This method is "complete" and doesn't need editing, except if you feel some things could be improved (there
     * is no trap)
     */
    private Optional<AuthenticateResponse> authenticateUser(final String email, final String password) throws IOException {
        var response = bridgeClient.authenticate(version, email, password, clientId, clientSecret).execute();
        if (response.isSuccessful()) {
            return Optional.of(response.body());
        }
        return Optional.empty();
    }

    /**
     * Methode retourne la liste des comptes utilsateurs
     * Cette methode devrait prendre en parametre le token
     *
     * @return
     * @throws IOException
     */
    @Override
    public GetAccountResponse getUserAccount() throws IOException {
        var authorization = authenticateUser(USER_EMAIL, USER_PASSWORD);
        if(authorization.isPresent() && authorization.get().expiresAt.isAfter(Instant.now())){
            return bridgeClient.getAccounts(version, BEARER +authorization.get().accessToken, LIMIT, clientId, clientSecret).execute().body();
        }
        return new GetAccountResponse();
    }

}
