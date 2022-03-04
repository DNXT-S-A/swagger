package pt.dnxt.demo.swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.dnxt.demo.swagger.dto.Response;
import pt.dnxt.demo.swagger.model.ExampleObject;
import pt.dnxt.demo.swagger.repository.ExampleObjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SwaggerService implements ISwaggerService{

    @Autowired
    private ExampleObjectRepository exampleObjectRepository;

    @Override
    public UUID createExample(String description) {
        return this.exampleObjectRepository.save(new ExampleObject(description)).getUuid();
    }

    @Override
    public UUID updateExample(String description, UUID uuid) {
        ExampleObject exampleObject = this.exampleObjectRepository.findByUuid(uuid);

        if(exampleObject == null){
            throw new NullPointerException("example.update.error.exampleNotFound");
        }

        exampleObject.updateDescription(description);

        return this.exampleObjectRepository.save(exampleObject).getUuid();
    }

    @Override
    public Response getExample(UUID uuid) {
        ExampleObject exampleObject = this.exampleObjectRepository.findByUuid(uuid);

        if(exampleObject == null){
            throw new NullPointerException("example.get.error.exampleNotFound");
        }

        exampleObject.updateUuid();
        this.exampleObjectRepository.save(exampleObject);

        return new Response(exampleObject.getUuid(), exampleObject.getDescription());
    }

    @Override
    public List<Response> getAllExamples() {
        List<Response> outputList = new ArrayList<>();
        this.exampleObjectRepository.findAll().forEach(element -> {
            outputList.add(new Response(element.getUuid(), element.getDescription()));
        });

        return outputList;
    }

    @Override
    public void deleteExample(UUID uuid) {
        ExampleObject exampleObject = this.exampleObjectRepository.findByUuid(uuid);

        if(exampleObject == null){
            throw new NullPointerException("example.delete.error.exampleNotFound");
        }

        this.exampleObjectRepository.delete(exampleObject);
    }
}
