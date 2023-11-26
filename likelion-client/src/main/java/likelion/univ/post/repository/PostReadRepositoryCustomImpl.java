package likelion.univ.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.univ.domain.post.dto.response.PostDetailResponseDto;
import likelion.univ.domain.post.dto.response.QPostDetailResponseDto;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static likelion.univ.domain.comment.entity.QComment.comment;
import static likelion.univ.domain.like.postlike.entity.QPostLike.postLike;
import static likelion.univ.domain.post.entity.QPost.post;
import static likelion.univ.domain.user.entity.QUser.user;


@Repository
@RequiredArgsConstructor
public class PostReadRepositoryCustomImpl implements PostReadRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final AuthentiatedUserUtils userUtils;

    @Override
    public List<PostDetailResponseDto> findAll(MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        return queryFactory
                .select(postDetailResponseDto())
                .from(post)
                .join(post.author, user)
                .where(
                        post.mainCategory.eq(mainCategory),
                        post.subCategory.eq(subCategory)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.createdDate.desc())
                .fetch();
    }

    @Override
    public List<PostDetailResponseDto> findAuthorPosts(Long userId, Pageable pageable) {

        return queryFactory
                .select(postDetailResponseDto())
                .from(post)
                .join(post.author, user)
                .where(
                        post.author.id.eq(userId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.createdDate.desc())
                .fetch();
    }


    @Override
    public List<PostDetailResponseDto> findCommentedPosts(Pageable pageable) {
        Long loginUserId = userUtils.getCurrentUserId();

        List<Long> postIds = queryFactory
                .select(comment.post.id)
                .from(comment)
                .join(comment.post, post)
                .where(
                        comment.author.id.eq(loginUserId),
                        comment.isDeleted.isFalse()
                )
                .fetch();
        return queryFactory
                .select(postDetailResponseDto())
                .from(post)
                .innerJoin(post.author, user)
                .where(
                        post.id.in(postIds)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.createdDate.desc())
                .fetch();
    }

    @Override
    public List<PostDetailResponseDto> findLikedPosts(Pageable pageable) {
        Long loginUserId = userUtils.getCurrentUserId();

        List<Long> postIds = queryFactory
                .select(postLike.post.id)
                .from(postLike)
                .join(postLike.post, post)
                .where(
                        postLike.author.id.eq(loginUserId)
                )
                .fetch();
        return queryFactory
                .select(postDetailResponseDto())
                .from(post)
                .innerJoin(post.author, user)
                .where(
                        post.id.in(postIds)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.createdDate.desc())
                .fetch();
    }


    @NotNull
    private static QPostDetailResponseDto postDetailResponseDto() {
        return new QPostDetailResponseDto(
                post.id,
                post.author.id,
                post.author.profile.name,
                post.title,
                post.body,
                post.thumbnail,
                post.postLikes.size(),
                post.mainCategory,
                post.subCategory,
                post.createdDate,
                post.modifiedDate);
    }



}
