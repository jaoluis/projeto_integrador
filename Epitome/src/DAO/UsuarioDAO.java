package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Usuario;

public class UsuarioDAO {
	
	private final Connection connection;
	
	public UsuarioDAO(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}
	
	public void insert(Usuario usuario) throws SQLException {
			

			PreparedStatement ps = connection.prepareStatement("insert into usuario(id_usuario, login_usuario, senha_usuario, cpf_usuario, nome_usuario, cargo) values('"+usuario.getId_usuario()+"', '"+usuario.getLogin_usuario()+"', '"+usuario.getSenha_usuario()+"', '"+usuario.getCpf_usuario()+"','"+usuario.getNome_usuario()+"', '"+usuario.getCargo()+"');");
			ps.execute();
			connection.close();
			
	}
	
}
