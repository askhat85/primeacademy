package com.prime.intern.demos.repository;

import com.prime.intern.demos.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    public Optional<UserInfo> findByLogin(String login);
}
