package org.springpratice.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postRepository;

    @After // Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
    // 배포전 전체 테스트를 수행할 때 테스트 간 데이터 침범을 막기 위해 사용
    public void cleanup() {
        postRepository.deleteAll();
    }

    @Test
    public void Content_SaveAndLoad() {
        // given
        String title = "test title";
        String content = "test content";

        postRepository.save(Posts.builder()
                        .title(title)
                        .content(content)
                        .author("test@gmail.com")
                .build());

        // when
        List<Posts> postsList = postRepository.findAll();
        // 테이블 posts에 있는 모든 데이터를 조회해 오는 메소드

        // then
        Posts post = postsList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_save() {
        // given
        LocalDateTime now = LocalDateTime.of(2022, 1, 1, 0, 0, 0);
        postRepository.save(Posts.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                .build());
        // when
        List<Posts> postsList = postRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>>>>>>>>>> createDate = " + posts.getCreatedDate() +
                " modifiedDate = " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
