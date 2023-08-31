package likelion.univ.domain.community.post.service;

import likelion.univ.domain.community.post.adaptor.PostAdaptor;
import likelion.univ.domain.community.post.dto.PostServiceDTO;
import likelion.univ.domain.community.post.entity.Post;
import likelion.univ.domain.community.post.entity.enums.MainCategory;
import likelion.univ.domain.community.post.entity.enums.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService{

    @Autowired
    private PostAdaptor postAdaptor;


    public PostServiceDTO.CreateResponse createPost(PostServiceDTO.CreateRequest request) {
        Post post = createEntity(request);
        postAdaptor.save(post);
        return PostServiceDTO.CreateResponse.builder().id(post.getId()).build();
    }

    private static Post createEntity(PostServiceDTO.CreateRequest request) {
        return  Post.builder()
                .author(request.getUser())
                .title(request.getTitle())
                .body(request.getBody())
                .thumbnail(request.getThumbnail())
                .mainCategory(MainCategory.valueOf(request.getMainCategory()))
                .subCategory(SubCategory.valueOf(request.getSubCategory()))
                .build();
    }
}
