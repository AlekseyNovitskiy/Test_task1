package com.example.test_task1.repository;

import com.example.test_task1.Entity.SockStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SockStockRepository extends JpaRepository<SockStock, Long> {
    SockStock findByColorAndCottonPart(String color, Byte cottonPart);

    List<SockStock> findAllByColorAndCottonPart(String color, Byte cottonPart);
    List<SockStock> findAllByColorAndCottonPartGreaterThan(String color, Byte cottonPart);
    List<SockStock> findAllByColorAndCottonPartLessThan(String color, Byte cottonPart);
}
