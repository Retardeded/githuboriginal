package com.pl.githuboriginal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Repository {
    private String name;
    private Owner owner;
    private boolean fork;
}
