package com.ljs.authserver.service;

import com.ljs.authserver.common.exception.DuplicateUserIdException;
import com.ljs.authserver.dto.SignupRequest;
import com.ljs.authserver.entity.User;
import com.ljs.authserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(SignupRequest signupRequest) {
        // 유효성 검사
        validate(signupRequest);

        // 사용자 엔티티 생성
        User user = new User(
                signupRequest.getUserId(),
                passwordEncoder.encode(signupRequest.getPassword())
        );

        // 사용자 생성
        userRepository.save(user);
    }

    /**
     * 입력된 사용자 정보의 유효성을 검사하는 메서드
     */
    private void validate(SignupRequest signupRequest) {
        validateUserId(signupRequest.getUserId());
        validatePasswordMatching(signupRequest.getPassword(), signupRequest.getConfirmPassword());
    }

    /**
     * 아이디 중복 여부를 확인하고 중복되는 경우 예외를 발생시킨다.
     */
    private void validateUserId(String userId) {
        if (userRepository.existsByUserId(userId)) {
            throw new DuplicateUserIdException("이미 사용 중인 ID입니다.");
        }
    }

    /**
     * 비밀번호와 확인 비밀번호가 일치하는지 확인하고 일치하지 않는 경우 예외를 발생시킨다.
     */
    private void validatePasswordMatching(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
