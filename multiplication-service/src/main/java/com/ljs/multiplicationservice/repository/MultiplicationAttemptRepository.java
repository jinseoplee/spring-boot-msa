package com.ljs.multiplicationservice.repository;

import com.ljs.multiplicationservice.entity.MultiplicationAttempt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * {@link MultiplicationAttempt}를 저장하고 조회하기 위한 인터페이스
 */
public interface MultiplicationAttemptRepository extends CrudRepository<MultiplicationAttempt, Long> {
    /**
     * @return 닉네임에 해당하는 사용자의 최근 답안 5개
     */
    List<MultiplicationAttempt> findTop5ByNicknameOrderByIdDesc(String nickname);
}
