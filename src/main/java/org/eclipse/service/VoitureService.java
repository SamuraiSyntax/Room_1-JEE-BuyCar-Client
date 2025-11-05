package org.eclipse.service;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.dto.VoitureDto;
import org.eclipse.models.Voiture;
import org.eclipse.repositories.VoitureDao;

public class VoitureService {
	VoitureDao dao = new VoitureDao();

	 public List<VoitureDto> findAll() {
	  return dao.findAll().stream().map(this::toDto)
	    .collect(Collectors.toList());
	 }

	 // public List<VoitureDto> search(String type, int page, int size,
	 // String sort) {
	 // var voittures = findAll();
	 //
	 // // Filtrage
	 // if (type == null || type.isBlank()) {
	 // return voittures;
	 // }
	 //
	 // var filtred = new ArrayList<VoitureDto>();
	 // for (VoitureDto dto : voittures) {
	 // if (type.equalsIgnoreCase(dto.getMarque())) {
	 // filtred.add(dto);
	 // }
	 // }
	 //
	 // // Trie
	 // if (sort != null && sort.equalsIgnoreCase("nom")) {
	 // filtred.sort(Comparator.comparing(VoitureDto::getNom));
	 // }
	 //
	 // // Pagination
	 // int from = page * size;
	 // int to = Math.min(from + size, filtred.size());
	 //
	 // if (from >= filtred.size()) {
	 // return new ArrayList<VoitureDto>();
	 // }
	 //
	 // return filtred.subList(from, to);
	 // }

	 public VoitureDto findById(int id) {
	  Voiture voiture = dao.findAll().stream()
	    .filter(v -> v.getIdVoiture() == id).findFirst().orElse(null);
	  if (voiture != null) {
	   return toDto(voiture);
	  }
	  return null;
	 }

	 public VoitureDto create(VoitureDto dto) {
	  int newId = dao.findAll().size() + 1;
	  Voiture voiture = new Voiture();
	  voiture.setIdVoiture(newId);
	  voiture.setAnnee(dto.getAnnee());
	  voiture.setCouleur(dto.getCouleur());
	  voiture.setDescription(dto.getDescription());
	  voiture.setIdCategorie(dto.getIdCategorie());
	  voiture.setMarque(dto.getMarque());
	  voiture.setModele(dto.getModele());
	  voiture.setPrix(dto.getPrix());
	  voiture.setReference(dto.getReference());

	  dao.findAll().add(voiture);
	  return toDto(voiture);
	 }

	 public VoitureDto update(int id, VoitureDto dto) {
	  Voiture voiture = dao.findAll().stream()
	    .filter(v -> v.getIdVoiture() == id).findFirst().orElse(null);

	  if (voiture != null) {
	   voiture.setAnnee(dto.getAnnee());
	   voiture.setCouleur(dto.getCouleur());
	   voiture.setDescription(dto.getDescription());
	   voiture.setIdCategorie(dto.getIdCategorie());
	   voiture.setMarque(dto.getMarque());
	   voiture.setModele(dto.getModele());
	   voiture.setPrix(dto.getPrix());
	   voiture.setReference(dto.getReference());

	   return toDto(voiture);
	  }

	  return null;
	 }

	 public boolean delete(int id) {
	  Voiture voiture = dao.findAll().stream()
	    .filter(v -> v.getIdVoiture() == id).findFirst().orElse(null);

	  if (voiture != null) {
	   dao.findAll().remove(voiture);
	   return true;
	  }

	  return false;
	 }
	 private VoitureDto toDto(Voiture voiture) {
	  VoitureDto dto = new VoitureDto();
	  dto.setIdVoiture(voiture.getIdVoiture());
	  dto.setAnnee(voiture.getAnnee());
	  dto.setCouleur(voiture.getCouleur());
	  dto.setDescription(voiture.getDescription());
	  dto.setIdCategorie(voiture.getIdCategorie());
	  dto.setMarque(voiture.getMarque());
	  dto.setModele(voiture.getModele());
	  dto.setPrix(voiture.getPrix());
	  dto.setReference(voiture.getReference());
	  return dto;
	 }
	}



