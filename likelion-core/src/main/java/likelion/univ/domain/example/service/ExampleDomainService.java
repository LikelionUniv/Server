package likelion.univ.domain.example.service;

import likelion.univ.domain.example.entity.Example;
import likelion.univ.domain.example.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExampleDomainService {

    private final ExampleRepository exampleRepository;

    @Transactional
    public Example createExample(String body) {
        Example example = Example.builder()
                .body(body)
                .build();
        exampleRepository.save(example);
        return example;
    }

    @Transactional
    public void editExample(Long id, String body) {
        Example example = exampleRepository.getById(id);
        example.editBody(body);
    }

    @Transactional
    public void deleteExample(Long id) {
        Example example = exampleRepository.getById(id);
        exampleRepository.delete(example);
    }
}
