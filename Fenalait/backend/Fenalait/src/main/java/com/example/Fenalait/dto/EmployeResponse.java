package com.example.Fenalait.dto;

import java.util.List;

import lombok.Data;

@Data
public class EmployeResponse {

	private List<EmployeDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
