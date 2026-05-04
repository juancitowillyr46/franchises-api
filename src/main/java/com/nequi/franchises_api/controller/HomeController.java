package com.nequi.franchises_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nequi.franchises_api.shared.response.StandardResponse;

@RestController
public class HomeController {

    @GetMapping("/")
    public StandardResponse<String> home() {
        return StandardResponse.success("Franchises API is running", "OK");
    }
}
