package likelion.univ.domain.graduation.service;

import likelion.univ.domain.graduation.entity.Graduation;
import likelion.univ.domain.user.entity.User;

import java.util.List;

public record GraduationsCreateCommand(
        List<Long> userId,
        Long ordinal
) {
    public Graduation toGraduations(User user) {
        return new Graduation(
                user,
                ordinal
        );
    }
}
