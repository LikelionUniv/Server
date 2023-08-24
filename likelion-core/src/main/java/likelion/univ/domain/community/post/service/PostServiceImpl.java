package likelion.univ.domain.community.post.service;

import likelion.univ.domain.dto.PostDto;
import likelion.univ.domain.dto.common.CommonResponseDto;
import likelion.univ.domain.community.post.entity.Post;
import likelion.univ.domain.entity.User;
import likelion.univ.domain.community.post.entity.enums.MainCategory;
import likelion.univ.domain.community.post.entity.enums.SubCategory;
import likelion.univ.domain.community.post.repository.PostRepository;
import likelion.univ.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private  UserRepository userRepository;
    @Override
    public CommonResponseDto<Object> createPost(PostDto.Save createRequest, Long userId) {

        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("유저가 존재하지 않습니다."));

            Post savePost = postRepository.save(createEntity(createRequest, user));

            return ResposeGenerate(savePost.getId(),"게시물 생성 성공");
        } catch (NoSuchElementException e) {
            return ErrorGenerate( e.getMessage());
        }
    }

    @Override
    public CommonResponseDto<Object> updatePost(PostDto.Update updateRequest, Long userId) {

        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("유저가 존재하지 않습니다."));
            Post findPost = postRepository.findById(updateRequest.getPostId()).orElseThrow(()-> new NoSuchElementException("게시글이 존재하지 않습니다."));
            if (user.getId().equals(findPost.getId())){
                findPost.update(updateRequest);
                postRepository.save(findPost);
                return ResposeGenerate(findPost.getId(),"게시물 수정 성공");
            }
        } catch (NoSuchElementException e) {
            return ErrorGenerate( e.getMessage());
        }

        return ErrorGenerate("본인 소유 게시물이 아닙니다.");
    }

    @Override
    public CommonResponseDto<Object> deletePost(PostDto.Delete deleteRequest, Long userId) {

        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("유저가 존재하지 않습니다."));
            Post findPost = postRepository.findById(deleteRequest.getPostId()).orElseThrow(()-> new NoSuchElementException("게시글이 존재하지 않습니다."));
            if (user.getId().equals(findPost.getId())){
                postRepository.delete(findPost);
                return NoDataResponseGenerate("게시물 삭제 성공");
            }
        } catch (NoSuchElementException e) {
            return ErrorGenerate( e.getMessage());
        }
        return ErrorGenerate("본인 소유 게시물이 아닙니다.");

    }


    private static CommonResponseDto<Object> NoDataResponseGenerate(String message) {
        return CommonResponseDto.builder()
                .message(message)
                .build();
    }

    private static CommonResponseDto<Object> ResposeGenerate(Object data, String message) {
        return CommonResponseDto.builder()
                .data(data)
                .message(message)
                .build();
    }

    private static CommonResponseDto<Object> ErrorGenerate(String message) {
        return CommonResponseDto.builder()
                .message(message)
                .build();
    }



    private static Post createEntity(PostDto.Save createRequest, User user) {
        return  Post.builder()
                .author(user)
                .title(createRequest.getTitle())
                .body(createRequest.getBody())
                .thumbnail(createRequest.getThumbnail())
                .mainCategory(MainCategory.valueOf(createRequest.getMainCategory()))
                .subCategory(SubCategory.valueOf(createRequest.getSubCategory()))
                .build();
    }
}
