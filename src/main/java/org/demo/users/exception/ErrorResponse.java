package org.demo.users.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private List<ExceptionResponse> error;
}
