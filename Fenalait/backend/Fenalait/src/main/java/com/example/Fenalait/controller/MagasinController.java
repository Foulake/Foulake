package com.example.Fenalait.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Fenalait.dto.MagasinRequestDto;
import com.example.Fenalait.dto.MagasinResponse;
import com.example.Fenalait.service.MagasinService;
import com.example.Fenalait.utils.AppConstants;

@RestController
@RequestMapping("/api/v1/magasins")
public class MagasinController {

	private MagasinService magasinService;

    public MagasinController(MagasinService magasinService) {
        this.magasinService = magasinService;
    }
    
    
    @PostMapping("/add")
    public ResponseEntity<MagasinRequestDto> createMagasin(@Valid @RequestBody MagasinRequestDto magasinRequestDto){
        return new ResponseEntity<>(magasinService.createMagasin(magasinRequestDto), HttpStatus.CREATED);
    }
   /**
    @PreAuthorize("hasRole('ADMIN')")
    // create blog magasin rest api
   	**/

   

   /** @PostAuthorize("hasRole('ADMIN')")
    // update magasin by id rest api
    @PutMapping("/edit/{id}")
    public ResponseEntity<MagasinRequestDto> updateMagasin(@Valid @RequestBody MagasinRequestDto magasinRequestDto, @PathVariable(name = "id") Long id){

       MagasinRequestDto magasinResponse = magasinService.updateMagasin(magasinRequestDto, id);

       return new ResponseEntity<>(magasinResponse, HttpStatus.OK);
    } 	**/

   /** @PostAuthorize("hasRole('ADMIN')")
    // delete magasin rest api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMagasin(@PathVariable(name = "id") Long id){

        magasinService.deleteMagasinById(id);

        return new ResponseEntity<>("Magasin entity deleted successfully.", HttpStatus.OK);
    }	**/
    
    
    /** New Codes **/
    
    // get all magasins rest api
    @GetMapping("/getAll")
    public MagasinResponse getAllMagasins(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return magasinService.getAllMagasins(pageNo, pageSize, sortBy, sortDir);
    }

    // get magasin by id
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<MagasinRequestDto> getMagasinById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(magasinService.getMagasinById(id));
    }
    
    @PostMapping("/localite/{localiteId}/magasin")
    public ResponseEntity<MagasinRequestDto> createMagasin(@PathVariable(value = "localiteId") Long localiteId,
                                                    @Valid @RequestBody MagasinRequestDto magasinDto){
        return new ResponseEntity<>(magasinService.createMagasin(localiteId, magasinDto), HttpStatus.CREATED);
    }

    @GetMapping("/localite/{localiteId}/magasin")
    public List<MagasinRequestDto> getMagasinsByLocaliteId(@PathVariable(value = "localiteId") Long localiteId){
        return magasinService.getMagasinsByLocaliteId(localiteId);
    }

    @GetMapping("/localite/{localiteId}/magasin/{id}")
    public ResponseEntity<MagasinRequestDto> getMagasinById(@PathVariable(value = "localiteId") Long localiteId,
                                                     @PathVariable(value = "id") Long magasinId){
        MagasinRequestDto magasinDto = magasinService.getMagasinById(localiteId, magasinId);
        return new ResponseEntity<>(magasinDto, HttpStatus.OK);
    }

    @PutMapping("/localite/{localiteId}/magasin/{id}")
    public ResponseEntity<MagasinRequestDto> updateMagasin(@PathVariable(value = "localiteId") Long localiteId,
                                                    @PathVariable(value = "id") Long magasinId,
                                                    @Valid @RequestBody MagasinRequestDto magasinDto){
        MagasinRequestDto updatedMagasin = magasinService.updateMagasin(localiteId, magasinId, magasinDto);
        return new ResponseEntity<>(updatedMagasin, HttpStatus.OK);
    }

    @DeleteMapping("/localite/{localiteId}/magasin/{id}")
    public ResponseEntity<String> deleteMagasin(@PathVariable(value = "localiteId") Long localiteId,
                                                @PathVariable(value = "id") Long magasinId){
        magasinService.deleteMagasin(localiteId, magasinId);
        return new ResponseEntity<>("Magasin deleted successfully", HttpStatus.OK);
    }

}
