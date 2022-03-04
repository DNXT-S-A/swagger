package pt.dnxt.demo.swagger.service;

import pt.dnxt.demo.swagger.dto.Response;

import java.util.List;
import java.util.UUID;

public interface ISwaggerService {

    //POST
    UUID createExample(String description);

    //PUT
    UUID updateExample(String description, UUID uuid);

    //GET
    Response getExample(UUID uuid);
    List<Response> getAllExamples();

    //DELETE
    void deleteExample(UUID uuid);
}
