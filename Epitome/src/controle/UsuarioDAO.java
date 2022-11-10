package controle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import modelo.Contato;
import modelo.Usuario;
import modelo.Endereco;
import visao.TelaInicialADM;
import visao.TelaInicialVND;
import visao.TelaListarUsuarios;

public class UsuarioDAO {

	private static Connection connection;

	public long insert(Usuario usuario) {
		connection = Conexao.getConnection();
		long id = 0;
		try {
		
		PreparedStatement ps = connection.prepareStatement("insert into Usuario (email_usuario, senha_usuario, cpf_usuario, nome_usuario, cargo_usuario, nascimento_usuario)"
				+ "values ( ? , ? , ? , ? , ? , ? )", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, usuario.getEmail());
			ps.setString(4, usuario.getNome_usuario());
			ps.setString(2, usuario.getSenha_usuario());
			ps.setDate(6, usuario.getNascimento_data());
			ps.setString(3, usuario.getCpf_usuario());
			ps.setString(5, usuario.getCargo());
			ps.execute();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			 generatedKeys.next();
			 id = generatedKeys.getLong(1);
					 
			Conexao.getClose();
			System.out.println("conexao Fechada");
			
			
			
		return id;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro com o BD insert");
			e.printStackTrace();
		}
		return id;
 
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
					usuarioLogado.setId_usuario(id);
					usuarioLogado.setNascimento_data(rs.getDate("nascimento_usuario"));
					Conexao.getClose();
					System.out.println("conexao Fechada");
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
		
		public boolean insertContato(Contato contato, long id) {
			connection = Conexao.getConnection();
			try {
			
				PreparedStatement ps = connection.prepareStatement("insert into  usuario_contato(email, telefone,fk_id_usuario_contato)"
						+ "values ( ? , ? , ? )");

				ps.setString(1, contato.getEmail());
				ps.setString(2, contato.getTelefone());
				ps.setLong(3, id);
				ps.execute();		
				System.out.println("conexao Fechada");
				
				
				
			return true;
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro com o BD insert");
				e.printStackTrace();
			}
			return false;
	 
		}
		
		public boolean insertEndereco(Endereco enderecoA, long id) {
			connection = Conexao.getConnection();
		    Integer n1= (int) id;
			try {
				PreparedStatement psii = connection.prepareStatement("insert into usuario_endereco ( cidade, bairro, rua, numero, fk_id_usuario_endereco)"
					+ "values ( ? , ? , ? , ? , ?  )");
					psii.setString(1, enderecoA.getCidade());
					psii.setString(2, enderecoA.getBairro());
					psii.setString(3, enderecoA.getRua());
					psii.setInt(4, enderecoA.getNumero());
					psii.setLong(5, n1);
					psii.execute();						
				
				Conexao.getClose();
				System.out.println("conexao Fechada");
			return true;
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro com o BD insert");
				e.printStackTrace();
			}
			return false;
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
		
		public static Usuario getUsuario(int id) {
			String s = "select * from usuario where id_usuario = ?";
			Usuario u = new Usuario();
			
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rset = null;
			try {
				conn = Conexao.getConnection();
				ps = (PreparedStatement) conn.prepareStatement(s);
				ps.setInt(1, id);
				
				rset = ps.executeQuery();
				
				while (rset.next()) {
					u.setCargo(rset.getString("cargo_usuario"));
					u.setEmail(rset.getString("email_usuario"));
					u.setSenha_usuario(rset.getString("senha_usuario"));
					u.setCpf_usuario(rset.getString("cpf_usuario"));
					u.setNome_usuario(rset.getString("nome_usuario"));
					u.setNascimento_data(rset.getDate("nascimento_usuario"));
					
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return u;
		}
		
		public List<Endereco> getEnderecos(int id){
			List<Endereco> enderecos = new ArrayList<Endereco>();
			
			String s = "select * from usuario_endereco where fk_id_usuario_endereco = ?";
			
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rset = null;
			try {
				conn = Conexao.getConnection();
				ps = (PreparedStatement) conn.prepareStatement(s);
				ps.setInt(1, id);
				
				rset = ps.executeQuery();
				
				while (rset.next()) {
					Endereco e = new Endereco();
					e.setCidade(rset.getString("cidade"));
					e.setBairro(rset.getString("bairro"));
					e.setRua(rset.getString("rua"));
					e.setNumero(rset.getInt("numero"));
					
					enderecos.add(e);
					
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return enderecos;
		}
		
		public List<Contato> getContatos(int id){
			List<Contato> contatos = new ArrayList<Contato>();
			
			String s = "select * from usuario_contato where fk_id_usuario_contato = ?";
			
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rset = null;
			try {
				conn = Conexao.getConnection();
				ps = (PreparedStatement) conn.prepareStatement(s);
				ps.setInt(1, id);
				
				rset = ps.executeQuery();
				
				while (rset.next()) {
					Contato c = new Contato();
					c.setEmail(rset.getString("email"));
					c.setTelefone(rset.getString("telefone"));
					
					contatos.add(c);
					
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return contatos;
		}
		
		public void update (Usuario usuario, ArrayList<Contato> contatos) {
			String sql  = "UPDATE usuario SET email_usuario = ?, senha_usuario = ?, cargo_usuario = ?, nome_usuario = ?, cpf_usuario = ?, nascimento_usuario = ?" + "WHERE id_usuario = ?";
			String sql1 = "UPDATE usuario_contato SET email = ?, telefone  = ?" + "WHERE id_usuario_contato = ?;";
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
				
				for (Contato contato : contatos) {
				ps = conn.prepareStatement(sql1);
				ps.setString(1, contato.getEmail());
				ps.setString(2, contato.getTelefone());
				ps.setInt(3, contato.getId());
				ps.execute();
				}
				
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
				System.out.println(id);
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
		
		public static boolean validarCPF(String CPF) {
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

		public boolean validarEmail (String Email) {
			Pattern p = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$");//. represents single character
			Matcher m = p.matcher(Email);
			boolean b = m.matches();
			return b;
		}
		
		
		public void updateEndereco (ArrayList<Endereco> enderecos) {
			String sql2 = "UPDATE fornecedor_endereco SET cidade = ?, bairro  = ?, rua = ?, numero = ?"
					+ " WHERE id_fornecedor_endereco = ?;";

			Connection conn = null;
			PreparedStatement ps = null;
			
			try {
				conn = Conexao.getConnection();
				
				for (Endereco endereco : enderecos) {
				ps = conn.prepareStatement(sql2);
				ps.setString(1, endereco.getCidade());
				ps.setString(2, endereco.getBairro());
				ps.setString(3, endereco.getRua());
				ps.setInt(4, endereco.getNumero());
				ps.setInt(5, endereco.getId());
				System.out.println(endereco.getId());
				ps.execute();
				}
				Conexao.getClose();
				
				
				System.out.println("Debug: produto alterado");
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
}




