package com.bankin.challengebackend.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.bankin.challengebackend.model.Account;
import com.bankin.challengebackend.model.GetAccountResponse;
import com.bankin.challengebackend.model.Response;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.bankin.challengebackend.services.ipml.BridgeServiceImpl;

/**
 * AccountController.myEndpoint is called when requesting GET /mycontroller/myroute
 * <p>
 * You can try it by running curl -X GET localhost:8080/mycontroller/myroute
 * <p>
 * The BridgeClient has been injected and ready for use. Maybe the controller, method and route need some renaming?
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private BridgeServiceImpl bridgeService;

    /**
     * Ce endPoint devrait prendre le token en parametre de la requette
     * @return
     * @throws IOException
     */
    @CrossOrigin
    @GetMapping("/all")
    public Response getAllAccount() throws IOException {
        final GetAccountResponse userAccount = bridgeService.getUserAccount();
        return Response.builder()
                .accounts(userAccount!=null ? Collections.singletonList(userAccount.getAccounts()) : null)
                .roundedValue(getBalance(userAccount.getAccounts()))
                .build();
    }

    private Double getBalance(List<Account> accountList){
        return CollectionUtils.isEmpty(accountList)? null : accountList.stream().mapToDouble(value -> value.balance).sum();
    }
}
