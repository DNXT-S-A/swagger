package pt.dnxt.demo.swagger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorReasonBodyDTO {

    private String errorMessage;
}