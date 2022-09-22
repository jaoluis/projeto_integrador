package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
					Conexao.getClose();
					
				}
				
			} catch (Exception e) {
				System.out.println("Debug: Deu erro no listarUsuarios" + e);
			}finally {
				try {
				if(rset!=null) {
					Conexao.getClose();
				}
				if(ps!=null) {
					Conexao.getClose();
				}
				if(conn!=null) {
					Conexao.getClose();;
				}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			 return usuarios;
		}
		
		public void update (Usuario usuario) {
			String sql  = "UPDATE usuario SET email_usuario = ?, senha_usuario = ?, cargo_usuario = ?, nome_usuario = ?, cpf_usuario = ?, nascimento_usuario = ?" + "WHERE id_usuario = ?";
			
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
				System.out.println("Debug: usuario alterado");
				Conexao.getClose();
			} catch (Exception e) {
				System.out.println("Debug: erro ao da update: "+e);
			}finally {
				try {
				if(ps!=null) {
					Conexao.getClose();			
					}
				if (conn!=null) {
					Conexao.getClose();
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
				System.out.println("usuario deletado");
				Conexao.getClose();
				}catch(Exception e) {
					System.out.println("Debug: erro ao da Delete: "+e);
				} finally {
					try {
						if (ps!=null) {
							Conexao.getClose();
						}
						if(conn!=null) {
							Conexao.getClose();
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
		}
		
		public static boolean isCPF(String CPF) {
	        // considera-se erro CPF's formados por uma sequencia de numeros iguais
	        if (CPF.equals("00000000000") ||
	            CPF.equals("11111111111") ||
	            CPF.equals("22222222222") || CPF.equals("33333333333") ||
	            CPF.equals("44444444444") || CPF.equals("55555555555") ||
	            CPF.equals("66666666666") || CPF.equals("77777777777") ||
	            CPF.equals("88888888888") || CPF.equals("99999999999") ||
	            (CPF.length() != 11))
	            return(false);

	        char dig10, dig11;
	        int sm, i, r, num, peso;

	        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
	        try {
	        // Calculo do 1o. Digito Verificador
	            sm = 0;
	            peso = 10;
	            for (i=0; i<9; i++) {
	        // converte o i-esimo caractere do CPF em um numero:
	        // por exemplo, transforma o caractere '0' no inteiro 0
	        // (48 eh a posicao de '0' na tabela ASCII)
	            num = (int)(CPF.charAt(i) - 48);
	            sm = sm + (num * peso);
	            peso = peso - 1;
	            }

	            r = 11 - (sm % 11);
	            if ((r == 10) || (r == 11))
	                dig10 = '0';
	            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

	        // Calculo do 2o. Digito Verificador
	            sm = 0;
	            peso = 11;
	            for(i=0; i<10; i++) {
	            num = (int)(CPF.charAt(i) - 48);
	            sm = sm + (num * peso);
	            peso = peso - 1;
	            }

	            r = 11 - (sm % 11);
	            if ((r == 10) || (r == 11))
	                 dig11 = '0';
	            else dig11 = (char)(r + 48);

	        // Verifica se os digitos calculados conferem com os digitos informados.
	            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
	                 return(true);
	            else return(false);
	                } catch (InputMismatchException erro) {
	                return(false);
	            }
	        }

		public boolean validarCPF (String CPF) {
			Pattern p = Pattern.compile("./^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$/");//. represents single character
			Matcher m = p.matcher(CPF);
			boolean b = m.matches();
			return b;
		}
		public boolean validarEmail (String Email) {
			Pattern p = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$");//. represents single character
			Matcher m = p.matcher(Email);
			boolean b = m.matches();
			return b;
		}
}




