package com.pl.githuboriginal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Branch {
    private String name;
    private Commit commit;
}
