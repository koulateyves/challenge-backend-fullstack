package com.bankin.challengebackend.clients;


import com.bankin.challengebackend.model.AuthenticateResponse;
import com.bankin.challengebackend.model.GetAccountResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BridgeClient {

    @POST("/v2/authenticate")
    Call<AuthenticateResponse> authenticate(@Header("Bankin-Version") String version,
                                            @Query(value = "email", encoded = true) String email,
                                            @Query(value = "password", encoded = true) String password,
                                            @Query("client_id") String clientId,
                                            @Query("client_secret") String clientSecret);

    @GET("/v2/accounts")
    Call<GetAccountResponse> getAccounts(@Header("Bankin-Version") String version,
                                         @Header("Authorization") String authorization,
                                         @Query("limit") String limit,
                                         @Query("client_id") String clientId,
                                         @Query("client_secret") String clientSecret);
}
