package likelion.univ.domain.community.post.service;

import likelion.univ.domain.community.post.adaptor.PostAdaptor;
import likelion.univ.domain.community.post.dto.PostServiceDTO;
import likelion.univ.domain.community.post.entity.Post;
import likelion.univ.domain.community.post.entity.enums.MainCategory;
import likelion.univ.domain.community.post.entity.enums.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService{

    @Autowired
    private PostAdaptor postAdaptor;


    public PostServiceDTO.ResponseDTO createPost(PostServiceDTO.CreateRequest request) {
        Post post = createEntity(request);
        postAdaptor.save(post);
        return PostServiceDTO.ResponseDTO.builder().id(post.getId()).build();
    }

//    public List<PostServiceDTO.Retrieve> retrievePostPaging(Integer page, Integer limit) {
//        List<Post> posts= postAdaptor.retrievePostPaging(page,limit);
//        return posts.stream()
//                .map(post -> PostServiceDTO.Retrieve.builder()
//                        .id(post.getId())
//                        .authorId(post.getAuthor().getId())
//                        .author(post.getAuthor().getProfile().getName())
//                        .title(post.getTitle())
//                        .body(post.getBody())
//                        .thumbnail(post.getThumbnail())
//                        .mainCategory(post.getMainCategory())
//                        .subCategory(post.getSubCategory())
//                        .build())
//                .collect(Collectors.toList());
//    }

    public PostServiceDTO.ResponseDTO editPost(PostServiceDTO.EditRequest request) {
        Post post = postAdaptor.findById(request.getPostId());
        post.edit(request);
        postAdaptor.save(post);
        return PostServiceDTO.ResponseDTO.builder().id(post.getId()).build();
    }
    public void deletePost(PostServiceDTO.DeleteRequest request) {
        Post post = postAdaptor.findById(request.getPostId());
        postAdaptor.delete(post);
        return;
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
