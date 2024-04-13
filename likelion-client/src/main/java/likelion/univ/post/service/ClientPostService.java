package likelion.univ.post.service;

import likelion.univ.domain.post.dto.request.CreatePostCommand;
import likelion.univ.domain.post.dto.request.DeletePostCommand;
import likelion.univ.domain.post.dto.request.UpdatePostCommand;
import likelion.univ.domain.post.service.PostDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ClientPostService {

    private final PostDomainService postDomainService;

    public Long create(CreatePostCommand command) {
        return postDomainService.createPost(command);
    }

    public Long update(UpdatePostCommand command) {
        return postDomainService.editPost(command);
    }

    public void delete(DeletePostCommand command) {
        postDomainService.deletePost(command);
    }
}
