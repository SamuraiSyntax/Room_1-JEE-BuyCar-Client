package org.eclipse.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.config.MySqlConnection;
import org.eclipse.models.Utilisateur;

public class UtilisateurDao implements GenericDao<Utilisateur, Integer> {

	@Override
	public List<Utilisateur> findAll() {
		List<Utilisateur> utilisateurs = null;
		Connection connection = MySqlConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String select = "SELECT * FROM utilisateur";
				ResultSet resultSet = statement.executeQuery(select);
				utilisateurs = new ArrayList<Utilisateur>();
				while (resultSet.next()) {
					int id = resultSet.getInt("id_user");
					String nom = resultSet.getString("nom");
					String prenom = resultSet.getString("prenom");
					String email = resultSet.getString("email");
					String password = resultSet.getString("mot_de_passe");
					String compte = resultSet.getString("nom_compte");
					int role = resultSet.getInt("id_role");
					String telephone = resultSet.getString("telephone");
					utilisateurs.add(new Utilisateur(id, nom, prenom, compte, email, password, role, telephone));
				}
			} catch (Exception e) {
				System.err.println("Problème de récupération d'utilisateurs");
			}
		}
		return utilisateurs;
	}

	@Override
	public Utilisateur findById(Integer id) {
		Connection connection = MySqlConnection.getConnection();
		if (connection != null) {
			try {
				String select = "SELECT * FROM utilisateur WHERE id = ?";
				PreparedStatement statement = connection.prepareStatement(select);
				statement.setInt(1, id);
				ResultSet resultSet = statement.executeQuery();
				if (resultSet.next()) {
					String nom = resultSet.getString("nom");
					String prenom = resultSet.getString("prenom");
					String email = resultSet.getString("email");
					String password = resultSet.getString("mot_de_passe");
					String compte = resultSet.getString("nom_compte");
					int role = resultSet.getInt("id_role");
					String telephone = resultSet.getString("telephone");
					return (new Utilisateur(id, nom, prenom, compte, email, password, role, telephone));
				}
			} catch (Exception e) {
				System.err.println("Problème de récupération d'utilisateurs");
			}
		}
		return null;
	}

	@Override
	public Utilisateur save(Utilisateur model) {
		Connection connection = MySqlConnection.getConnection();
		if (connection != null) {
			try {
				String insert = "INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, id_role) VALUES (?, ?, ?, ?, ?)";

				PreparedStatement statement = connection.prepareStatement(insert,
						PreparedStatement.RETURN_GENERATED_KEYS);
				statement.setString(1, model.getNom());
				statement.setString(2, model.getPrenom());
				statement.setString(3, model.getEmail());
				statement.setString(4, model.getPassword());
				statement.setInt(5, 2);

				statement.executeUpdate();
				ResultSet result = statement.getGeneratedKeys();
				if (result.next()) {
					model.setId(result.getInt(1));
					return model;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Problème d'insertion de utilisateur");
			}
		}
		return null;
	}

	@Override
	public Utilisateur update(Utilisateur model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	public Utilisateur findByEmailAndPassword(String email, String motDePasse) {
		Connection connection = MySqlConnection.getConnection();
		if (connection != null) {
			try {
				String sql = "SELECT * FROM utilisateur WHERE email = ? AND mot_de_passe = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, email);
				statement.setString(2, motDePasse);
				ResultSet resultSet = statement.executeQuery();

				if (resultSet.next()) {
					int id = resultSet.getInt("id_user");
					String nom = resultSet.getString("nom");
					String prenom = resultSet.getString("prenom");
					String motDePasseDb = resultSet.getString("mot_de_passe");

					return new Utilisateur(id, nom, prenom, email, motDePasseDb);
				}
			} catch (Exception e) {
				System.err.println("Erreur lors de la recherche de l'utilisateur : " + e.getMessage());
			}
		}
		return null;
	}
}
