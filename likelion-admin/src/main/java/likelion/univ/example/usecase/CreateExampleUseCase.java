package likelion.univ.example.usecase;

import likelion.univ.domain.example.adapter.ExampleAdapter;
import likelion.univ.domain.example.service.ExampleDomainService;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class CreateExampleUseCase {
    private final ExampleAdapter exampleAdapter;
    private final ExampleDomainService exampleDomainService;

    public void excute(String body){
        exampleDomainService.createExample(body);
    }
}
