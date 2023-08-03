package likelion.univ.domain.service;

import likelion.univ.domain.entity.Example;
import likelion.univ.domain.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExampleService {
    private final ExampleRepository exampleRepository;

    @Transactional
    public Example createExample(String body){
        Example example = Example.builder()
                .body(body)
                .build();
        exampleRepository.save(example);
        return example;
    }
    @Transactional
    public void updateExample(Long id, String body){
        Example example = exampleRepository.findById(id).get();
        example.updateBody(body);
    }
}
