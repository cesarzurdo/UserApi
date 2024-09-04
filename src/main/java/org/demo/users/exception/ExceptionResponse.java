package org.demo.users.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class ExceptionResponse {
    private Date timestamp;
    private int codigo;
    private String detail;
}
