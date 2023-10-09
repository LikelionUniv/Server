package likelion.univ.domain.post.repository;

import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static likelion.univ.domain.post.entity.QPost.post;
import static likelion.univ.domain.user.entity.QUser.user;

@Repository
public class PostCommandCustomRepositoryImpl implements PostCommandCustomRepository {

}
