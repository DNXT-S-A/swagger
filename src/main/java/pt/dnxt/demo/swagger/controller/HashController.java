package pt.dnxt.demo.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.dnxt.demo.swagger.utils.Utils;

import java.security.NoSuchAlgorithmException;

@Api(tags = "Hash demo controller")
@RequestMapping("/v1")
@RestController
public class HashController {

    @ApiOperation(value = "Get Admin Hash Role", httpMethod = "GET")
    @GetMapping(value = "/hash/retrieve")
    public ResponseEntity<?> getHashCode() throws NoSuchAlgorithmException {
        return new ResponseEntity<>(Utils.prepareAdminHeader(), HttpStatus.OK);
    }
}
