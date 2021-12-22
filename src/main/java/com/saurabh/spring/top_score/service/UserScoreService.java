package com.saurabh.spring.top_score.service;

import com.saurabh.spring.top_score.model.UserScore;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface UserScoreService {

    UserScore createUpdateScoreForUser(UserScore order) throws IOException;

    List<UserScore> findTopScorers(Integer total);

    List<UserScore> getAllUserScore();

    UserScore getUserScoreByName(String name);

    boolean deleteUserScoreByName(String name);
}
