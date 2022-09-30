package controle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Produto;
import modelo.Usuario;
import visao.TelaInicialADM;

public class ProdutoBD {
	
	private static Connection connection;

	public int getID(){
	     
		String sql = "SELECT * FROM produto";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rset = null;
		int id = 0;
		
		try {
			conn = Conexao.getConnection();
			ps = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = ps.executeQuery();
			
			while (rset.next()) {
				id = rset.getInt("id_historico_produto");


				
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
		 return id;
	}
	
	
	public long insert(Produto produto) {
		Date now = new Date(System.currentTimeMillis());
		connection = Conexao.getConnection();
		long id = 0;
		try {
			
			//insert no historico do produto
		PreparedStatement ps = connection.prepareStatement("insert into Historico_Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto)"
				+ "values (? , ? , ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, produto.getNomeProduto());
			ps.setInt(2, produto.getQuantidadeEstoque());
			ps.setString(3, produto.getMaterialProduto());
			ps.setString(4, produto.getDimencoesProduto());
						
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
	

	
	
	
	public boolean insert2(Produto produto, long id) {
		Date now = new Date(System.currentTimeMillis());
		connection = Conexao.getConnection();

		    Integer n1= (int) id;
		    System.out.println(n1);
		try {

			//insert no preco
			 PreparedStatement ps = connection.prepareStatement("insert into preco (preco_custo, preco_venda, data_alteracao, fk_id_historico_produto)"
					+ "values ( ? , ? , ?, ? )");
				ps.setFloat(1, produto.getPrecoCustoProduto());
				ps.setFloat(2, produto.getPrecoVendaProduto());
				ps.setDate(3, now);
				ps.setLong(4, n1);
		//insert no produto
			ps = connection.prepareStatement("insert into produto (nome_produto, estoque_produto, material_produto, dimensoes_produto, fk_id_historico_produto)"
						+ "values (? , ? , ?, ?, ?)");
					ps.setString(1, produto.getNomeProduto());
					ps.setInt(2, produto.getQuantidadeEstoque());
					ps.setString(3, produto.getMaterialProduto());
					ps.setString(4, produto.getDimencoesProduto());
					ps.setInt(5, n1);
						
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
	
	
	
	public void update (Produto produto) {
		String sql  = "UPDATE usuario SET (nome_usuario = ?, estoque_produto  = ?, material_produto  = ?, dimensoes_produto  = ?" + "WHERE id_produto = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = Conexao.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, produto.getNomeProduto());
			ps.setInt(2, produto.getQuantidadeEstoque());
			ps.setString(3, produto.getMaterialProduto());
			ps.setString(4, produto.getDimencoesProduto());
			ps.setInt(5, produto.getIdProduto());
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
		String sql = "DELETE FROM produto WHERE id_produto = ?";
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		try {
			conn = Conexao.getConnection();		
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			System.out.println("produto deletado");
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
	
	public int getListarProdutos(){
		     
		String sql = "SELECT * FROM produto";
		
		List<Produto> produtos = new ArrayList<Produto>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rset = null;
		int id = 0;
		
		try {
			conn = Conexao.getConnection();
			ps = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = ps.executeQuery();
			
			while (rset.next()) {
				id = rset.getInt("id_historico_produto");


				
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
		 return id;
	}
}
