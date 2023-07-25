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
    public Example createExample(String column){
        Example example = Example.builder()
                .column(column)
                .build();
        exampleRepository.save(example);
        return example;
    }
    @Transactional
    public void updateExample(Long id, String column){
        Example example = exampleRepository.findById(id).get();
        example.updateColumn(column);
    }
}
