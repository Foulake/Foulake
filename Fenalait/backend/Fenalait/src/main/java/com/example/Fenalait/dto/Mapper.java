package com.example.Fenalait.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.Fenalait.model.Category;
import com.example.Fenalait.model.Fournisseur;
import com.example.Fenalait.model.Magasin;
import com.example.Fenalait.model.Paiement;
import com.example.Fenalait.model.Approvissionnement;
import com.example.Fenalait.model.CategorieFournisseur;
import com.example.Fenalait.model.Produit;
import com.example.Fenalait.model.User;
import com.example.Fenalait.model.Vente;
public class Mapper {
	
public static ProduitResponse produitToProduitResponse(Produit produit) {
		
	ProduitResponse produitResponseDto = new ProduitResponse();
	produitResponseDto.setId(produit.getId());
	produitResponseDto.setCategoryNom(produit.getCategory().getNom());
	produitResponseDto.setMagasinNom(produit.getMagasin().getNomMagasin());
	produitResponseDto.setEmail(produit.getUser().getEmail());
	
	//neeew
	produitResponseDto.setCode(produit.getCode());
	produitResponseDto.setPrice(produit.getPrice());
	produitResponseDto.setQte(produit.getQte());
	produitResponseDto.setDate(produit.getDate());
	produitResponseDto.setDateExp(produit.getDateExp());
	produitResponseDto.setNomPrdt(produit.getNomPrdt());
	
	return produitResponseDto;
}

public static List<ProduitResponse> produitToProduitResponses( List<Produit> produits){
	
	List<ProduitResponse> produitResponseDtos = new ArrayList<>();
	for(Produit produit: produits) {
		produitResponseDtos.add(produitToProduitResponse(produit));
	}
	
	return produitResponseDtos;
	
}


public static ApproResponse approvissionnementToApproResponse(Approvissionnement approvissionnement) {
	
	ApproResponse approResponseDto = new ApproResponse();
	approResponseDto.setId(approvissionnement.getId());
	approResponseDto.setUserNom(approvissionnement.getUser().getPrenom());
	approResponseDto.setProduitNom(approvissionnement.getProduit().getNomPrdt());
	approResponseDto.setFournisseurNom(approvissionnement.getFournisseur().getPrenom());
	
	//neeew
	approResponseDto.setDateAppro(approvissionnement.getDateAppro());
	approResponseDto.setQteAppro(approvissionnement.getQteAppro());
	
	return approResponseDto;
}

public static List<ApproResponse> approvissionnementToApproResponses( List<Approvissionnement> approvissionnements){
	
	List<ApproResponse> approvissionnementResponseDtos = new ArrayList<>();
	for(Approvissionnement approvissionnement: approvissionnements) {
		approvissionnementResponseDtos.add(approvissionnementToApproResponse(approvissionnement));
	}
	
	return approvissionnementResponseDtos;
	
}	

public static CategoryResponse categoryToCategoryResponse(Category category) {
	CategoryResponse categoryResponse = new CategoryResponse();
	categoryResponse.setId(category.getId());
	categoryResponse.setNom(category.getNom());
//	List<String> names = new ArrayList<>();
//	List<Produit> produits = category.getProduits();
//	for(Produit produit: produits) {
//		names.add(produit.getName());
//	}
//	categoryResponse.setProduitNames(names);
	return categoryResponse;
}

public static List<CategoryResponse> categoryToCategoryResponses(List<Category> categories){
	List<CategoryResponse> categoryResponses = new ArrayList<>();
	for(Category category: categories) {
		categoryResponses.add(categoryToCategoryResponse(category));
	}
	return categoryResponses;
}

public static MagasinResponse magasinToMagasinResponse(Magasin magasin) {
	MagasinResponse magasinResponse = new MagasinResponse();
	magasinResponse.setId(magasin.getId());
	magasinResponse.setNomMagasin(magasin.getNomMagasin());
//	magasinResponse.setNomLocalite(magasin.getLocalite().getNom());
//	magasinResponse.setDescription(magasin.getLocalite().getDescription());
//	List<String> names = new ArrayList<>();
//	List<Produit> produits = magasin.getProduits();
//	for(Produit produit: produits) {
//		names.add(produit.getName());
//	}
//	magasinResponse.setProduitNames(names);
	return magasinResponse;
}




public static List<MagasinResponse> magasinToMagasinResponses(List<Magasin> magasins){
	List<MagasinResponse> magasinResponses = new ArrayList<>();
	for(Magasin magasin: magasins) {
		magasinResponses.add(magasinToMagasinResponse(magasin));
	}
	return magasinResponses;
}

	

