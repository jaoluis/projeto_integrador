package controle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Fornecedor;
import modelo.ProdVNDQuant;
import modelo.Usuario;
import modelo.Venda;

public class VendaBD {
	
private static Connection connection;
	
	public long insert(Venda venda) {
		connection = Conexao.getConnection();
		long id = 0;
		try {
			PreparedStatement ps = connection.prepareStatement("insert into venda (data_venda, preco_total_venda, pagamento, fk_id_usuario_venda,lucro_venda)"
				+ "values ( ? , ?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS);
				ps.setDate(1, venda.getDataVenda());
				ps.setFloat(2, venda.getTotal());
				ps.setString(3, venda.getPagamento());
				ps.setFloat(4, venda.getFk());
				ps.setFloat(5, venda.getLucro());
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
	
	public boolean insertProdutoVenda(ProdVNDQuant vendaQuant, long id) {
		connection = Conexao.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("insert into produto_venda (quantidade,fk_id_produto,fk_id_venda,lucro_produto_venda)"
				+ "values ( ? , ?,?,?)",Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, vendaQuant.getQuant());
				ps.setInt(2, vendaQuant.getId());
				ps.setLong(3, id);
				ps.setFloat(4, vendaQuant.getLucro());
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
	
	
	public ArrayList<Venda> getListarVendas(){
		
		String sql = "SELECT * FROM venda";
		
		ArrayList<Venda> vendas = new ArrayList<Venda>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rset = null;
		
		try {
			conn = Conexao.getConnection();
			ps = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = ps.executeQuery();
			
			while (rset.next()) {
				Venda venda = new Venda();
				venda.setId(rset.getInt("id_venda"));
				venda.setFk(rset.getInt("fk_id_usuario_venda"));
				venda.setTotal(rset.getFloat("preco_total_venda"));
				venda.setPagamento(rset.getString("pagamento"));
				venda.setDataVenda(rset.getDate("data_venda"));

				vendas.add(venda);
				
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
		 return vendas;
	}
	
	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date(0);
		return dateFormat.format(date);
	}
	
	public void DeleteByID(int id) {
		String sql = "DELETE FROM venda WHERE id_venda = ?";
		String sql1 = "DELETE FROM produto_venda WHERE fk_id_venda = ?";
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		try {
			
			conn = Conexao.getConnection();		
			ps = conn.prepareStatement(sql1);
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

public ArrayList<Venda> getPesquisarPeriodo(String inicio, String fim){
		
		String sql = "SELECT * FROM venda where data_venda BETWEEN ? AND ?";
		
		ArrayList<Venda> vendas = new ArrayList<Venda>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rset = null;
		
		try {
			conn = Conexao.getConnection();
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1,inicio);
			ps.setString(2,fim);
			rset = ps.executeQuery();
			
			while (rset.next()) {
				Venda venda = new Venda();
				venda.setId(rset.getInt("id_venda"));
				venda.setFk(rset.getInt("fk_id_usuario_venda"));
				venda.setTotal(rset.getFloat("preco_total_venda"));
				venda.setPagamento(rset.getString("pagamento"));
				venda.setDataVenda(rset.getDate("data_venda"));

				vendas.add(venda);
				
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
		 return vendas;
	}

}
