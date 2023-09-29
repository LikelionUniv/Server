package likelion.univ.domain.post.repository;

import likelion.univ.domain.post.entity.Post;

import java.util.List;

public interface PostCustomRepository {

    public List<Post> pagingWithCoveringIndex(Integer page, Integer limit);
}
