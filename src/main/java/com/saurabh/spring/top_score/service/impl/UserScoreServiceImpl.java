package com.saurabh.spring.top_score.service.impl;

import com.saurabh.spring.top_score.model.UserScore;
import com.saurabh.spring.top_score.repository.UserScoreRepository;
import com.saurabh.spring.top_score.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
@Service
public class UserScoreServiceImpl implements UserScoreService {
    @Autowired
    UserScoreRepository userScoreRepository;

    @Override
    public UserScore createUpdateScoreForUser(UserScore userData) throws IOException {
        UserScore existingUser = userScoreRepository.findByNameEquals(userData.getName());
        if (existingUser != null) {
            existingUser.setScore(userData.getScore());
        } else {
             existingUser = new UserScore();
             existingUser.setName(userData.getName());
             existingUser.setScore(userData.getScore());
        }
        String content = existingUser.toString();
        System.out.println(content);
        Files.write(Paths.get("./userScore.txt"), content.getBytes());
        return userScoreRepository.save(existingUser);
    }

    @Override
    public List<UserScore> findTopScorers(Integer count) {
        if (count == null) {
            return userScoreRepository.findTop5ByOrderByScoreDesc();
        } else {
            return userScoreRepository.findTopScorers(count);
        }
    }

    @Override
    public List<UserScore> getAllUserScore() {
        return (List<UserScore>) userScoreRepository.findAll();
    }

    @Override
    public UserScore getUserScoreByName(String name) {
        Optional<UserScore> dbOrder = Optional.ofNullable(userScoreRepository.findByNameEquals(name));
        return dbOrder.orElse(null);
    }

    @Override
    public boolean deleteUserScoreByName(String name) {
        userScoreRepository.deleteByName(name);
        return getUserScoreByName(name) == null;
    }
}
