package likelion.univ.domain.service;

<<<<<<<< HEAD:likelion-core/src/main/java/likelion/univ/domain/service/ExampleService.java
import likelion.univ.domain.entity.Example;
import likelion.univ.domain.repository.ExampleRepository;
========
import likelion.univ.domain.example.adaptor.ExampleAdaptor;
import likelion.univ.domain.example.entity.Example;
>>>>>>>> 891af271784ed3390a4a47abb6701bb961be436a:likelion-core/src/main/java/likelion/univ/domain/service/ExampleDomainService.java
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExampleDomainService {
    private final ExampleAdaptor exampleAdaptor;

    @Transactional
    public Example createExample(String body){
        Example example = Example.builder()
                .body(body)
                .build();
        exampleAdaptor.save(example);
        return example;
    }
    @Transactional
    public void editExample(Long id, String body){
        Example example = exampleAdaptor.findById(id);
        example.editBody(body);
    }

    @Transactional
    public void deleteExample(Long id){
        Example example = exampleAdaptor.findById(id);
        exampleAdaptor.delete(example);
    }
}
