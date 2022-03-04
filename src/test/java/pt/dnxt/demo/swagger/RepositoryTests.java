package pt.dnxt.demo.swagger;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.dnxt.demo.swagger.model.ExampleObject;
import pt.dnxt.demo.swagger.repository.ExampleObjectRepository;

import java.util.List;

@DataJpaTest
class RepositoryTests {

    @Autowired
    private ExampleObjectRepository exampleObjectRepository;

    @Test
    public void callSave_thenCallFindAll_thenAssertTheResultIsNotNullOrEmpty(){
        exampleObjectRepository.save(new ExampleObject("Test"));
        List<ExampleObject> users = (List<ExampleObject>) this.exampleObjectRepository.findAll();

        assertNotNull(users.get(0));
    }
}
