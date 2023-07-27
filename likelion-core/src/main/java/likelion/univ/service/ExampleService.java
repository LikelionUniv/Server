package likelion.univ.service;

import likelion.univ.domain.entity.Example;
import likelion.univ.domain.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class ExampleService {
    private final ExampleRepository exampleRepository;

    public Example createExample(String column){
        Example example = Example.builder()
                .column(column)
                .build();
        exampleRepository.save(example);
        return example;
    }
    public void updateExample(Long id, String column){
        Example example = exampleRepository.findById(id).get();
        example.updateColumn(column);
    }
}
