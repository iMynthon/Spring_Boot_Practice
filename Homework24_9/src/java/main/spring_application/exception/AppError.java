package main.spring_application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class AppError {

    private Integer statusCode;

    private String message;

}
