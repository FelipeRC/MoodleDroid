package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.ConstantesSistema;
import entities.Usuario;
import factory.ConnectionFactory;

/**
 * 
 * @author Felipe
 * @date 08/09/2013
 * 
 */
public class UsuarioDAO{

	public Usuario verifyUsuario(Usuario usuario) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Usuario usuarioTemp = null;

		try {
			String query = "select * from mdl_user where username = ? and password = md5(?)";

			connection = ConnectionFactory.getInstance().getConnection();

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha()
					+ ConstantesSistema.CODE_PASSWORD);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				usuarioTemp = new Usuario(resultSet.getInt("id"),
						resultSet.getString("username"),
						resultSet.getString("password"),
						resultSet.getString("firstname"),
						resultSet.getString("lastname"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException ex) {
				Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
		return usuarioTemp;
	}

	public void updateUsuario(Usuario usuarioSession) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
