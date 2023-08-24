package likelion.univ.domain.community.post.service;


import likelion.univ.domain.dto.PostDto;
import likelion.univ.domain.dto.common.CommonResponseDto;

public interface PostService {
    public CommonResponseDto<Object> createPost(PostDto.Save createRequest , Long userId) ;

    public CommonResponseDto<Object> updatePost(PostDto.Update updateRequest, Long userId);

    public CommonResponseDto<Object> deletePost(PostDto.Delete deleteRequest, Long userId);
}
