package com.example.springrestotot;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Quote {

    private @Id @GeneratedValue Long id;
    private String content;
    private String author;

    public Quote() {}

    public Quote(String content, String author) {
        this.content = content;
        this.author = author;
    }
}
