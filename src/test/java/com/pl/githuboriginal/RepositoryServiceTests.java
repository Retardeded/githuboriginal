package com.pl.githuboriginal;

import com.pl.githuboriginal.model.Branch;
import com.pl.githuboriginal.model.BranchInformation;
import com.pl.githuboriginal.model.CustomRepoInformation;
import com.pl.githuboriginal.model.Repository;
import com.pl.githuboriginal.model.Owner;
import com.pl.githuboriginal.model.Commit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RepositoryServiceTests {

    @Autowired
    private RepositoryService repositoryService;

    @MockBean
    private GitHubClient gitHubClient;

    @BeforeEach
    void setUp() {
        Owner owner = new Owner("Retardeded");
        Repository repo1 = new Repository("Repo1", owner, false);
        Repository repo2 = new Repository("Repo2", owner, true); // This is a fork and should be filtered out
        Branch branch1 = new Branch("main", new Commit("sha12345"));
        Branch branch2 = new Branch("develop", new Commit("sha67890"));

        when(gitHubClient.listUserRepos("Retardeded")).thenReturn(Arrays.asList(repo1, repo2));
        when(gitHubClient.listRepoBranches("Retardeded", "Repo1")).thenReturn(Arrays.asList(branch1, branch2));
    }

    @Test
    void testListNonForkRepos() {
        List<CustomRepoInformation> repos = repositoryService.listNonForkRepos("Retardeded");
        assertNotNull(repos);
        assertEquals(1, repos.size());

        CustomRepoInformation repoInfo = repos.get(0);
        assertEquals("Repo1", repoInfo.getRepositoryName());
        assertEquals("Retardeded", repoInfo.getOwnerLogin());
        assertEquals(2, repoInfo.getBranches().size());

        BranchInformation mainBranch = repoInfo.getBranches().get(0);
        assertEquals("main", mainBranch.getBranchName());
        assertEquals("sha12345", mainBranch.getLastCommitSha());
    }
}