	public static CategoryFourResponse categorieFournisseurToCategoryFourResponse(CategorieFournisseur categorieFournisseur) {
		CategoryFourResponse categoryFourResponse = new CategoryFourResponse();
		categoryFourResponse.setId(categorieFournisseur.getId());
		categoryFourResponse.setDescription(categorieFournisseur.getDescription());
//		categoryFourResponse.setNomLocalite(categorieFournisseur.getLocalite().getNom());
//		categoryFourResponse.setDescription(categorieFournisseur.getLocalite().getDescription());
//		List<String> names = new ArrayList<>();
//		List<Produit> produits = categorieFournisseur.getProduits();
//		for(Produit produit: produits) {
//			names.add(produit.getName());
//		}
//		categoryFourResponse.setProduitNames(names);
		return categoryFourResponse;
	}
	
	
	
	
	public static List<CategoryFourResponse> categorieFournisseurToCategoryFourResponses(List<CategorieFournisseur> categorieFournisseurs){
		List<CategoryFourResponse> categoryFourResponses = new ArrayList<>();
		for(CategorieFournisseur categorieFournisseur: categorieFournisseurs) {
			categoryFourResponses.add(categorieFournisseurToCategoryFourResponse(categorieFournisseur));
		}
		return categoryFourResponses;
	}
	
	public static FournisseurResponse fournisseurToFournisseurResponse(Fournisseur fournisseur) {
		
		FournisseurResponse fournisseurResponseDto = new FournisseurResponse();
		fournisseurResponseDto.setId(fournisseur.getId());
		fournisseurResponseDto.setCategoryFourNom(fournisseur.getCategorieFournisseur().getDescription());
		
		//neeew
		fournisseurResponseDto.setNom(fournisseur.getNom());
		fournisseurResponseDto.setPrenom(fournisseur.getPrenom());
		fournisseurResponseDto.setTel(fournisseur.getTel());
		fournisseurResponseDto.setDateFour(fournisseur.getDateFour());
		
		return fournisseurResponseDto;
	}

	public static List<FournisseurResponse> fournisseurToFournisseurResponses( List<Fournisseur> fournisseurs){
		
		List<FournisseurResponse> fournisseurResponseDtos = new ArrayList<>();
		for(Fournisseur fournisseur: fournisseurs) {
			fournisseurResponseDtos.add(fournisseurToFournisseurResponse(fournisseur));
		}
		
		return fournisseurResponseDtos;
		
	}
	
	public static VenteResponse venteToVenteResponse(Vente vente) {
			
		VenteResponse venteResponseDto = new VenteResponse();
		venteResponseDto.setId(vente.getId());
		venteResponseDto.setClientNom(vente.getClient().getPrenomClient());
		venteResponseDto.setProduitNom(vente.getProduit().getNomPrdt());
		//venteResponseDto.setUserNom(vente.getUser().getPrenom());
		
		//neeew
		venteResponseDto.setMontant(vente.getMontant());
		venteResponseDto.setQuantite(vente.getQuantite());
		
		return venteResponseDto;
	}

	public static List<VenteResponse> venteToVenteResponses( List<Vente> ventes){
		
		List<VenteResponse> venteResponseDtos = new ArrayList<>();
		for(Vente vente: ventes) {
			venteResponseDtos.add(venteToVenteResponse(vente));
		}
		
		return venteResponseDtos;
		
	}
	
	public static PaiementResponse paiementToPaiementResponse(Paiement paiement) {
		
		PaiementResponse paiementResponseDto = new PaiementResponse();
		paiementResponseDto.setId(paiement.getId());
		paiementResponseDto.setFournisseurNom(paiement.getFournisseur().getPrenom());
		
		//neeew
		paiementResponseDto.setDate(paiement.getDate());
		paiementResponseDto.setMontant(paiement.getMontant());
		paiementResponseDto.setQte(paiement.getQte());
		paiementResponseDto.setPayee(paiement.isPayee());
		
		return paiementResponseDto;
	}

	public static List<PaiementResponse> paiementToPaiementResponses( List<Paiement> paiements){
		
		List<PaiementResponse> paiementResponseDtos = new ArrayList<>();
		for(Paiement paiement: paiements) {
			paiementResponseDtos.add(paiementToPaiementResponse(paiement));
		}
		
		return paiementResponseDtos;
		
	}
	
	public static UserResponseDto userToUserResponseDto(User user) {
		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setId(user.getId());
		userResponseDto.setNom(user.getNom());
		userResponseDto.setPrenom(user.getPrenom());
		userResponseDto.setEmail(user.getEmail());
//		List<String> names = new ArrayList<>();
//		List<Produit> produits = user.getProduits();
//		for(Produit produit: produits) {
//			names.add(produit.getName());
//		}
//		userResponseDto.setProduitNames(names);
		return userResponseDto;
	}
	public static List<UserResponseDto> userToUserResponseDtos(List<User> users){
		List<UserResponseDto> userResponseDtos = new ArrayList<>();
		for(User user: users) {
			userResponseDtos.add(userToUserResponseDto(user));
		}
		return userResponseDtos;
	}
	
	

}
