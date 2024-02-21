package com.pl.githuboriginal;

import com.pl.githuboriginal.model.Branch;
import com.pl.githuboriginal.model.BranchInformation;
import com.pl.githuboriginal.model.CustomRepoInformation;
import com.pl.githuboriginal.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryService {

    @Autowired
    private GitHubClient gitHubClient;

    public List<CustomRepoInformation> listNonForkRepos(String user) {
        List<Repository> repos = gitHubClient.listUserRepos(user);
        List<CustomRepoInformation> result = new ArrayList<>();

        for (Repository repo : repos) {
            if (!repo.isFork()) {
                List<Branch> branches = gitHubClient.listRepoBranches(repo.getOwner().getLogin(), repo.getName());
                List<BranchInformation> branchInfos = branches.stream()
                        .map(branch -> new BranchInformation(branch.getName(), branch.getCommit().getSha()))
                        .collect(Collectors.toList());

                result.add(new CustomRepoInformation(repo.getName(), repo.getOwner().getLogin(), branchInfos));
            }
        }

        return result;
    }
}
