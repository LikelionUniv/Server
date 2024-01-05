package likelion.univ.domain.post.repository.impl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.univ.domain.post.dto.response.PostSimpleData;
import likelion.univ.domain.post.dto.response.QPostSimpleData;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import likelion.univ.domain.post.repository.PostCustomRepository;
import likelion.univ.domain.post.repository.impl.condition.PostSortType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static likelion.univ.domain.comment.entity.QComment.comment;
import static likelion.univ.domain.like.postlike.entity.QPostLike.postLike;
import static likelion.univ.domain.post.entity.QPost.post;
import static likelion.univ.domain.university.entity.QUniversity.university;
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
        List<Long> ids = getCoveringIndexByPostLike(postLike.user.id.eq(userId));
        return findByCoveringIndexOrderByCreatedDate(ids, pageable);
    }

    /* ----- 커뮤니티 ----- */
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
    public Page<Post> findByPostLikeAuthorId(Long userId, Pageable pageable, String sort, String search){
        List<Long> ids = getCoveringIndexByPostLike(postLike.user.id.eq(userId));
        return findByPostLikesWithSort(ids, pageable, PostSortType.toOrderSpecifier(sort), search);
    }

    @Override
    public Page<Post> findByCategoriesAndUniversityOrderByCreatedDate(
            MainCategory mainCategory, SubCategory subCategory, Long universityId, Pageable pageable) {

        List<Long> ids = queryFactory
                .select(post.id, post.createdDate,post.postLikes.size(), post.comments.size())
                .from(post)
                .join(post.author, user)
                .join(user.universityInfo.university, university)
                .leftJoin(post.postLikes, postLike)
                .leftJoin(post.comments, comment)
                .where(user.universityInfo.university.id.eq(universityId)
                        .and(post.mainCategory.eq(mainCategory))
                        .and(post.subCategory.eq(subCategory)))
                .groupBy(post)
                .orderBy(getOrderSpecifierByPageable(pageable))
                .fetch().stream().map(t -> t.get(post.id)).toList();


        List<Post> posts = queryFactory
                .select(post)
                .from(post)
                .leftJoin(post.author, user).fetchJoin()
                .where(post.id.in(ids))
                .distinct()
                .fetch();
        return new PageImpl<>(posts, pageable, ids.size());
    }

    @Override
    public Page<Post> findByCategoriesOrderByCommentCount(MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        List<Long> ids = getCoveringIndexByPost(post.mainCategory.eq(mainCategory)
                .and(post.subCategory.eq(subCategory)));
        return findByCoveringIndexOrderByCommentCount(ids, pageable);
    }

    @Override
    public Page<PostSimpleData> findByCategoriesAndSearchTitle(String searchTitle, MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        long totalSize = getSearchResultSize(searchTitle);

        List<PostSimpleData> posts = queryFactory
                .select(postSimpleData())
                .from(post)
                .join(post.author, user)
                .where(
                        post.title.containsIgnoreCase(searchTitle)
                    .and(post.mainCategory.eq(mainCategory)
                    .and(post.subCategory.eq(subCategory))))
                .offset(pageable.getOffset())
                .orderBy(post.createdDate.desc())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(posts, pageable, totalSize);
    }

    @Override
    public Page<PostSimpleData> findBySearchTitle(String searchTitle, Pageable pageable) {
        long totalSize = getSearchResultSize(searchTitle);

        List<PostSimpleData> posts = queryFactory
                .select(postSimpleData())
                .from(post)
                .join(post.author, user)
                .where(post.title.containsIgnoreCase(searchTitle))
                .offset(pageable.getOffset())
                .orderBy(post.createdDate.desc())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(posts, pageable, totalSize);
    }


    /* ----- 내부 메서드 ----- */
    private List<Long> getCoveringIndexByComment(Predicate predicate) {
        return queryFactory.select(comment.post.id).from(comment).where(predicate).fetch();
    }
    private List<Long> getCoveringIndexByPostLike(Predicate predicate) {
        return queryFactory.select(postLike.post.id).from(postLike).where(predicate).fetch();
    }

    private BooleanExpression searchCondition(String search) {
        return StringUtils.hasText(search) ? post.body.contains(search).or(post.title.contains(search)) : null;
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

    private  Page<Post> findByUniversityAndCoveringIndexOrderByCreatedDate(List<Long> ids, Pageable pageable,
                                                                           Long universityId){
        List<Post> posts =
                queryFactory
                        .select(post)
                        .from(post)
                        .innerJoin(post.author, user).fetchJoin()
                        .where(post.id.in(ids),
                                user.universityInfo.university.id.eq(universityId))
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

    private int getSearchResultSize(String searchTitle) {
        return queryFactory
                .select(post.id)
                .from(post)
                .where(post.title.containsIgnoreCase(searchTitle))
                .fetch().size();
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
                post.mainCategory,
                post.subCategory,
                post.author.profile.name,
                post.author.profile.profileImage,
                post.title,
                post.body,
                post.thumbnail,
                post.createdDate);
    }

    private  Page<Post> findByPostLikesWithSort(List<Long> ids, Pageable pageable, OrderSpecifier sort, String search){
        List<Post> posts =
                queryFactory
                        .selectDistinct(post)
                        .from(post)
                        .innerJoin(post.author, user).fetchJoin()
                        .leftJoin(post.postLikes, postLike)
                        .leftJoin(post.comments, comment)
                        .where(post.id.in(ids)
                                .and(searchCondition(search)))
                        .offset(pageable.getOffset())
                        .orderBy(sort)
                        .limit(pageable.getPageSize())
                        .fetch();

        return new PageImpl<>(posts, pageable, ids.size());
    }

    private OrderSpecifier[] getOrderSpecifierByPageable(Pageable pageable){
        OrderSpecifier[] orderSpecifiers = getOrdersBySort(post, pageable.getSort());
        return Arrays.stream(orderSpecifiers).map(orderSpecifier -> convertToPostOrderSpecifier(orderSpecifier))
                .collect(Collectors.toList()).toArray(new OrderSpecifier[0]);
    }
    private OrderSpecifier convertToPostOrderSpecifier(OrderSpecifier orderSpecifier){
        if(orderSpecifier.getTarget().equals(post.postLikes)){
            return post.postLikes.size().desc();
        }else if(orderSpecifier.getTarget().equals(post.comments)){
            return post.comments.size().desc();
        }
        return orderSpecifier;
    }
}
