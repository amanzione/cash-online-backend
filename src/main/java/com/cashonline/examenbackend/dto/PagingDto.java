package com.cashonline.examenbackend.dto;

import lombok.Data;

@Data
public class PagingDto {
    private final int page;
    private final int size;
    private final long total;
}
