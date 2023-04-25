package com.example.test_task1.service;

import com.example.test_task1.Entity.Operation;
import com.example.test_task1.Entity.SockStock;
import com.example.test_task1.repository.SockStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class SocksService {
    @Autowired
    SockStockRepository sockStockRepository;

    public Long getSocksQuantity(String color, Operation operation, Byte cottonPart) {
        color = color.toLowerCase(Locale.ROOT);
        List<SockStock> sockStocks = new ArrayList<>();

        switch (operation) {
            case equal:
                sockStocks = sockStockRepository.findAllByColorAndCottonPart(color, cottonPart);
                break;
            case moreThan:
                sockStocks = sockStockRepository.findAllByColorAndCottonPartGreaterThan(color, cottonPart);
                break;
            case lessThan:
                sockStocks = sockStockRepository.findAllByColorAndCottonPartLessThan(color, cottonPart);
                break;
        }

        Long quantity = 0L;
        for (SockStock sockStock : sockStocks) {
            quantity += sockStock.getQuantity();
        }
        return quantity;
    }

    public void addSocks(SockStock income) {
        SockStock sockStock = sockStockRepository.findByColorAndCottonPart(
                income.getColor().toLowerCase(Locale.ROOT),
                income.getCottonPart());

        if (sockStock == null) {
            income.setColor(income.getColor().toLowerCase(Locale.ROOT));
            sockStock = income;
        } else
            sockStock.setQuantity(sockStock.getQuantity() + income.getQuantity());

        sockStockRepository.save(sockStock);
    }
    public void withdrawSocks(SockStock withdraw){
        SockStock sockStock = sockStockRepository.findByColorAndCottonPart(
                withdraw.getColor().toLowerCase(Locale.ROOT),
                withdraw.getCottonPart());
        if(!(sockStock != null && sockStock.getQuantity() - withdraw.getQuantity() >= 0))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        sockStock.setQuantity(sockStock.getQuantity() - withdraw.getQuantity());
        sockStockRepository.save(sockStock);
    }
}
