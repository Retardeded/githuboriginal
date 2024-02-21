package com.pl.githuboriginal.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Repository {
    private String name;
    private Owner owner;
    private boolean fork;
    // getters and setters
}
