package likelion.univ.domain.community.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.univ.domain.community.post.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostCustomRepository {

    public List<Post> pagingWithCoveringIndex(Integer page, Integer limit);
}
