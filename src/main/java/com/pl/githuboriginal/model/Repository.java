package com.pl.githuboriginal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Repository {
    private String name;
    private Owner owner;
    private boolean fork;
}
