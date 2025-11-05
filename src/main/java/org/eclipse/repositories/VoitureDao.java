package org.eclipse.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.config.MySqlConnection;
import org.eclipse.models.Voiture;

public class VoitureDao implements GenericDao<Voiture, Integer> {

	@Override
	public List<Voiture> findAll() {
		List<Voiture> voitures = new ArrayList<>();
		Connection connection = MySqlConnection.getConnection();

		if (connection != null) {
			try {
				String query = "SELECT * FROM Voiture";
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);

				while (rs.next()) {
					voitures.add(mapResultSetToVoiture(rs));
				}

			} catch (Exception e) {
				System.err.println("Erreur lors de la récupération des voitures : " + e.getMessage());
			}
		}
		return voitures;
	}

	@Override
	public Voiture findById(Integer id) {
		Connection connection = MySqlConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT * FROM Voiture WHERE id_voiture = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					return mapResultSetToVoiture(rs);
				}

			} catch (Exception e) {
				System.err.println("Erreur lors de la récupération de la voiture : " + e.getMessage());
			}
		}
		return null;
	}

	@Override
	public Voiture save(Voiture v) {
		Connection connection = MySqlConnection.getConnection();

		if (connection != null) {
			try {
				String query = "INSERT INTO Voiture (reference, marque, modele, annee, couleur, prix, description, id_categorie) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

				ps.setString(1, v.getReference());
				ps.setString(2, v.getMarque());
				ps.setString(3, v.getModele());
				ps.setInt(4, v.getAnnee());
				ps.setString(5, v.getCouleur());
				ps.setDouble(6, v.getPrix());
				ps.setString(7, v.getDescription());
				ps.setInt(8, v.getIdCategorie());

				ps.executeUpdate();

				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					v.setIdVoiture(rs.getInt(1));
				}
				return v;

			} catch (Exception e) {
				System.err.println("Erreur lors de l'insertion de la voiture : " + e.getMessage());
			}
		}
		return null;
	}

	@Override
	public Voiture update(Voiture v) {
		Connection connection = MySqlConnection.getConnection();

		if (connection != null) {
			try {
				String query = "UPDATE Voiture SET reference = ?, marque = ?, modele = ?, annee = ?, couleur = ?, prix = ?, description = ?, id_categorie = ? WHERE id_voiture = ?";
				PreparedStatement ps = connection.prepareStatement(query);

				ps.setString(1, v.getReference());
				ps.setString(2, v.getMarque());
				ps.setString(3, v.getModele());
				ps.setInt(4, v.getAnnee());
				ps.setString(5, v.getCouleur());
				ps.setDouble(6, v.getPrix());
				ps.setString(7, v.getDescription());
				ps.setInt(8, v.getIdCategorie());
				ps.setInt(9, v.getIdVoiture());

				int result = ps.executeUpdate();
				if (result > 0)
					return v;

			} catch (Exception e) {
				System.err.println("Erreur lors de la mise à jour de la voiture : " + e.getMessage());
			}
		}
		return null;
	}

	@Override
	public boolean remove(Integer id) {
		Connection connection = MySqlConnection.getConnection();

		if (connection != null) {
			try {
				String query = "DELETE FROM Voiture WHERE id_voiture = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1, id);

				int result = ps.executeUpdate();
				return result > 0;

			} catch (Exception e) {
				System.err.println("Erreur lors de la suppression de la voiture : " + e.getMessage());
			}
		}
		return false;
	}

	private Voiture mapResultSetToVoiture(ResultSet rs) throws SQLException {
		return new Voiture(rs.getInt("id_voiture"), rs.getString("reference"), rs.getString("marque"),
				rs.getString("modele"), rs.getInt("annee"), rs.getString("couleur"), rs.getDouble("prix"),
				rs.getString("description"),
				rs.getTimestamp("date_ajout") != null ? rs.getTimestamp("date_ajout").toLocalDateTime() : null,
				rs.getInt("id_categorie"));
	}
}
