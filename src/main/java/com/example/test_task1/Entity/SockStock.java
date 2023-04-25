package com.example.test_task1.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class SockStock {
    @Id
    @GeneratedValue
    private Long id;
    private String color;
    private Byte cottonPart;
    private Long quantity;


}
