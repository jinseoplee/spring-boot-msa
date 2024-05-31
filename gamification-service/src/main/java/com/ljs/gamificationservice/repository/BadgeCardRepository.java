package com.ljs.gamificationservice.repository;

import com.ljs.gamificationservice.domain.BadgeCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * {@link BadgeCard}를 저장하고 조회하기 위한 인터페이스
 */
public interface BadgeCardRepository extends CrudRepository<BadgeCard, Long> {
    /**
     * 사용자의 배지 카드 목록을 조회한다.
     *
     * @param userId 사용자 ID
     * @return 최근 획득한 순으로 정렬된 {@link BadgeCard} 리스트
     */
    List<BadgeCard> findByUserIdOrderByCreatedDateDesc(String userId);
}
