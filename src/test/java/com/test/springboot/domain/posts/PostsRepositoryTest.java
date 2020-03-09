package com.test.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup()
    {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기()
    {
        //given
        String title[]={"테스트1", "test2", "test3", "test3"};
        String content[]={"테스트 본문","test2","test3","test4"};

        for(int i=0;i<title.length;i++) {
            postsRepository.save(Posts.builder()
                    .title(title[i])
                    .content(content[i])
                    .author("jojoldu@gmail.com")
                    .build());
        }
        //when
        List<Posts> postsList=postsRepository.findAll();

        //then
        for(int i=0;i<postsList.size();i++) {
            Posts posts = postsList.get(i);
            assertThat(posts.getTitle()).isEqualTo(title[i]);
            assertThat(posts.getContent()).isEqualTo(content[i]);
        }
    }
}
