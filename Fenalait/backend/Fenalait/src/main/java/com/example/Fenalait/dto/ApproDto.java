package com.example.Fenalait.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_DEFAULT)
public class ApproDto {

	private Long id;
	private Long produitId;
	private Long userId;
	private Long fournisseurId;
	
	private String produitNom;
	private String userNom;
	private String fournisseurNom;

	//@NotBlank(message = "Veuillez entrer la quantité d'approvissionnement !!")
	private Double qteAppro;
	
	@Past(message = "La date ne peut être inférieure à la date courante !!")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateAppro;
	
//	@Past(message = "La date ne peut être inférieure à la date courante !!")
//    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
////	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@JsonSerialize(using = LocalDateTimeSerializer.class)
//	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
//	private LocalDateTime dateAppro;

	public ApproDto(Long id, Double qteAppro) {
		this.id = id;
		this.qteAppro = qteAppro;
	}
	
	
}
