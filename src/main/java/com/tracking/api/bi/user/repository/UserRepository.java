package com.tracking.api.bi.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tracking.lib.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 사용자 ID로 조회
    Optional<User> findByUserId(String userId);
    
    // 이메일로 사용자 조회
    Optional<User> findByEmail(String email);
}
