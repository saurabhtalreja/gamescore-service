package com.saurabh.spring.top_score.service;

import com.saurabh.spring.top_score.model.UserScore;
import com.saurabh.spring.top_score.repository.UserScoreRepository;
import com.saurabh.spring.top_score.service.impl.UserScoreServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserScoreServiceImplTest {
    @InjectMocks
    private UserScoreServiceImpl userScoreServiceImpl;

    @Mock
    private UserScoreRepository userScoreRepository;

    @Test
    public void whenGetAllUser_thenReturnUserList() {

        List<UserScore> userDataList = new ArrayList<>();
        UserScore userData = new UserScore();
        userData.setScore(10);
        userData.setName("rajesh");
        userData.setId(1);
        userDataList.add(userData);

        UserScore userData1 = new UserScore();
        userData1.setScore(40);
        userData1.setName("mahesh");
        userData1.setId(2);
        userDataList.add(userData1);

        Mockito.when(userScoreRepository.findAll()).thenReturn(userDataList);

        List<UserScore> returnedUserList = userScoreServiceImpl.getAllUserScore();
        assertEquals(returnedUserList.size(), userDataList.size());


    }
}
