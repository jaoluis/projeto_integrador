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

	public boolean insert(Usuario usuario) throws SQLException {

		PreparedStatement ps = connection.prepareStatement(
				"insert into usuario(id_usuario, email_usuario, senha_usuario, cpf_usuario, nome_usuario, cargo_usuario, nascimento_usuario, fk_id_usuario_endereco) values(112,'" + usuario.getEmail() + "', '"
						+ usuario.getSenha_usuario() + "', '" + usuario.getCpf_usuario() + "','"
						+ usuario.getNome_usuario() + "', '" + usuario.getCargo() + "', '"+ usuario.getNascimento_data()+"',3);");
		ps.execute();
		connection.close();

		return false;

	}

}
