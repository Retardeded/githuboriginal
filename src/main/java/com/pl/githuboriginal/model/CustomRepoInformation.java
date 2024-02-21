package com.pl.githuboriginal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CustomRepoInformation {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchInformation> branches;
}
