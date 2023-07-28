package likelion.univ.service;


import likelion.univ.domain.dto.PostDto;

public interface PostService {
    public PostDto.Save createPost(PostDto.Save createRequest , Long userId);

    public void updatePost(PostDto.Update updateRequest, Long userId);

    public void deletePost(PostDto.Delete deleteRequest, Long userId);
}
