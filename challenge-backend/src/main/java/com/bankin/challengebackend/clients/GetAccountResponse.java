package com.bankin.challengebackend.clients;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetAccountResponse {

    @JsonProperty("resources")
    private List<Account> accounts;
}
