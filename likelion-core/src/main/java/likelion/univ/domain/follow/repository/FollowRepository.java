package likelion.univ.domain.follow.repository;

import likelion.univ.domain.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface FollowRepository extends JpaRepository<Follow,Long> {
    Long countByFollowerId(Long followerId);
    Long countByFollowingId(Long followingId);
    @Modifying
    @Transactional
    @Query(value = "insert into follow(created_date, modified_date, follower_id, following_id)"
            + "values (now(), now(), :currentId , :userId)", nativeQuery = true)
    void save(Long currentId, Long userId);

    @Modifying
    @Transactional
    @Query("delete from Follow f where f.follower.id = :currentId and f.following.id = :userId")
    void delete(Long currentId, Long userId);
}
