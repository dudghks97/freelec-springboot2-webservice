package com.jojoldu.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// User 의 CRUD 담당
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);   // email 을 통해 기존 회원인지 최초 가입인지를 판단하기 위한 메소드
}
