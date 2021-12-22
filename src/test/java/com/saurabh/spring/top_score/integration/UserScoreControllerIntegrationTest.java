package com.saurabh.spring.top_score.integration;

import com.saurabh.spring.top_score.TopScoreApplication;
import com.saurabh.spring.top_score.model.UserScore;
import org.json.JSONException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TopScoreApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserScoreControllerIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();



    @Test
    public void testAddUserScore() throws JSONException {
        UserScore userData = new UserScore();
        userData.setScore(10);
        userData.setName("rajesh");
        userData.setId(1);

        HttpEntity<UserScore> entity = new HttpEntity<UserScore>(userData, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/rest/api/createUpdateScore"),
                HttpMethod.POST, entity, String.class);

        String expected = "{\"id\":1,\"name\":\"rajesh\",\"score\":10}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testFindUserByName() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/rest/api/score/rajesh"),
                HttpMethod.GET, entity, String.class);

        String expected = "{\"id\":1,\"name\":\"rajesh\",\"score\":10}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
