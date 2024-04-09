package likelion.univ.domain.example.repository;

import likelion.univ.domain.example.entity.Example;
import likelion.univ.domain.example.exception.ExampleNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example, Long> {

    default Example getById(Long id) {
        return findById(id).orElseThrow(ExampleNotFoundException::new);
    }
}
