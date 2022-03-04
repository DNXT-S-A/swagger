package pt.dnxt.demo.swagger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Response {
    private UUID uuid;
    private String description;
}
