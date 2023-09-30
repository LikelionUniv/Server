package likelion.univ.domain.likepost.service;

import likelion.univ.domain.likepost.adaptor.LikePostAdaptor;
import likelion.univ.domain.likepost.dto.LikePostCreateRequestDto;
import likelion.univ.domain.likepost.dto.LikePostDeleteRequestDto;
import likelion.univ.domain.likepost.dto.LikePostResponseDto;
import likelion.univ.domain.likepost.entity.LikePost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikePostDomainService {

    private final LikePostAdaptor likePostAdaptor;

    public LikePostResponseDto createLikePost(LikePostCreateRequestDto request) {
        Long savedLikeId = likePostAdaptor.save(LikePost.of(request));

        return LikePostResponseDto.builder()
                .id(savedLikeId)
                .build();
    }

    public void deleteLikePost(LikePostDeleteRequestDto request) {
        LikePost likePost = likePostAdaptor.find(request.getPost(), request.getAuthor());
        likePostAdaptor.delete(likePost);
    }
}
