package com.example.Fenalait.service;

import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.EmployeDto;
import com.example.Fenalait.dto.EmployeResponse;

@Service
public interface EmployeService {

	EmployeDto createEmploye(EmployeDto employeDto);

    EmployeResponse getAllEmployes(int pageNo, int pageSize, String sortBy, String sortDir);

    EmployeDto getEmployeById(Long id);

    EmployeDto updateEmploye(EmployeDto employeDto, Long id);

    void deleteEmployeById(Long id);

	EmployeResponse searchEmployeFull(int pageNo, int pageSize, String sortBy, String sortDir, String keywords);
    
}
