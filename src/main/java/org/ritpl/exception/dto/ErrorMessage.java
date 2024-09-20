package org.ritpl.exception.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private long  timestamp;
    private int statusCode;
    private String message;

}
