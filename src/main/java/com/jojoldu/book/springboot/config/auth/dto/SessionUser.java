package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;
// Session 유저가 필요한 이유 : User 클래스를 세션에 저장하려고하면, User 클래스가 직렬화 되지 않았다는 에러가 발생함
// -> User 클래스는 엔티티이기 때문에 이렇게 직렬화 기능을 가진 세션 Dto 를 하나 만드는게 더 좋음
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
