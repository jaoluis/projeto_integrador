package controle;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DAO.Conexao;
import DAO.UsuarioDAO;
import modelo.Usuario;
import telas.TelaCadastro;

public class CadastroControle {
	private TelaCadastro View;

	public CadastroControle(TelaCadastro view) {
		this.View = view;
	}
	
	public void salvaUsuario() {
		
		//String id_usuarioStr View.getTxtId().getText();
		String senha_usuario = View.getTxtSenha().getText();
		String login_usuario = View.getTxtEmail().getText();
		String cpf_usuario = View.getTxtCPF().getText();
		String nome_usuario = View.getTxtNome().getText();
		//String cargo = View.getTxtCargo().getText();
		//int id_usuarioInt = Integer.parseInt(id_usuarioStr);
		
		Usuario usuario1 = new Usuario(113, senha_usuario, login_usuario, cpf_usuario, nome_usuario, "ADM");
	
	Connection conexao = null;
	try {
		conexao = new Conexao().getConnection();
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	UsuarioDAO usuarioDao = new UsuarioDAO(conexao);	
	try {
		usuarioDao.insert(usuario1);
		JOptionPane.showMessageDialog(null, "Usuario Salvo com sucesso");
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
		
	}
}
