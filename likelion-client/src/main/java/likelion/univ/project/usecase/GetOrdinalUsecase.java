package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetOrdinalUsecase {
    private final ProjectAdaptor projectAdaptor;

    public int excute() {
        return projectAdaptor.getCurrentOrdinal();
    }
}
