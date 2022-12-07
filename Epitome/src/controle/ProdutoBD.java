package controle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Produto;

public class ProdutoBD {
	
	private static Connection connection;
	
	public long insert(Produto produto) {

		connection = Conexao.getConnection();
		long id = 0;
		try {
			
			//insert no historico do produto
		PreparedStatement ps = connection.prepareStatement("insert into Historico_Produto (nome_produto, estoque_produto, material_produto, dimensoes_produto)"
				+ "values (? , ? , ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, produto.getNomeProduto());
			ps.setInt(2, produto.getQuantidadeEstoque());
			ps.setString(3, produto.getMaterialProduto());
			ps.setString(4, produto.getDimensoesProduto());
						
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

		
		try {

			//insert no preco
			
			 PreparedStatement ps = connection.prepareStatement("insert into preco (preco_custo, preco_venda, data_alteracao, fk_id_historico_produto_preco)"
					+ "values ( ? , ? , ?, ? )");
				ps.setFloat(1, produto.getPrecoCustoProduto());
				ps.setFloat(2, produto.getPrecoVendaProduto());
				ps.setDate(3, now);
				ps.setLong(4, n1);
				ps.execute();
				
				
		//insert no produto
			ps = connection.prepareStatement("insert into produto (nome_produto, estoque_produto, material_produto, dimensoes_produto, fk_id_historico_produto, fk_id_fornecedor_id)"
						+ "values (? , ? , ?, ?, ?,?)");
					ps.setString(1, produto.getNomeProduto());
					ps.setInt(2, produto.getQuantidadeEstoque());
					ps.setString(3, produto.getMaterialProduto());
					ps.setString(4, produto.getDimensoesProduto());
					ps.setInt(5, n1);
					
				    System.out.println(produto.getFornecedor());
					if(produto.getFornecedor()==0) {
						ps.setString(6, null);
					}else {
						ps.setInt(6, produto.getFornecedor());
					}

						
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
		String sql  = "UPDATE historico_produto SET nome_produto = ?, estoque_produto  = ?, material_produto  = ?, dimensoes_produto  = ? WHERE id_historico_produto = ?;";
		String sql1 = "UPDATE produto SET nome_produto = ?, estoque_produto  = ?, material_produto  = ?, dimensoes_produto  = ?, fk_id_fornecedor_id = ? WHERE fk_id_historico_produto = ?;";
		String sql2 = "UPDATE preco SET preco_venda = ?, preco_custo = ?, data_alteracao = ? WHERE fk_id_historico_produto_preco = ?;";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = Conexao.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, produto.getNomeProduto());
			ps.setInt(2, produto.getQuantidadeEstoque());
			ps.setString(3, produto.getMaterialProduto());
			ps.setString(4, produto.getDimensoesProduto());
			ps.setInt(5, produto.getIdProduto());
			ps.execute();
			
			ps = conn.prepareStatement(sql1);
			ps.setString(1, produto.getNomeProduto());
			ps.setInt(2, produto.getQuantidadeEstoque());
			ps.setString(3, produto.getMaterialProduto());
			ps.setString(4, produto.getDimensoesProduto());
			ps.setInt(5, produto.getFornecedor());
			ps.setInt(6, produto.getIdProduto());
			ps.execute();

			ps = conn.prepareStatement(sql2);
			ps.setFloat(1, produto.getPrecoVendaProduto());
			ps.setFloat(2, produto.getPrecoCustoProduto());
			ps.setDate(3, new Date(System.currentTimeMillis()));
			ps.setInt(4, produto.getIdProduto());
			ps.execute();
			
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
	public void DeleteByID(int id) {
		String sql = "DELETE FROM produto WHERE id_produto = ?";
		
		Connection conn = null;
		System.out.println(id);
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
	
	public ArrayList<Produto> getListarProdutos(){
		     
		String sql1 = "select * from produto inner join preco on produto.fk_id_historico_produto  = preco.fk_id_historico_produto_preco;";
		
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rset = null;
		
		try {
			conn = Conexao.getConnection();
			ps = (PreparedStatement) conn.prepareStatement(sql1);
			rset = ps.executeQuery();
			
			while (rset.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(rset.getInt("id_produto"));
				produto.setNomeProduto(rset.getString("nome_produto"));
				produto.setMaterialProduto(rset.getString("material_produto"));
				produto.setQuantidadeEstoque(rset.getInt("estoque_produto"));
				produto.setDimensoesProduto(rset.getString("dimensoes_produto"));
				produto.setPrecoVendaProduto(rset.getFloat("preco_venda"));
				produto.setPrecoCustoProduto(rset.getFloat("preco_custo"));
				produto.setFornecedor(rset.getInt("fk_id_fornecedor_id"));
				
				produtos.add(produto);
				
			}
			
		} catch (Exception e) {
			System.out.println("Debug: Deu erro no listarProdutos1" + e);
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
		
		
		return produtos;

	}
	
	public ArrayList<Produto> getPesquisar(String pesquisar){
	     
		String sql1 = "select * from produto inner join preco on produto.fk_id_historico_produto  = preco.fk_id_historico_produto_preco where nome_produto like ?";
		
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rset = null;
		
		try {
			conn = Conexao.getConnection();
			ps = (PreparedStatement) conn.prepareStatement(sql1);
			ps.setString(1, '%' + pesquisar + '%');
			rset = ps.executeQuery();
			
			while (rset.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(rset.getInt("id_produto"));
				produto.setNomeProduto(rset.getString("nome_produto"));
				produto.setMaterialProduto(rset.getString("material_produto"));
				produto.setQuantidadeEstoque(rset.getInt("estoque_produto"));
				produto.setDimensoesProduto(rset.getString("dimensoes_produto"));
				produto.setPrecoVendaProduto(rset.getFloat("preco_venda"));
				produto.setPrecoCustoProduto(rset.getFloat("preco_custo"));
				produto.setFornecedor(rset.getInt("fk_id_fornecedor_id"));
				
				produtos.add(produto);
				
			}
			
		} catch (Exception e) {
			System.out.println("Debug: Deu erro no listarProdutos1" + e);
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
		
		
		return produtos;

	}
	
	public int getPetPegarQuant(int id){
	     
		String sql1 = "select estoque_produto from produto inner join preco on produto.fk_id_historico_produto  = preco.fk_id_historico_produto_preco where id_produto = ?";
		int quant = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rset = null;
		
		try {
			conn = Conexao.getConnection();
			ps = (PreparedStatement) conn.prepareStatement(sql1);
			ps.setInt(1, id);
			rset = ps.executeQuery();
			rset.next();
			quant = rset.getInt("estoque_produto");
				
			
		} catch (Exception e) {
			System.out.println("Debug: Deu erro no pegar quant" + e);
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
		
		return quant;

	}
	
	
}
		

