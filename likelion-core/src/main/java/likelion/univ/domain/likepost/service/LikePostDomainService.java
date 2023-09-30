package likelion.univ.domain.likepost.service;

import likelion.univ.domain.likepost.adaptor.LikePostAdaptor;
import likelion.univ.domain.likepost.dto.LikePostCreateServiceDto;
import likelion.univ.domain.likepost.dto.LikePostDeleteServiceDto;
import likelion.univ.domain.likepost.dto.LikePostResponseDto;
import likelion.univ.domain.likepost.entity.LikePost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikePostDomainService {

    private final LikePostAdaptor likePostAdaptor;

    public LikePostResponseDto createLikePost(LikePostCreateServiceDto request) {
        Long savedLikeId = likePostAdaptor.save(LikePost.of(request));

        return LikePostResponseDto.builder()
                .id(savedLikeId)
                .build();
    }

    public void deleteLikePost(LikePostDeleteServiceDto request) {
        LikePost likePost = likePostAdaptor.find(request.getPost(), request.getAuthor());
        likePostAdaptor.delete(likePost);
    }
}
