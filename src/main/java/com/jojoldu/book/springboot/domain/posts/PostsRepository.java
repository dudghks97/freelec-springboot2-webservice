package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// DB Layer 접근자. MyBatis 등에서 Dao 라고 불림
// Entity 클래스와 함께 위치해야함 - 도메인 패키지에서 함께 관리
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
