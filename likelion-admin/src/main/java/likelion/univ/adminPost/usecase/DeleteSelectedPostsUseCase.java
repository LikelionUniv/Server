package likelion.univ.adminPost.usecase;

import java.util.List;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteSelectedPostsUseCase {

    private final PostRepository postRepository;

    public void execute(List<Long> selectedIds) {
        postRepository.deleteAllByIdInBatch(selectedIds);
    }
}
