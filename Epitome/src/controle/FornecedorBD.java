package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Fornecedor;

public class FornecedorBD {
	private static Connection connection;
	
	public boolean insert(Fornecedor fornecedor) {
		connection = Conexao.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("insert into Fornecedor (nome_fornecedor, cnpj_fornecedor)"
				+ "values ( ? , ? )");
				ps.setString(1, fornecedor.getNome_fornecedor());
				ps.setString(2, fornecedor.getCnpj_fornecedor());
				ps.execute();
			
			PreparedStatement psi = connection.prepareStatement("insert into Fornecedor_Contato (id_fornecedor_contato, email, telefone, fk_id_fornecedor)"
				+ "values ( ? , ? )");
				psi.setInt(1, fornecedor.getId_fornecedor_endereco());
				psi.setString(2, fornecedor.getEmail());
				psi.setString(3, fornecedor.getTelefone());
				psi.setInt(4, fornecedor.getFk_id_fornecedor_contato());
				psi.execute();
			
			PreparedStatement psii = connection.prepareStatement("insert into Fornecedor_Endereco (id_fornecedor_endereco, cidade, bairro, rua, numero, fk_id_fornecedor)"
				+ "values ( ? , ? , ? , ? , ? , ? )");
				psii.setInt(1, fornecedor.getId_fornecedor_endereco());
				psii.setString(2, fornecedor.getCidade());
				psii.setString(3, fornecedor.getBairro());
				psii.setString(4, fornecedor.getRua());
				psii.setString(5, fornecedor.getNumero());
				psii.setInt(6, fornecedor.getFk_id_fornecedor_contato());
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
	
	
	
}
