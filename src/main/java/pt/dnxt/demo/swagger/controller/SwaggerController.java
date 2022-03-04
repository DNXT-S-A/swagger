package pt.dnxt.demo.swagger.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.dnxt.demo.swagger.dto.Body;
import pt.dnxt.demo.swagger.dto.ErrorReasonBodyDTO;
import pt.dnxt.demo.swagger.dto.Response;
import pt.dnxt.demo.swagger.service.ISwaggerService;
import pt.dnxt.demo.swagger.utils.Utils;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Api(tags = "Swagger demo controller")
@RequestMapping("/v1")
@RestController
public class SwaggerController {

    private final ISwaggerService iSwaggerService;

    @Autowired
    public SwaggerController(ISwaggerService iSwaggerService) {
        this.iSwaggerService = iSwaggerService;
    }

    //POST
    @ApiOperation(value = "Create Example", httpMethod = "POST")
    @PostMapping(value = "/example/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createExample(@RequestBody @ApiParam(value = "description") Body body){

        return new ResponseEntity<>(Utils.prepareResponseHeader(this.iSwaggerService.createExample(body.getDescription())), HttpStatus.CREATED);
    }

    //PUT
    @ApiOperation(value = "Update Example", httpMethod = "PUT")
    @PutMapping(value = "/example/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateExample(@RequestHeader("Authorization") @ApiParam(value = "Bearer $token") String authorization, @RequestBody @ApiParam(value = "description") Body body){

        return new ResponseEntity<>(Utils.prepareResponseHeader(this.iSwaggerService.updateExample(body.getDescription(), Utils.getTokenFromAuthorization(authorization))), HttpStatus.OK);
    }

    //GET
    @ApiOperation(value = "Get Example by Token", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
    @GetMapping(value = "/example/retrieve", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Body> getExampleByToken(@RequestHeader("Authorization") @ApiParam(value = "Bearer $token") String authorization){
        Response response = this.iSwaggerService.getExample(Utils.getTokenFromAuthorization(authorization));
        Body responseBody = new Body();
        responseBody.setDescription(response.getDescription());

        return ResponseEntity.ok().headers(Utils.prepareResponseHeader(response.getUuid())).body(responseBody);
    }

    @ApiOperation(value = "Get All Examples by Hash Role", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
    @GetMapping(value = "/admin/example/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Response>> getAllExamplesByProfile(@RequestHeader("Authorization") @ApiParam(value = "Bearer $hashAdmin") String authorization) throws NoSuchAlgorithmException {
        if(Utils.compareHashes(Utils.getTokenFromAuthorizationAdmin(authorization))){
            List<Response> responseList = this.iSwaggerService.getAllExamples();
            return ResponseEntity.ok().body(responseList);
        }else{
            throw new PermissionDeniedDataAccessException("example.get.all.error.permissionDenied", null);
        }
    }

    //DELETE
    @ApiOperation(value = "Delete Example", httpMethod = "DELETE")
    @DeleteMapping(value = "/example/delete")
    public ResponseEntity<?> deleteExample(@RequestHeader("Authorization") @ApiParam(value = "Bearer $token") String authorization){
        this.iSwaggerService.deleteExample(Utils.getTokenFromAuthorization(authorization));

        return ResponseEntity.ok().build();
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NullPointerException.class)
    public ErrorReasonBodyDTO notFound(NullPointerException e){
        e.getStackTrace();
        return new ErrorReasonBodyDTO(e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = PermissionDeniedDataAccessException.class)
    public ErrorReasonBodyDTO accessUnauthorized(PermissionDeniedDataAccessException e){
        e.getStackTrace();
        return new ErrorReasonBodyDTO(e.getMessage());
    }
}
