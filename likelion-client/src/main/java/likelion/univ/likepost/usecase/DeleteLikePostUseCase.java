package likelion.univ.likepost.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.likepost.dto.LikePostRequestDto;
import likelion.univ.domain.likepost.dto.LikePostDto;
import likelion.univ.domain.likepost.service.LikePostDomainService;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
@RequiredArgsConstructor
public class DeleteLikePostUseCase {

    @Autowired
    private LikePostDomainService likePostDomainService;

    @Autowired
    private UserAdaptor userAdaptor;

    @Autowired
    private PostAdaptor postAdaptor;

    public void execute(LikePostRequestDto.Delete request) {
        //user

        likePostDomainService.deleteLikePost(buildDTO(request));

    }

    private LikePostDto.DeleteRequest buildDTO(LikePostRequestDto.Delete request) {
        return LikePostDto.DeleteRequest.builder()
                .post(postAdaptor.findById(request.getPostId()))
                .author(userAdaptor.findById(request.getUserId()))
                .build();
    }
}
