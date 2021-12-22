package com.saurabh.spring.top_score.repository;

import com.saurabh.spring.top_score.model.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserScoreRepository extends JpaRepository<UserScore, Integer> {

    List<UserScore> findTop5ByOrderByScoreDesc();

    @Query(value = "select * from UserScore us order by score desc LIMIT  ?1",nativeQuery = true)
    List<UserScore> findTopScorers(Integer total);

    List<UserScore> findByNameStartingWith(String prefix);

    @Transactional
    void deleteByName(String name);

    UserScore findByNameEquals(String name);
}
