package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 실제 DB의 에티블과 매칭될 클래스. 보통 Entity 클래스라고 함
// -> @Entity 어노테이션 사용. Entity 클래스는 절대로 Setter 를 만들지 않아야 함.
@Getter                 // 클래스 내 모든 필드의 Getter 메소드 자동 생성
@NoArgsConstructor      // 기본 생성자 자동 추가
@Entity                 // 테이블과 링크될 클래스임을 나타냄. 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭함
public class Posts extends BaseTimeEntity {
    @Id // 해당 테이블의 PK 필드임을 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // PK 의 생성 규칙 설정. IDENTITY -> auto_increment
    private long id;

    // 테이블의 칼럼을 나타냄. 굳이 선언하지 않아도 선언되기는 함. 기본값이 아니라 다른 값으로 설정하고 싶을 때 사용
    @Column(length=500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // constructor + insert
    @Builder    // 해당 클래스의 빌더 패턴 클래스 생성. 각 필드에 어떤 값을 채워야하는지 명확하게 인지가능
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // update
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
