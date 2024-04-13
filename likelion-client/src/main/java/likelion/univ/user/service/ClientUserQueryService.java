package likelion.univ.user.service;

import java.util.List;
import java.util.Optional;
import likelion.univ.common.response.PageResponse;
import likelion.univ.common.response.SliceResponse;
import likelion.univ.domain.follow.repository.FollowRepository;
import likelion.univ.domain.like.postlike.repository.PostLikeRepository;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.repository.PostRepository;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.repository.ProjectRepository;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import likelion.univ.follow.dao.FollowNumRedisDao;
import likelion.univ.follow.entity.FollowNum;
import likelion.univ.follow.service.FollowNumRedisService;
import likelion.univ.post.processor.GetOrCreatePostCountInfoProcessor;
import likelion.univ.user.dto.response.FollowUserInfoDto;
import likelion.univ.user.dto.response.ProfileDetailsDto;
import likelion.univ.user.dto.response.UserPagePostsDto;
import likelion.univ.user.dto.response.UserPageProjectsDto;
import likelion.univ.user.dto.response.UserSearchResultDto;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ClientUserQueryService {

    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final GetOrCreatePostCountInfoProcessor getOrCreatePostCountInfoProcessor;
    private final PostLikeRepository postLikeRepository;
    private final FollowNumRedisDao followNumRedisDao;
    private final FollowNumRedisService followNumRedisService;
    private final FollowRepository followRepository;
    private final ProjectRepository projectRepository;

    public SliceResponse<FollowUserInfoDto> getFollowInfoForFollowing(Long userId, Pageable pageable) {
        Slice<User> userSlice = userRepository.findFollowingUsersByFollowerId(userId, pageable);
        List<User> myFollowingUsers = getMyFollowingUsers(userSlice);
        return SliceResponse.of(userSlice.map(u ->
                FollowUserInfoDto.of(u, checkIFollowingTarget(u, myFollowingUsers)))
        );
    }

    public SliceResponse<FollowUserInfoDto> getFollowInfoForFollower(Long userId, Pageable pageable) {
        Slice<User> userSlice = userRepository.findFollowerUsersByFollowingId(userId, pageable);
        List<User> myFollowingUsers = getMyFollowingUsers(userSlice);
        return SliceResponse.of(userSlice.map(u ->
                FollowUserInfoDto.of(u, checkIFollowingTarget(u, myFollowingUsers)))
        );
    }

    private List<User> getMyFollowingUsers(Slice<User> userSlice) {
        Long currentUserId = authenticatedUserUtils.getCurrentUserId();
        List<Long> followingUserIds = userSlice.getContent()
                .stream()
                .map(User::getId)
                .toList();
        return userRepository.findMyFollowingUsersByFollowingIdIn(currentUserId, followingUserIds);
    }

    private Boolean checkIFollowingTarget(User target, List<User> myFollowingUsers) {
        return myFollowingUsers.stream()
                .anyMatch(u -> u.getId().equals(target.getId()));
    }

    public PageResponse<UserPagePostsDto> getPostsCommentedByMe(Long userId, Pageable pageable) {
        Long currentUserId = authenticatedUserUtils.getCurrentUserId();
        Page<Post> posts = postRepository.findByCommentAuthorId(userId, pageable);
        List<Long> postIds = posts.get()
                .map(Post::getId)
                .toList();
        List<Long> myLikedPostIds = postLikeRepository.findPostIdsByUserIdAndPostIdsIn(currentUserId, postIds);
        return PageResponse.of(posts.map(p -> UserPagePostsDto.of(
                p,
                currentUserId,
                getOrCreatePostCountInfoProcessor.execute(p.getId()),
                myLikedPostIds.contains(p.getId()))
        ));
    }

    public ProfileDetailsDto getProfile(Long userId) {
        Long currentUserId = authenticatedUserUtils.getCurrentUserId();
        User user = userRepository.getByIdWithUniversity(userId);
        FollowNum followNum = getUserFollowNum(user.getId());
        if (user.getId().equals(currentUserId)) {
            return ProfileDetailsDto.of(user, true, followNum.getFollowerNum(), followNum.getFollowingNum());
        } else {
            return ProfileDetailsDto.of(user, false, followNum.getFollowerNum(), followNum.getFollowingNum());
        }
    }

    private FollowNum getUserFollowNum(Long userId) {
        Optional<FollowNum> userFollowNum = followNumRedisDao.findById(userId);
        if (userFollowNum.isEmpty()) {
            Long followerNum = followRepository.countByFollowingId(userId);
            Long followingNum = followRepository.countByFollowerId(userId);
            return followNumRedisService.save(userId, followerNum, followingNum);
        }
        return userFollowNum.get();
    }

    public PageResponse<UserPagePostsDto> getUserLikedPosts(
            Long userId,
            Pageable pageable,
            String sort,
            String search
    ) {
        Long currentUserId = authenticatedUserUtils.getCurrentUserId();
        Page<Post> posts = postRepository.findByPostLikeAuthorId(userId, pageable, sort, search);
        List<Long> postIds = posts.get()
                .map(Post::getId)
                .toList();
        List<Long> myLikedPostIds = postLikeRepository.findPostIdsByUserIdAndPostIdsIn(currentUserId, postIds);
        return PageResponse.of(posts.map(p -> UserPagePostsDto.of(p, currentUserId,
                getOrCreatePostCountInfoProcessor.execute(p.getId()),
                myLikedPostIds.contains(p.getId()))));
    }

    public PageResponse<UserPagePostsDto> getUserPosts(Long userId, Pageable pageable) {
        Long currentUserId = authenticatedUserUtils.getCurrentUserId();
        Page<Post> posts = postRepository.findAllByAuthorId(userId, pageable);
        List<Long> postIds = posts.get()
                .map(Post::getId)
                .toList();
        List<Long> myLikedPostIds = postLikeRepository.findPostIdsByUserIdAndPostIdsIn(currentUserId, postIds);
        return PageResponse.of(posts.map(p -> UserPagePostsDto.of(p, currentUserId,
                getOrCreatePostCountInfoProcessor.execute(p.getId()),
                myLikedPostIds.contains(p.getId()))));
    }

    public PageResponse<UserPageProjectsDto> getUserProjects(Long userId, Pageable pageable) {
        Page<Project> projects = projectRepository.findByProjectMember(userId, pageable);
        return PageResponse.of(projects.map(UserPageProjectsDto::of));
    }

    public SliceResponse searchUserByName(String name, Pageable pageable) {
        Slice<User> userSlice = userRepository.searchByName(name, pageable);
        return SliceResponse.of(userSlice.map(UserSearchResultDto::of));
    }
}
