package likelion.univ.domain.postlike.service;

import likelion.univ.domain.postlike.adaptor.PostLikeAdaptor;
import likelion.univ.domain.postlike.dto.PostLikeCreateServiceDto;
import likelion.univ.domain.postlike.dto.PostLikeDeleteServiceDto;
import likelion.univ.domain.postlike.dto.PostLikeResponseDto;
import likelion.univ.domain.postlike.entity.PostLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeDomainService {

    private final PostLikeAdaptor postLikeAdaptor;

    public PostLikeResponseDto createLikePost(PostLikeCreateServiceDto request) {
        Long savedLikeId = postLikeAdaptor.save(PostLike.of(request));

        return PostLikeResponseDto.builder()
                .id(savedLikeId)
                .build();
    }

    public void deleteLikePost(PostLikeDeleteServiceDto request) {
        PostLike postLike = postLikeAdaptor.find(request.getPost(), request.getAuthor());
        postLikeAdaptor.delete(postLike);
    }
}
