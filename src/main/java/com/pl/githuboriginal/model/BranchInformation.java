package com.pl.githuboriginal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class BranchInformation {
    private String branchName;
    private String lastCommitSha;
}
