package com.saurabh.spring.top_score.controller;

import com.saurabh.spring.top_score.model.UserScore;
import com.saurabh.spring.top_score.service.UserScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/rest/api")
@Validated
public class UserScoreController {
    private static final Logger log = LoggerFactory.getLogger(UserScoreController.class);

    @Autowired
    private UserScoreService userScoreService;

    @GetMapping("/topScorers")
    public ResponseEntity<List<UserScore>> findTopScorers(@RequestParam(required = false) Integer count) {

        return new ResponseEntity<>(userScoreService.findTopScorers(count), HttpStatus.OK);

    }

    @GetMapping("/score/{name}")
    public ResponseEntity<UserScore> getScoreByName(@PathVariable("name") @NotBlank String name) {


        UserScore userData = userScoreService.getUserScoreByName(name);

        if (userData != null) {
            return new ResponseEntity<>(userData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/createUpdateScore")
    public ResponseEntity<UserScore> createUpdateScoreForUser(@Valid @RequestBody UserScore userScore) throws IOException {

        return new ResponseEntity<>(userScoreService.createUpdateScoreForUser(userScore), HttpStatus.CREATED);

    }

    @DeleteMapping("/deleteUserScore/{name}")
    public ResponseEntity<HttpStatus> deleteUserScore(@PathVariable("name") @NotBlank String name) throws Exception {

        if (!userScoreService.deleteUserScoreByName(name)) throw new Exception();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
