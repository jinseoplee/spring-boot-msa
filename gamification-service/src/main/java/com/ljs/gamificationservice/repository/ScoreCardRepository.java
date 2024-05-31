package com.ljs.gamificationservice.repository;

import com.ljs.gamificationservice.domain.LeaderboardRow;
import com.ljs.gamificationservice.domain.ScoreCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * {@link ScoreCard}를 저장하고 조회하기 위한 인터페이스
 */
public interface ScoreCardRepository extends CrudRepository<ScoreCard, Long> {
    /**
     * ScoreCard의 점수를 합해서 사용자의 총 점수를 조회한다.
     *
     * @param userId 사용자 ID
     * @return 사용자의 총 점수
     */
    @Query("SELECT SUM(s.score) " +
            "FROM ScoreCard s " +
            "WHERE s.userId = :userId " +
            "GROUP BY s.userId")
    Integer getTotalScoreForUser(@Param("userId") String userId);

    /**
     * 사용자와 사용자의 총 점수를 나타내는 {@link LeaderboardRow} 리스트를 조회한다.
     *
     * @return 높은 점수순으로 정렬된 리더보드
     */
    @Query("SELECT NEW com.ljs.gamificationservice.domain.LeaderboardRow(s.userId, SUM(s.score)) " +
            "FROM ScoreCard s " +
            "GROUP BY s.userId " +
            "ORDER BY SUM(s.score) DESC " +
            "LIMIT 10")
    List<LeaderboardRow> findFirst10();
}
