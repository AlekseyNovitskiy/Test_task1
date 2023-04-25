package com.example.test_task1.controller;

import com.example.test_task1.Entity.Operation;
import com.example.test_task1.Entity.SockStock;
import com.example.test_task1.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/socks")
public class SocksController {
    @Autowired
    SocksService socksService;

    @GetMapping
    public String getSocksQuantity(@RequestParam String color,
                                   @RequestParam Operation operation,
                                   @RequestParam Byte cottonPart){
        return socksService.getSocksQuantity(color, operation, cottonPart).toString();
    }

    @PostMapping(path="income")
    public void addSocks(@RequestBody SockStock income) {
        socksService.addSocks(income);
    }

    @PostMapping(path = "outcome")
    public void withdrawSocks(@RequestBody SockStock withdraw){
        socksService.withdrawSocks(withdraw);
    }
}
