package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import modelo.Contato;
import modelo.Endereco;
import modelo.Fornecedor;
import modelo.Produto;

public class FornecedorBD {
	private static Connection connection;
	
	public long insert(Fornecedor fornecedor) {
		connection = Conexao.getConnection();
		long id = 0;
		try {
			PreparedStatement ps = connection.prepareStatement("insert into Fornecedor (nome_fornecedor, cnpj_fornecedor)"
				+ "values ( ? , ? )",Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, fornecedor.getNome_fornecedor());
				ps.setString(2, fornecedor.getCnpj_fornecedor());
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
	
	public boolean insertContato( Contato contatoA, long id ) {
		connection = Conexao.getConnection();
	    Integer n1= (int) id;
		try {
			
			PreparedStatement psi = connection.prepareStatement("insert into Fornecedor_Contato ( email, telefone, fk_id_fornecedor)"
				+ "values ( ? , ?, ?)");
				psi.setString(1, contatoA.getEmail());
				psi.setString(2, contatoA.getTelefone());
				psi.setLong(3, n1);
				psi.execute();
				
				Conexao.getClose();
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
			PreparedStatement psii = connection.prepareStatement("insert into Fornecedor_Endereco (cidade, bairro, rua, numero, fk_id_fornecedor)"
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
	
	
	
	 public static boolean isCNPJ(String CNPJ) {
		// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
		    if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
		        CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
		        CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
		        CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
		        CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
		       (CNPJ.length() != 14))
		       return(false);

		    char dig13, dig14;
		    int sm, i, r, num, peso;

		// "try" - protege o código para eventuais erros de conversao de tipo (int)
		    try {
		// Calculo do 1o. Digito Verificador
		      sm = 0;
		      peso = 2;
		      for (i=11; i>=0; i--) {
		// converte o i-ésimo caractere do CNPJ em um número:
		// por exemplo, transforma o caractere '0' no inteiro 0
		// (48 eh a posição de '0' na tabela ASCII)
		        num = (int)(CNPJ.charAt(i) - 48);
		        sm = sm + (num * peso);
		        peso = peso + 1;
		        if (peso == 10)
		           peso = 2;
		      }

		      r = sm % 11;
		      if ((r == 0) || (r == 1))
		         dig13 = '0';
		      else dig13 = (char)((11-r) + 48);

		// Calculo do 2o. Digito Verificador
		      sm = 0;
		      peso = 2;
		      for (i=12; i>=0; i--) {
		        num = (int)(CNPJ.charAt(i)- 48);
		        sm = sm + (num * peso);
		        peso = peso + 1;
		        if (peso == 10)
		           peso = 2;
		      }

		      r = sm % 11;
		      if ((r == 0) || (r == 1))
		         dig14 = '0';
		      else dig14 = (char)((11-r) + 48);

		// Verifica se os dígitos calculados conferem com os dígitos informados.
		      if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
		         return(true);
		      else return(false);
		    } catch (InputMismatchException erro) {
		        return(false);
		    }
		  }

		  public static String imprimeCNPJ(String CNPJ) {
		// máscara do CNPJ: 99.999.999.9999-99
		    return(CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "." +
		      CNPJ.substring(5, 8) + "." + CNPJ.substring(8, 12) + "-" +
		      CNPJ.substring(12, 14));
		  }
	
		  
			public boolean validarEmail (String Email) {
				Pattern p = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$");//. represents single character
				Matcher m = p.matcher(Email);
				boolean b = m.matches();
				return b;
			}
	
			
			public List<Fornecedor> getListarFornecedores(){
			     
				String sql1 = "select id_fornecedor,nome_fornecedor, cnpj_fornecedor from fornecedor;";
				
				List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rset = null;
				try {
					conn = Conexao.getConnection();
					ps = (PreparedStatement) conn.prepareStatement(sql1);
					
					rset = ps.executeQuery();
					
					while (rset.next()) {
						Fornecedor fornecedor = new Fornecedor();
						fornecedor.setId_fornecedor(rset.getInt("id_fornecedor"));
						fornecedor.setNome_fornecedor(rset.getString("nome_fornecedor"));
						fornecedor.setCnpj_fornecedor(rset.getString("cnpj_fornecedor"));
						
						fornecedores.add(fornecedor);
						

						
					}
					
					Conexao.getClose();
					System.out.println("conexao Fechada");
					
				} catch (Exception e) {
					System.out.println("Debug: Deu erro no listarFornecedor1" + e);
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
				
				
				return fornecedores;
			}
			
			public Fornecedor fornecedorDadosUnico(int id) {
				
				String sql1 = "select *\r\n"
						+ "from fornecedor inner join  fornecedor_endereco \r\n"
						+ "on fornecedor.id_fornecedor  = fornecedor_endereco.fk_id_fornecedor \r\n"
						+ "inner join fornecedor_contato on fornecedor.id_fornecedor = fornecedor_contato.fk_id_fornecedor where id_fornecedor = ?;\r\n";
				Fornecedor fornecedor = new Fornecedor();
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rset = null;
				try {
					conn = Conexao.getConnection();
					ps = (PreparedStatement) conn.prepareStatement(sql1);
					ps.setInt(1, id);
					
					rset = ps.executeQuery();
					
					while (rset.next()) {
						
						fornecedor.setId_fornecedor(rset.getInt("id_fornecedor"));
						fornecedor.setNome_fornecedor(rset.getString("nome_fornecedor"));
						fornecedor.setCnpj_fornecedor(rset.getString("cnpj_fornecedor"));
						fornecedor.setCidade(rset.getString("cidade"));
						fornecedor.setBairro(rset.getString("bairro"));
						fornecedor.setRua(rset.getString("rua"));
						fornecedor.setNumero(rset.getString("numero"));
						fornecedor.setEmail(rset.getString("email"));
						fornecedor.setTelefone(rset.getString("telefone"));
						
					}
					
				} catch (Exception e) {
					System.out.println("Debug: Deu erro no listarFornecedorEspecfico" + e);
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
				
				
				return fornecedor;
			}
			
			public List<Endereco> getEnderecos(int id){
				List<Endereco> enderecos = new ArrayList<Endereco>();
				
				String s = "select * from fornecedor_endereco where fk_id_fornecedor = ?";
				
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
						e.setId(rset.getInt("id_fornecedor_endereco"));
						System.out.println((rset.getInt("id_fornecedor_endereco")));
						
						enderecos.add(e);
						
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
				return enderecos;
			}
			
			public List<Contato> getContatos(int id){
				List<Contato> contatos = new ArrayList<Contato>();
				
				String s = "select * from fornecedor_contato where fk_id_fornecedor = ?";
				
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
						c.setId(rset.getInt("id_fornecedor_contato"));
						
						contatos.add(c);
						
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
				return contatos;
			}
			
				
			public void DeleteByID(int id) {
				String sql1 = "DELETE FROM fornecedor_contato WHERE fk_id_fornecedor = ?";
				String sql = "DELETE FROM fornecedor WHERE id_fornecedor = ?";
				String sql2 = "DELETE FROM fornecedor_endereco WHERE fk_id_fornecedor = ?";
				
				Connection conn = null;
				
				PreparedStatement ps = null;
				try {
					
					conn = Conexao.getConnection();		
					ps = conn.prepareStatement(sql1);
					ps.setInt(1, id);
					ps.execute();
					
					conn = Conexao.getConnection();		
					ps = conn.prepareStatement(sql2);
					ps.setInt(1, id);
					ps.execute();
					
					conn = Conexao.getConnection();		
					ps = conn.prepareStatement(sql);
					ps.setInt(1, id);
					ps.execute();
					
					System.out.println("fornecedor deletado");
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
			
			
			public static boolean VRFornR(int id){
			     
				String sql1 = "select id_fornecedor from fornecedor;";
				boolean r = false;
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rset = null;
				try {
					conn = Conexao.getConnection();
					ps = (PreparedStatement) conn.prepareStatement(sql1);
					
					rset = ps.executeQuery();
					
					while (rset.next()) {
						Fornecedor fornecedor = new Fornecedor();
						fornecedor.setId_fornecedor(rset.getInt("id_fornecedor"));
						if(rset.getInt("id_fornecedor")==id) {
							r = true;
						}

						
						

						
					}
					
					Conexao.getClose();
					System.out.println("conexao Fechada");
					
				} catch (Exception e) {
					System.out.println("Debug: Deu erro no listarFornecedor1" + e);
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
				
				
				return r;
			}
			
			
			
			public void update (Fornecedor fornecedor, ArrayList<Contato> contatos) {
				String sql  = "UPDATE fornecedor SET nome_fornecedor = ?, cnpj_fornecedor  = ?" + "WHERE id_fornecedor = ?;";
				String sql1 = "UPDATE fornecedor_contato SET email = ?, telefone  = ?" + "WHERE id_fornecedor_contato = ?;";

				Connection conn = null;
				PreparedStatement ps = null;
				
				try {
					conn = Conexao.getConnection();
					ps = conn.prepareStatement(sql);
					ps.setString(1, fornecedor.getNome_fornecedor());
					ps.setString(2, fornecedor.getCnpj_fornecedor());
					ps.setInt(3, fornecedor.getId_fornecedor());
					System.out.println(fornecedor.getId_fornecedor());
					ps.execute();
					
					
					for (Contato contato : contatos) {
					ps = conn.prepareStatement(sql1);
					ps.setString(1, contato.getEmail());
					ps.setString(2, contato.getTelefone());
					ps.setInt(3, contato.getId());
					System.out.println(contato.getId());
					ps.execute();
					}
					
					
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
			
			public void update1 (ArrayList<Endereco> enderecos) {
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
	

