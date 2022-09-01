package DAO;


import java.sql.*;

public class Conexao {
		public Connection getConnection() throws SQLException {
				Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/teste", "root", "aluno");
				System.out.println("Conectado � base de dados com sucesso.");
			return conexao;
	}
	}


