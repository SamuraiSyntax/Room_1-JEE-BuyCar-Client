package org.eclipse.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dto.VoitureDto;

public class PanierService {

	private VoitureService voitureService = new VoitureService();

	public List<VoitureDto> getPanier(List<VoitureDto> sessionPanier) {
		if (sessionPanier == null) {
			return new ArrayList<>();
		}
		return sessionPanier;
	}

	public List<VoitureDto> addVoiture(List<VoitureDto> panier, int idVoiture) {
		VoitureDto voiture = voitureService.findById(idVoiture);
		if (voiture != null && !panier.contains(voiture)) {
			panier.add(voiture);
		}
		return panier;
	}

	public List<VoitureDto> removeVoiture(List<VoitureDto> panier,
			int idVoiture) {
		panier.removeIf(v -> v.getIdVoiture() == idVoiture);
		return panier;
	}

	public List<VoitureDto> clearPanier(List<VoitureDto> panier) {
		panier.clear();
		return panier;
	}

	public double calculerTotal(List<VoitureDto> panier) {
		return panier.stream().mapToDouble(VoitureDto::getPrix).sum();
	}
}
