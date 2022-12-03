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
import org.springframework.web.bind.annotation.RestController;

import com.example.Fenalait.dto.FournisseurDto;
import com.example.Fenalait.service.FournisseurService;

@RestController
@RequestMapping("/api/v1/fournisseurs")
public class FournisseurController {

	private FournisseurService fournisseurService;

    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @PostMapping("/category/{categoryFourId}/add")
    public ResponseEntity<FournisseurDto> createFournisseur(@PathVariable(value = "categoryFourId") Long categoryFourId,
                                                    @Valid @RequestBody FournisseurDto fournisseurDto){
        return new ResponseEntity<>(fournisseurService.createFournisseur(categoryFourId, fournisseurDto), HttpStatus.CREATED);
    }

    @GetMapping("/category/{categoryFourId}/fournisseur")
    public List<FournisseurDto> getFournisseursByCategoryId(@PathVariable(value = "categoryFourId") Long categoryFourId){
        return fournisseurService.getFournisseursByCategoryFourId(categoryFourId);
    }

    @GetMapping("/category/{categoryFourId}/fournisseur/{id}")
    public ResponseEntity<FournisseurDto> getFournisseurById(@PathVariable(value = "categoryFourId") Long categoryFourId,
                                                     @PathVariable(value = "id") Long fournisseurId){
        FournisseurDto fournisseurDto = fournisseurService.getFournisseurById(categoryFourId, fournisseurId);
        return new ResponseEntity<>(fournisseurDto, HttpStatus.OK);
    }

    @PutMapping("/category/{categoryFourId}/fournisseur/{id}")
    public ResponseEntity<FournisseurDto> updateFournisseur(@PathVariable(value = "categoryFourId") Long categoryFourId,
                                                    @PathVariable(value = "id") Long fournisseurId,
                                                    @Valid @RequestBody FournisseurDto fournisseurDto){
        FournisseurDto updatedFournisseur = fournisseurService.updateFournisseur(categoryFourId, fournisseurId, fournisseurDto);
        return new ResponseEntity<>(updatedFournisseur, HttpStatus.OK);
    }

    @DeleteMapping("/category/{categoryFourId}/fournisseur/{id}")
    public ResponseEntity<String> deleteFournisseur(@PathVariable(value = "categoryFourId") Long categoryFourId,
                                                @PathVariable(value = "id") Long fournisseurId){
        fournisseurService.deleteFournisseur(categoryFourId, fournisseurId);
        return new ResponseEntity<>("Fournisseur deleted successfully", HttpStatus.OK);
    }
}
