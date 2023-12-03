package likelion.univ.domain.post.repository.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.univ.domain.post.dto.response.PostSimpleResponseDto;
import likelion.univ.domain.post.dto.response.QPostSimpleResponseDto;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import likelion.univ.domain.post.repository.PostCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static likelion.univ.domain.comment.entity.QComment.comment;
import static likelion.univ.domain.like.postlike.entity.QPostLike.postLike;
import static likelion.univ.domain.post.entity.QPost.post;
import static likelion.univ.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Post> findByCommentAuthorId(Long userId, Pageable pageable){
        List<Long> ids = getCoveringIndexByComment(comment.author.id.eq(userId)
                                                     .and(comment.isDeleted.isFalse()));
        return findByCoveringIndex(ids, pageable);
    }

    @Override
    public Page<Post> findByPostLikeAuthorId(Long userId, Pageable pageable){
        List<Long> ids = getCoveringIndexByPostLike(postLike.author.id.eq(userId));
        return findByCoveringIndex(ids, pageable);
    }

    private List<Long> getCoveringIndexByComment(Predicate predicate) {
        return queryFactory.select(comment.post.id).from(comment).where(predicate).fetch();
    }
    private List<Long> getCoveringIndexByPostLike(Predicate predicate) {
        return queryFactory.select(postLike.post.id).from(postLike).where(predicate).fetch();
    }
    private  Page<Post> findByCoveringIndex(List<Long> ids, Pageable pageable){
        List<Post> posts =
                queryFactory
                        .select(post)
                        .from(post)
                        .innerJoin(post.author, user).fetchJoin()
                        .where(post.id.in(ids))
                        .offset(pageable.getOffset())
                        .orderBy(post.createdDate.desc())
                        .limit(pageable.getPageSize())
                        .fetch();

        return new PageImpl<>(posts, pageable, ids.size());
    }

    @Override
    public List<PostSimpleResponseDto> findAllByCategories(MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        return queryFactory
                .select(postSimpleResponseDto())
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
    public List<PostSimpleResponseDto> findPostsByAuthorId(Long userId, Pageable pageable) {

        return queryFactory
                .select(postSimpleResponseDto())
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
    public List<PostSimpleResponseDto> findCommentedPosts(Long loginUserId, Pageable pageable) {

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
                .select(postSimpleResponseDto())
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
    public List<PostSimpleResponseDto> findLikedPosts(Long loginUserId, Pageable pageable) {

        List<Long> postIds = queryFactory
                .select(postLike.post.id)
                .from(postLike)
                .join(postLike.post, post)
                .where(
                        postLike.author.id.eq(loginUserId)
                )
                .fetch();
        return queryFactory
                .select(postSimpleResponseDto())
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

    private static QPostSimpleResponseDto postSimpleResponseDto() {
        return new QPostSimpleResponseDto(
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
