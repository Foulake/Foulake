
package com.example.Fenalait.service;

import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.ClientDto;
import com.example.Fenalait.dto.ClientResponse;

@Service
public interface ClientService {

	ClientDto createClient(ClientDto clientDto);

    ClientResponse getAllClients(int pageNo, int pageSize, String sortBy, String sortDir);

    ClientDto getClientById(Long id);

    ClientDto updateClient(ClientDto clientDto, Long id);

    void deleteClientById(Long id);

	ClientResponse searchClientFull(int pageNo, int pageSize, String sortBy, String sortDir, String keywords);

}
