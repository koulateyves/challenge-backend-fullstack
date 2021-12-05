package com.bankin.challengebackend.services;

import com.bankin.challengebackend.model.GetAccountResponse;
import java.io.IOException;

public interface BridgeService {

    /**
     * Methode retourne la list des compte d'un utilisateur
     * @return
     */
     GetAccountResponse getUserAccount() throws IOException;
}
