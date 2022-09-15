package controle;

import java.sql.*;

public class Conexao {

	private static Connection conexao;

	public static Connection getConnection() {

		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "aluno");
		} catch (SQLException e) {
			System.out.println("Não foi possivel acessar o Banco de dados");
			e.printStackTrace();
		}
		System.out.println("Conectado � base de dados com sucesso.");
		return conexao;
	}

	public static void getClose() {
		try {
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
