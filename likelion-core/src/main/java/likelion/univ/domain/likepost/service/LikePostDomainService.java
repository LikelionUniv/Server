package likelion.univ.domain.likepost.service;

import likelion.univ.domain.likepost.adaptor.LikePostAdaptor;
import likelion.univ.domain.likepost.dto.LikePostDto;
import likelion.univ.domain.likepost.entity.LikePost;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikePostDomainService {

    @Autowired
    private LikePostAdaptor likePostAdaptor;

    public LikePostDto.ResponseDTO createLikePost(LikePostDto.CreateRequest request) {
        LikePost likePost = createEntity(request);
        likePostAdaptor.save(likePost);
        return LikePostDto.ResponseDTO.builder().id(likePost.getLikeId()).build();
    }

    public void deleteLikePost(LikePostDto.DeleteRequest request) {
        LikePost likePost = likePostAdaptor.find(request.getPost(), request.getAuthor());
        likePostAdaptor.delete(likePost);
    }
    private static LikePost createEntity(LikePostDto.CreateRequest request) {
        return LikePost.builder()
                .post(request.getPost())
                .author(request.getAuthor())
                .build();
    }

}
