package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import modelo.Usuario;
import visao.TelaInicialADM;
import visao.TelaListarUsuarios;

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
					TelaInicialADM iniciologin = new TelaInicialADM();
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
		
		public List<Usuario> getListarUsuarios(){
			
			String sql = "SELECT * FROM usuario";
			
			List<Usuario> usuarios = new ArrayList<Usuario>();
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rset = null;
			
			try {
				conn = Conexao.getConnection();
				ps = (PreparedStatement) conn.prepareStatement(sql);
				
				rset = ps.executeQuery();
				
				while (rset.next()) {
					Usuario usuario = new Usuario();
					usuario.setId_usuario(rset.getInt("id_usuario"));
					usuario.setEmail(rset.getString("email_usuario"));
					usuario.setNome_usuario(rset.getString("nome_usuario"));
					usuario.setSenha_usuario(rset.getString("senha_usuario"));
					usuario.setCargo(rset.getString("cargo_usuario"));
					usuario.setCpf_usuario(rset.getString("cpf_usuario"));
					usuario.setNascimento_data(rset.getDate("nascimento_usuario"));
					
					usuarios.add(usuario);
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
				if(rset!=null) {
					rset.close();
				}
				if(ps!=null) {
					ps.close();
				}
				if(conn!=null) {
					conn.close();
				}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			 return usuarios;
		}
		
		public void update (Usuario usuario) {
			String sql  = "UPDATE usuario SET email_usuario = ?, senha_usuario = ?, cargo_usuario = ?, nome_usuario = ?, cpf_usuario = ?, nascimento_usuario = ?" + "WHERE id = ?";
			
			Connection conn = null;
			PreparedStatement ps = null;
			
			try {
				conn = Conexao.getConnection();
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, usuario.getEmail());
				ps.setString(4, usuario.getNome_usuario());
				ps.setString(2, usuario.getSenha_usuario());
				ps.setDate(6, usuario.getNascimento_data());
				ps.setString(5, usuario.getCpf_usuario());
				ps.setString(3, usuario.getCargo());
				
				ps.setInt(7, usuario.getId_usuario());
				ps.execute();
				
			} catch (Exception e) {
				System.out.println("erro ao da update"+e);
			}finally {
				try {
				if(ps!=null) {
					ps.close();;			
					}
				if (conn!=null) {
					conn.close();
				}
				}catch (Exception e2) {
					
				}
		}

	}
		public void DeleteByID(int id) {
			String sql = "DELETE FROM usuario WHERE id_usuario = ?";
			
			Connection conn = null;
			
			PreparedStatement ps = null;
			try {
				conn = Conexao.getConnection();		
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				ps.execute();
				System.out.print(id);
				}catch(Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (ps!=null) {
							ps.close();
						}
						if(conn!=null) {
							conn.close();
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
		}
}




