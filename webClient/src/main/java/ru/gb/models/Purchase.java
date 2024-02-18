package ru.gb.models;

import lombok.Data;

@Data
public class Purchase {

    private Long id;

    private String userName;

    private Product product;
}
