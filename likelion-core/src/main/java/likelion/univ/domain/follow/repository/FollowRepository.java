package likelion.univ.domain.follow.repository;

import likelion.univ.domain.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    Long countByFollowerId(Long followerId);
    Long countByFolloweeId(Long followeeId);
}
