package com.tracking.api.bi.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tracking.lib.domain.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    // 사용자 ID로 조회
    Optional<Users> findByUserId(String userId);
    
    // 이메일로 사용자 조회
    Optional<Users> findByEmail(String email);
    
}
