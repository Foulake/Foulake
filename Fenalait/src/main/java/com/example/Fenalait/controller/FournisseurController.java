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
import org.springframework.web.bind.annotation.RestController;

import com.example.Fenalait.dto.FournisseurDto;
import com.example.Fenalait.service.FournisseurService;

@RestController
public class FournisseurController {

	private FournisseurService fournisseurService;

    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @PostMapping("/category/{categoryId}/fournisseurs")
    public ResponseEntity<FournisseurDto> createFournisseur(@PathVariable(value = "categoryId") long categoryId,
                                                    @Valid @RequestBody FournisseurDto fournisseurDto){
        return new ResponseEntity<>(fournisseurService.createFournisseur(categoryId, fournisseurDto), HttpStatus.CREATED);
    }

    @GetMapping("/category/{categoryId}/fournisseur")
    public List<FournisseurDto> getFournisseursByCategoryId(@PathVariable(value = "categoryId") Long categoryId){
        return fournisseurService.getFournisseursByCategoryFourId(categoryId);
    }

    @GetMapping("/category/{categoryId}/fournisseur/{id}")
    public ResponseEntity<FournisseurDto> getFournisseurById(@PathVariable(value = "categoryId") Long categoryId,
                                                     @PathVariable(value = "id") Long fournisseurId){
        FournisseurDto fournisseurDto = fournisseurService.getFournisseurById(categoryId, fournisseurId);
        return new ResponseEntity<>(fournisseurDto, HttpStatus.OK);
    }

    @PutMapping("/category/{categoryId}/fournisseur/{id}")
    public ResponseEntity<FournisseurDto> updateFournisseur(@PathVariable(value = "categoryId") Long categoryId,
                                                    @PathVariable(value = "id") Long fournisseurId,
                                                    @Valid @RequestBody FournisseurDto fournisseurDto){
        FournisseurDto updatedFournisseur = fournisseurService.updateFournisseur(categoryId, fournisseurId, fournisseurDto);
        return new ResponseEntity<>(updatedFournisseur, HttpStatus.OK);
    }

    @DeleteMapping("/category/{categoryId}/fournisseur/{id}")
    public ResponseEntity<String> deleteFournisseur(@PathVariable(value = "categoryId") Long categoryId,
                                                @PathVariable(value = "id") Long fournisseurId){
        fournisseurService.deleteFournisseur(categoryId, fournisseurId);
        return new ResponseEntity<>("Fournisseur deleted successfully", HttpStatus.OK);
    }
}
