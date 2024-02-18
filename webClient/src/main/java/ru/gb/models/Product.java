package ru.gb.models;

import lombok.Data;


@Data
public class Product {

    private Long id;

    private String name;

    private String description;

    private double price;

    private String pictureUrl;

}
