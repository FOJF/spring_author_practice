package com.john.spring_author_practice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto {
    Boolean isSuccess;
    Object result;
    Integer statusCode;
    String statusMessage;
}
