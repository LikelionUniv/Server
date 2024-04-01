package likelion.univ.domain.example.repository;

import likelion.univ.domain.example.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example, Long> {
}
