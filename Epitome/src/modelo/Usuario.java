package modelo;

import java.sql.Date;

public class Usuario {
	private int id_usuario;
	private String login_usuario;
	private String senha_usuario;
	private String cpf_usuario;
	private String nome_usuario;
	private java.util.Date nascimento_data;
	private String cargo;
	private String email;

	public Usuario() {

	}

	public Usuario(String email, String senha, String nomeUsuario, String nome, String cpf, Date nascimento_data,
			String cargo) {
		this.login_usuario = nomeUsuario;
		this.senha_usuario = senha;
		this.cpf_usuario = cpf;
		this.nome_usuario = nome;
		this.nascimento_data = nascimento_data;
		this.cargo = cargo;
		this.email = email;
	}

	public String getSenha_usuario() {
		return senha_usuario;
	}

	public void setSenha_usuario(String senha_usuario) {
		this.senha_usuario = senha_usuario;
	}

	public String getLogin_usuario() {
		return login_usuario;
	}

	public void setLogin_usuario(String login_usuario) {
		this.login_usuario = login_usuario;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getCpf_usuario() {
		return cpf_usuario;
	}

	public void setCpf_usuario(String cpf_usuario) {
		this.cpf_usuario = cpf_usuario;
	}

	public String getNome_usuario() {
		return nome_usuario;
	}

	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Date getNascimento_data() {
		return (Date) nascimento_data;
	}

	public void setNascimento_data(java.util.Date dataN) {
		this.nascimento_data = dataN;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}