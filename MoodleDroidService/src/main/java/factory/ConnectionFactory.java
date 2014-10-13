package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.ConstantesSistema;

/**
 * @author Felipe
 */
public class ConnectionFactory {
	private static ConnectionFactory conectionFactory;
	private Connection connection;

	public static ConnectionFactory getInstance() {
		if (conectionFactory == null) {
			conectionFactory = new ConnectionFactory();
		}
		return conectionFactory;
	}

	public Connection getConnection() {
		if (connection == null) {
			try {
				String connectionUrl = ConstantesSistema.CONNECTION_URL;
				String connectionUser = ConstantesSistema.USER;
				String connectionPassword = ConstantesSistema.PASSWORD;
				
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				connection = DriverManager.getConnection(connectionUrl, connectionUser,
						connectionPassword);
			} catch (SQLException ex) {
				//TODO tratar exceção
				ex.printStackTrace();
			}
		}
		return connection;
	}
}
