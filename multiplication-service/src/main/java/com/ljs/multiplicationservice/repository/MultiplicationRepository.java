package com.ljs.multiplicationservice.repository;

import com.ljs.multiplicationservice.entity.Multiplication;
import org.springframework.data.repository.CrudRepository;

/**
 * {@link Multiplication}을 저장하고 조회하기 위한 인터페이스
 */
public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {
}
