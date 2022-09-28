package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modelo.Usuario;

public class ProdutoBD {
	public List<Produto> getListarUsuarios(){
		
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
}
