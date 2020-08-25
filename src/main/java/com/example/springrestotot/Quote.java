package com.example.springrestotot;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Quote {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String content;
    private String author;


}
