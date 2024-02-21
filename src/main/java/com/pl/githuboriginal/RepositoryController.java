package com.pl.githuboriginal;

import com.pl.githuboriginal.model.CustomRepoInformation;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/repositories")
public class RepositoryController {

    @Autowired
    private RepositoryService repositoryService;

    @GetMapping("/{username}")
    public ResponseEntity<?> listUserRepositories(@PathVariable String username) {
        try {
            List<CustomRepoInformation> repos = repositoryService.listNonForkRepos(username);
            return new ResponseEntity<>(repos, HttpStatus.OK);
        } catch (FeignException.NotFound e) {
            return new ResponseEntity<>(Map.of(
                    "status", HttpStatus.NOT_FOUND.value(),
                    "message", "User not found"
            ), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of(
                    "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "message", "An unexpected error occurred"
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
