package org.ritpl.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDTO {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String path;
}
