package likelion.univ.adminPost.usecase;

import likelion.univ.adminPost.dto.response.PostInfoResponseDto;
import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.like.postlike.adaptor.PostLikeAdaptor;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.dto.request.DeletePostCommand;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.service.UserDomainService;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class UnivDeletePostUseCase {

    private final PostDomainService postDomainService;
    private final PostAdaptor postAdaptor;

    public PostInfoResponseDto execute(Long postId){

        Post post= postAdaptor.findById(postId);

        DeletePostCommand deletePostCommand = DeletePostCommand.builder()
                .postId(postId)
                .build();

        postDomainService.deletePost(deletePostCommand);

        return PostInfoResponseDto.of(post);
    }


}

