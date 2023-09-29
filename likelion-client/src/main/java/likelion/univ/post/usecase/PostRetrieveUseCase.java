package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
@RequiredArgsConstructor
public class PostRetrieveUseCase {

    @Autowired
    private PostService postService;

//    public List<PostServiceDTO.Retrieve> execute(Integer page, Integer limit) {
//        List<PostServiceDTO.Retrieve> response = postService.retrievePostPaging(page,limit);
//        return response;
//    }
}
