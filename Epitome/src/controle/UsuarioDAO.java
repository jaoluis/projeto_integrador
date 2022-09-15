package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import modelo.Usuario;
import visao.TelaInicialADM;

public class UsuarioDAO {

	private static Connection connection;

	public boolean insert(Usuario usuario) {
		connection = Conexao.getConnection();
		try {
		
		PreparedStatement ps = connection.prepareStatement("insert into Usuario (email_usuario, senha_usuario, cpf_usuario, nome_usuario, cargo_usuario, nascimento_usuario)"
				+ "values ( ? , ? , ? , ? , ? , ? )");
			ps.setString(1, usuario.getEmail());
			ps.setString(4, usuario.getNome_usuario());
			ps.setString(2, usuario.getSenha_usuario());
			ps.setDate(6, usuario.getNascimento_data());
			ps.setString(3, usuario.getCpf_usuario());
			ps.setString(5, usuario.getCargo());
			ps.execute();
			Conexao.getClose();
			System.out.println("conexao Fechada");
		return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro com o BD insert");
			e.printStackTrace();
		}
		return false;
 
	}
		public Usuario verificacao(Usuario usuario) {
			connection = Conexao.getConnection();
			try {
				PreparedStatement ps = connection.prepareStatement("select * from usuario where email_usuario like ? and senha_usuario like ?");

				ps.setString(1, usuario.getEmail());

				ps.setString(2, usuario.getSenha_usuario());

				ResultSet rs = ps.executeQuery();
				Usuario usuarioLogado = new Usuario(); // pegar os dados da consulta

				while (rs.next()) {
					int id = rs.getInt("id_usuario");
					String login = rs.getString("email_usuario");
					String nome = rs.getString("nome_usuario");
					String senha = rs.getString("senha_usuario");
					String cargo = rs.getString("cargo_usuario");
					String cpf = rs.getString("cpf_usuario");
					
					usuarioLogado.setEmail(senha);
					usuarioLogado.setNome_usuario(nome);
					usuarioLogado.setSenha_usuario(senha);
					usuarioLogado.setCargo(cargo);
					usuarioLogado.setCpf_usuario(cpf);
					TelaInicialADM iniciologin = new TelaInicialADM(usuarioLogado);
					Conexao.getClose();
					System.out.println("conexao Fechada");
					iniciologin.setVisible(true);
					return usuarioLogado;
					
				}
				Conexao.getClose();
				System.out.println("conexao Fechada");
				
				//Joption quando dá erro no logar
				
				JOptionPane.showMessageDialog(null, "Erro ao logar");
			} catch (SQLException e) {
				System.out.println("Deu erro na verificação" + e);
				e.printStackTrace();
			}
			return null;

		}

	}




