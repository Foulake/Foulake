package com.example.Fenalait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.Fenalait.dto.EmployeDto;
import com.example.Fenalait.dto.EmployeResponse;
import com.example.Fenalait.exception.ResourceNotFoundException;
import com.example.Fenalait.model.Employe;
import com.example.Fenalait.repository.EmployeRepository;
import com.example.Fenalait.service.EmployeService;

public class EmployeServiceImpl implements EmployeService{

private EmployeRepository employeRepository; 
	
	public EmployeServiceImpl(EmployeRepository employeRepository) {
		this.employeRepository = employeRepository;
	}

	@Override
	public EmployeDto createEmploye(EmployeDto employeDto) {
		 // convert DTO to entity
        Employe employe = mapToEntity(employeDto);
        Employe newEmploye = employeRepository.save(employe);

        // convert entity to DTO
        EmployeDto employeResponse = mapToDTO(newEmploye);
        return employeResponse;
	}

	@Override
	public EmployeResponse getAllEmployes(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Employe> employes = employeRepository.findAll(pageable);

        // get content for page object
        List<Employe> listOfEmployes = employes.getContent();

        List<EmployeDto> content= listOfEmployes.stream().map(employe -> mapToDTO(employe)).collect(Collectors.toList());

        EmployeResponse employeResponse = new EmployeResponse();
        employeResponse.setContent(content);
        employeResponse.setPageNo(employes.getNumber());
        employeResponse.setPageSize(employes.getSize());
        employeResponse.setTotalElements(employes.getTotalElements());
        employeResponse.setTotalPages(employes.getTotalPages());
        employeResponse.setLast(employes.isLast());

        return employeResponse;
	}

	@Override
	public EmployeDto getEmployeById(Long id) {
		Employe employe = employeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employe", "id", id));
        return mapToDTO(employe);
	}

	@Override
	public EmployeDto updateEmploye(EmployeDto employeDto, Long id) {
		// get employe by id from the database
        Employe employe = employeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employe", "id", id));

        employe.setEmail(employeDto.getEmail());
        employe.setFirstName(employeDto.getFirstName());
        employe.setLastName(employeDto.getLastName());
        employe.setTelEmploye(employeDto.getTelEmploye());

        Employe updatedEmploye = employeRepository.save(employe);
        return mapToDTO(updatedEmploye);
	}

	@Override
	public void deleteEmployeById(Long id) {
		// get employe by id from the database
        Employe employe = employeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employe", "id", id));
        employeRepository.delete(employe);
	}

	
	 // convert Entity into DTO
    private EmployeDto mapToDTO(Employe employe){
       // EmployeDto employeDto = mapper.map(employe, EmployeDto.class);
        EmployeDto employeDto = new EmployeDto();
        employeDto.setId(employe.getId());
        employeDto.setEmail(employe.getEmail());
        employeDto.setFirstName(employe.getFirstName());
        employeDto.setLastName(employe.getLastName());
        employeDto.setTelEmploye(employe.getTelEmploye());
        return employeDto;
    }

    // convert DTO to entity
    private Employe mapToEntity(EmployeDto employeDto){
        //Employe employe = mapper.map(employeDto, Employe.class);
        Employe employe = new Employe();
        employe.setFirstName(employeDto.getFirstName());
        employe.setLastName(employeDto.getLastName());
        employe.setTelEmploye(employeDto.getTelEmploye());
        employe.setEmail(employeDto.getEmail());
        return employe;
    }

	@Override
	public EmployeResponse searchEmployeFull(int pageNo, int pageSize, String sortBy, String sortDir, String keywords) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<Employe> employes = employeRepository.findAll(pageable, keywords);
        
        List<Employe> listOfEmployes = employes.getContent();
        
        List<EmployeDto> content = listOfEmployes.stream().map(employe -> mapToDTO(employe)).collect(Collectors.toList());
		
        EmployeResponse employeResponse = new EmployeResponse();
        employeResponse.setContent(content);
        employeResponse.setPageNo(employes.getNumber());
        employeResponse.setPageSize(employes.getSize());
        employeResponse.setTotalElements(employes.getTotalElements());
        employeResponse.setTotalPages(employes.getTotalPages());
        employeResponse.setLast(employes.isLast());

        return employeResponse;

	}
	
}
