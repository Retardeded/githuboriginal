package com.pl.githuboriginal;

import com.pl.githuboriginal.model.Branch;
import com.pl.githuboriginal.model.Repository;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "github-client", url = "${github.api.url}")
public interface GitHubClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/{user}/repos")
    List<Repository> listUserRepos(@PathVariable("user") String user);

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}/branches")
    List<Branch> listRepoBranches(@PathVariable("owner") String owner, @PathVariable("repo") String repo);
}
