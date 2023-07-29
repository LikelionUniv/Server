package likelion.univ.service;

import likelion.univ.domain.dto.PostDto;
import likelion.univ.domain.entity.Post;
import likelion.univ.domain.entity.enums.MainCategory;
import likelion.univ.domain.entity.enums.SubCategory;
import likelion.univ.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Override
    public PostDto.Save createPost(PostDto.Save createRequest, Long userId) {

        //User 찾아오는 로직 추가 필요


        Post post = Post.builder()
                .title(createRequest.getTitle())
                .body(createRequest.getBody())
                .thumbnail(createRequest.getThumbnail())
                .mainCategory(MainCategory.valueOf(createRequest.getMainCategory()))
                .subCategory(SubCategory.valueOf(createRequest.getSubCategory()))
                .build();
        postRepository.save(post);

        return new PostDto.Save(post.getTitle(), post.getBody(), post.getThumbnail(), post.getMainCategory().toString(), post.getSubCategory().toString());
    }

    @Override
    public void updatePost(PostDto.Update updateRequest, Long userId) {

        Post post = postRepository.findById(updateRequest.getPostId()).get();   //예외처리: 포스트가 없을 때

        post.update(updateRequest); //예외처리: 포스트 소유자가 아닐 때
        postRepository.save(post);

    }

    @Override
    public void deletePost(PostDto.Delete deleteRequest, Long userId) {

        Post post = postRepository.findById(deleteRequest.getPostId()).get();   //예외처리: 포스트가 없을 때
        postRepository.delete(post);//예외처리: 포스트 소유자가 아닐 때
    }
}
