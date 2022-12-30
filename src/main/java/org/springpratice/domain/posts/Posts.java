package org.springpratice.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springpratice.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter // get 메소드 자동 생성
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity // JPA의 어노테이션
// 테이블과 링크된 클래스
public class Posts extends BaseTimeEntity {
    @Id // pk필드를 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 생성규칙을 의미, auto_increment 의미
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "Text", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
