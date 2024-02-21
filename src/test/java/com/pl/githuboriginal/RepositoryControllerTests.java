package com.pl.githuboriginal;

import com.pl.githuboriginal.model.CustomRepoInformation;
import com.pl.githuboriginal.model.BranchInformation;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RepositoryController.class)
public class RepositoryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepositoryService repositoryService;

    @Test
    void listUserRepositoriesSuccess() throws Exception {
        List<CustomRepoInformation> repos = Arrays.asList(
                new CustomRepoInformation("Repo1", "Retardeded", Arrays.asList(
                        new BranchInformation("main", "sha12345")
                ))
        );

        given(repositoryService.listNonForkRepos("Retardeded")).willReturn(repos);

        mockMvc.perform(get("/api/repositories/Retardeded").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].repositoryName").value("Repo1"))
                .andExpect(jsonPath("$[0].ownerLogin").value("Retardeded"))
                .andExpect(jsonPath("$[0].branches[0].branchName").value("main"))
                .andExpect(jsonPath("$[0].branches[0].lastCommitSha").value("sha12345"));
    }

    @Test
    void listUserRepositoriesNotFound() throws Exception {
        given(repositoryService.listNonForkRepos("nonexistent")).willThrow(FeignException.NotFound.class);

        mockMvc.perform(get("/api/repositories/nonexistent").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("User not found"));
    }
}
