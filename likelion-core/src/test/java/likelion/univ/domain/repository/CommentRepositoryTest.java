package likelion.univ.domain.repository;

import likelion.univ.LikelionCoreApplication;
import likelion.univ.domain.community.comment.repository.CommentRepository;
import org.assertj.core.internal.Classes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@ContextConfiguration(classes = LikelionCoreApplication.class)
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("테스트")
    void findAllByPost() {

    }
}
