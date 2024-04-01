package likelion.univ.domain.project.repository.impl;

import static likelion.univ.domain.project.entity.QProject.project;
import static likelion.univ.domain.project.entity.QProjectImage.projectImage;
import static likelion.univ.domain.project.entity.QProjectMember.projectMember;
import static likelion.univ.domain.university.entity.QUniversity.university;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.repository.ProjectCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


@RequiredArgsConstructor
public class ProjectCustomRepositoryImpl implements ProjectCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Project> findByProjectMember(Long userId, Pageable pageable) {
        List<Long> ids = getCoveringIndexByProjectMember(projectMember.user.id.eq(userId));
        return queryBasicByCoveringIndex(ids, pageable);
    }

    private List<Long> getCoveringIndexByProjectMember(Predicate predicate) {
        return queryFactory.select(projectMember.project.id).from(projectMember).where(predicate).fetch();
    }

    private Page<Project> queryBasicByCoveringIndex(List<Long> ids, Pageable pageable) {
        List<Project> projects =
                queryFactory
                        .selectDistinct(project)
                        .from(project)
                        .innerJoin(project.projectImages, projectImage).fetchJoin()
                        .innerJoin(project.univ, university).fetchJoin()
                        .where(project.id.in(ids))
                        .offset(pageable.getOffset())
                        .orderBy(project.createdDate.desc())
                        .limit(pageable.getPageSize())
                        .fetch();

        return new PageImpl<>(projects, pageable, ids.size());
    }
}
