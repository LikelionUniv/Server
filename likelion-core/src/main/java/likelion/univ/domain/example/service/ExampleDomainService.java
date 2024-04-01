package likelion.univ.domain.example.service;

import likelion.univ.domain.example.adaptor.ExampleAdaptor;
import likelion.univ.domain.example.entity.Example;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExampleDomainService {
    private final ExampleAdaptor exampleAdaptor;

    @Transactional
    public Example createExample(String body) {
        Example example = Example.builder()
                .body(body)
                .build();
        exampleAdaptor.save(example);
        return example;
    }

    @Transactional
    public void editExample(Long id, String body) {
        Example example = exampleAdaptor.findById(id);
        example.editBody(body);
    }

    @Transactional
    public void deleteExample(Long id) {
        Example example = exampleAdaptor.findById(id);
        exampleAdaptor.delete(example);
    }
}
