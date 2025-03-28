package com.tracking.api.bi.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tracking.lib.domain.Users;

@Repository
public interface LoginRepository extends JpaRepository<Users, Long> {

    @Query("SELECT u FROM Users u WHERE u.userId = :userId AND u.userPw = :userPw")
    Optional<Users> findByUserIdAndPw(@Param("userId") String userId, @Param("userPw") String userPw);
}
