package likelion.univ.domain.post.repository.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.univ.domain.post.dto.response.PostSimpleData;
import likelion.univ.domain.post.dto.response.QPostSimpleData;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
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

    /* ----- 마이 페이지 ----- */
    @Override
    public Page<Post> findByCommentAuthorId(Long userId, Pageable pageable){
        List<Long> ids = getCoveringIndexByComment(comment.author.id.eq(userId)
                                                     .and(comment.isDeleted.isFalse()));
        return findByCoveringIndexOrderByCreatedDate(ids, pageable);
    }
    @Override
    public Page<Post> findByPostLikeAuthorId(Long userId, Pageable pageable){
        List<Long> ids = getCoveringIndexByPostLike(postLike.author.id.eq(userId));
        return findByCoveringIndexOrderByCreatedDate(ids, pageable);
    }

    @Override
    public Page<Post> findByCategoriesOrderByCreatedDate(MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        List<Long> ids = getCoveringIndexByPost(post.mainCategory.eq(mainCategory)
                                                    .and(post.subCategory.eq(subCategory)));
        return findByCoveringIndexOrderByCreatedDate(ids, pageable);
    }

    @Override
    public Page<Post> findByCategoriesOrderByLikeCount(MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        List<Long> ids = getCoveringIndexByPost(post.mainCategory.eq(mainCategory)
                .and(post.subCategory.eq(subCategory)));
        return findByCoveringIndexOrderByLikeCount(ids, pageable);
    }

    @Override
    public Page<Post> findByCategoriesOrderByCommentCount(MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        List<Long> ids = getCoveringIndexByPost(post.mainCategory.eq(mainCategory)
                .and(post.subCategory.eq(subCategory)));
        return findByCoveringIndexOrderByCommentCount(ids, pageable);
    }


    /* ----- 내부 메서드 ----- */
    private List<Long> getCoveringIndexByComment(Predicate predicate) {
        return queryFactory.select(comment.post.id).from(comment).where(predicate).fetch();
    }
    private List<Long> getCoveringIndexByPostLike(Predicate predicate) {
        return queryFactory.select(postLike.post.id).from(postLike).where(predicate).fetch();
    }

    private List<Long> getCoveringIndexByPost(Predicate predicate) {
        return queryFactory
                .select(post.id)
                .from(post)
                .where(predicate)
                .fetch();
    }

    private  Page<Post> findByCoveringIndexOrderByCreatedDate(List<Long> ids, Pageable pageable){
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

    private Page<Post> findByCoveringIndexOrderByLikeCount(List<Long> ids, Pageable pageable) {
        List<Post> posts = queryFactory
                .selectFrom(post)
                .join(post.author, user).fetchJoin()
                .where(post.id.in(ids))
                .offset(pageable.getOffset())
                .orderBy(post.postLikes.size().desc())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(posts, pageable, ids.size());
    }
    private Page<Post> findByCoveringIndexOrderByCommentCount(List<Long> ids, Pageable pageable) {
        List<Post> posts = queryFactory
                .selectFrom(post)
                .join(post.author, user).fetchJoin()
                .where(post.id.in(ids))
                .offset(pageable.getOffset())
                .orderBy(post.comments.size().desc())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(posts, pageable, ids.size());
    }

//    @Override
//    public Page<PostSimpleData> findByCategoriesOrderByCreatedDate(MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
//        List<PostSimpleData> posts = queryFactory
//                .select(postSimpleData())
//                .from(post)
//                .join(post.author, user)
//                .where(
//                        post.mainCategory.eq(mainCategory),
//                        post.subCategory.eq(subCategory)
//                )
//                .offset(pageable.getOffset())
//                .orderBy(post.createdDate.desc())
//                .limit(pageable.getPageSize())
//                .fetch();
//        return new PageImpl<>(posts, pageable, posts.size());
//    }

//    @Override
//    public Page<PostSimpleData> findByCategoriesOrderByLikeCount(MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
//        List<PostSimpleData> posts = queryFactory
//                .select(postSimpleData())
//                .from(post)
//                .join(post.author, user)
//                .where(
//                        post.mainCategory.eq(mainCategory),
//                        post.subCategory.eq(subCategory)
//                )
//                .offset(pageable.getOffset())
//                .orderBy(post.postLikes.size().desc())
//                .limit(pageable.getPageSize())
//                .fetch();
//        return new PageImpl<>(posts, pageable, posts.size());
//    }
//
//    @Override
//    public Page<PostSimpleData> findByCategoriesOrderByCommentCount(MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
//        List<PostSimpleData> posts = queryFactory
//                .select(postSimpleData())
//                .from(post)
//                .join(post.author, user)
//                .where(
//                        post.mainCategory.eq(mainCategory),
//                        post.subCategory.eq(subCategory)
//                )
//                .offset(pageable.getOffset())
//                .orderBy(post.comments.size().desc())
//                .limit(pageable.getPageSize())
//                .fetch();
//        return new PageImpl<>(posts, pageable, posts.size());
//    }

    private static QPostSimpleData postSimpleData() {
        return new QPostSimpleData(
                post.id,
                post.author.id,
                post.author.profile.name,
                post.author.profile.profileImage,
                post.title,
                post.body,
                post.thumbnail,
                post.createdDate);
    }

}
