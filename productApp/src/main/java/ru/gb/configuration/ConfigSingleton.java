package ru.gb.configuration;

import lombok.Data;

//Класс для 12 домашнего задания паттерн Singleton
@Data
public class ConfigSingleton {
    private static ConfigSingleton instance;
    private String connectionString;
    private String user;
    private String password;
    private ConfigSingleton(){

    }
    public static ConfigSingleton getInstance(){
        if (instance == null){
            instance = new ConfigSingleton();
        }
        return instance;
    }
}
