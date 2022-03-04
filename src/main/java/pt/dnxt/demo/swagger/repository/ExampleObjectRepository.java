package pt.dnxt.demo.swagger.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.dnxt.demo.swagger.model.ExampleObject;

import java.util.UUID;

@Repository
public interface ExampleObjectRepository extends CrudRepository<ExampleObject, Long> {
    ExampleObject findByUuid(UUID uuid);
}
