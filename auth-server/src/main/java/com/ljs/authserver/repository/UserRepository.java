package com.ljs.authserver.repository;

import com.ljs.authserver.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * {@link User}를 저장하고 조회하기 위한 인터페이스
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUserId(String userId);

    boolean existsByUserId(String userId);
}
