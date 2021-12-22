package com.saurabh.spring.top_score.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saurabh.spring.top_score.model.UserScore;
import com.saurabh.spring.top_score.service.UserScoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserScoreController.class)
public class UserScoreControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserScoreService userScoreService;

    @Test
    public void getTopScorer() throws Exception {

        List<UserScore> usersList = new ArrayList<>();
        UserScore userData = new UserScore();
        userData.setScore(10);
        userData.setName("rajesh");
        userData.setId(1);
        usersList.add(userData);

        when(userScoreService.findTopScorers(null)).thenReturn(usersList);
        mvc.perform(MockMvcRequestBuilders
                .get("/rest/api/topScorers")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].score").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").value(1));
    }


    @Test
    public void createUpdateScoreOfUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/rest/api/createUpdateScore")
                .content(asJsonString(new UserScore(null, "Rajesh", 567)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
