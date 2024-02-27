package com.example.apijwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Registro {
    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Autoincrement
    private Long id;


}
