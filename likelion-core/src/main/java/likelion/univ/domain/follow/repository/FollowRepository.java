package likelion.univ.domain.follow.repository;

import likelion.univ.domain.follow.entity.Follow;
import likelion.univ.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    Long countByFollowerId(Long followerId);
    Long countByFollowingId(Long followingId);
}
