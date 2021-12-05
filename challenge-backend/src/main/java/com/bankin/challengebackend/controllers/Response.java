package com.bankin.challengebackend.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Response {
    public Object roundedValue = "value";
    public List<Object> accounts;
}
