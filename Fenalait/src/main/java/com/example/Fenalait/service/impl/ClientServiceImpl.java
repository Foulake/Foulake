package com.example.Fenalait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.ClientDto;
import com.example.Fenalait.dto.ClientResponse;
import com.example.Fenalait.exception.ResourceNotFoundException;
import com.example.Fenalait.model.Client;
import com.example.Fenalait.repository.ClientRepository;
import com.example.Fenalait.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{
	
private ClientRepository clientRepository; 
	
	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public ClientDto createClient(ClientDto clientDto) {
		 // convert DTO to entity
        Client client = mapToEntity(clientDto);
        Client newClient = clientRepository.save(client);

        // convert entity to DTO
        ClientDto clientResponse = mapToDTO(newClient);
        return clientResponse;
	}

	@Override
	public ClientResponse getAllClients(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Client> clients = clientRepository.findAll(pageable);

        // get content for page object
        List<Client> listOfClients = clients.getContent();

        List<ClientDto> content= listOfClients.stream().map(client -> mapToDTO(client)).collect(Collectors.toList());

        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setContent(content);
        clientResponse.setPageNo(clients.getNumber());
        clientResponse.setPageSize(clients.getSize());
        clientResponse.setTotalElements(clients.getTotalElements());
        clientResponse.setTotalPages(clients.getTotalPages());
        clientResponse.setLast(clients.isLast());

        return clientResponse;
	}

	@Override
	public ClientDto getClientById(Long id) {
		Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
        return mapToDTO(client);
	}

	@Override
	public ClientDto updateClient(ClientDto clientDto, Long id) {
		// get client by id from the database
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));

        client.setNomClient(clientDto.getNomClient());
        client.setPrenomClient(clientDto.getPrenomClient());
        client.setTelClient(clientDto.getTelClient());

        Client updatedClient = clientRepository.save(client);
        return mapToDTO(updatedClient);
	}

	@Override
	public void deleteClientById(Long id) {
		// get client by id from the database
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
        clientRepository.delete(client);
	}

	
	 // convert Entity into DTO
    private ClientDto mapToDTO(Client client){
       // ClientDto clientDto = mapper.map(client, ClientDto.class);
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setNomClient(client.getNomClient());
        clientDto.setPrenomClient(client.getPrenomClient());
        clientDto.setTelClient(client.getTelClient());
        return clientDto;
    }

    // convert DTO to entity
    private Client mapToEntity(ClientDto clientDto){
        //Client client = mapper.map(clientDto, Client.class);
        Client client = new Client();
        client.setNomClient(clientDto.getNomClient());
        client.setPrenomClient(clientDto.getPrenomClient());
        client.setTelClient(clientDto.getTelClient());
        return client;
    }

	@Override
	public ClientResponse searchClientFull(int pageNo, int pageSize, String sortBy, String sortDir, String keywords) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<Client> clients = clientRepository.findAll(pageable, keywords);
        
        List<Client> listOfClients = clients.getContent();
        
        List<ClientDto> content = listOfClients.stream().map(client -> mapToDTO(client)).collect(Collectors.toList());
		
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setContent(content);
        clientResponse.setPageNo(clients.getNumber());
        clientResponse.setPageSize(clients.getSize());
        clientResponse.setTotalElements(clients.getTotalElements());
        clientResponse.setTotalPages(clients.getTotalPages());
        clientResponse.setLast(clients.isLast());

        return clientResponse;

	}
}
