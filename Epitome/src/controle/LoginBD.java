package controle;

import java.sql.Connection;

import modelo.Usuario;

public class LoginBD {

	public Usuario efetuarLogin(String email, char[] senha) {
		
		Connection conexao = Conexao.getConnection();

		return null;
	}

}
