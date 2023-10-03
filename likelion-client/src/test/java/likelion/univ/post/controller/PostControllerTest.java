package likelion.univ.post.controller;

import likelion.univ.LikelionClientApplication;
import likelion.univ.TestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestConfiguration.class)
@TestPropertySource("classpath:application.yml")
class PostControllerTest {

    @Test
    void createPost() {
        
    }

    @Test
    void findAll() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }
}
