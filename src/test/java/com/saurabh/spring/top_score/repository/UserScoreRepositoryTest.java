package com.saurabh.spring.top_score.repository;

import com.saurabh.spring.top_score.model.UserScore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserScoreRepositoryTest {

    @Autowired
    private UserScoreRepository userScoreRepository;

    @Test
    public void testSaveUserScore() {

        UserScore userData = new UserScore();
        userData.setScore(10);
        userData.setName("saurabh");
        userData.setId(1);
        userScoreRepository.save(userData);

        UserScore savedUserData = userScoreRepository.findById(1).get();

        assertNotNull(savedUserData);
        assertEquals(savedUserData.getName(), userData.getName());
    }

    @Test(expected = NoSuchElementException.class)
    public void testUserNotPresent() {

        UserScore userData = new UserScore();
        userData.setScore(10);
        userData.setName("saurabh");
        userData.setId(1);
        userScoreRepository.save(userData);

        userScoreRepository.findById(2).get();
    }

}
