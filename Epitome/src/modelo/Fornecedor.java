package modelo;

public class Fornecedor {
	private int id_funcionario;
	private String nome_fornecedor;
	private String cnpj_fornecedor;
	
	//contato//
	private int id_fornecedor_contato;
	private String email;
	private String telefone;
	private int fk_id_fornecedor_contato;
	
	//endereco//
	private int id_fornecedor_endereco;
	private String cidade;
	private String bairro;
	private String rua;
	private String numero;
	private int fk_id_fornecedor_endereco;
	
	
	//construtores//
	public Fornecedor() { };
	
	public Fornecedor(String nome, String cnpj) {
		nome_fornecedor = nome;
		cnpj_fornecedor = cnpj;
	}
	
	//FUNCIONARIO//
	public int getId_funcionario() {
		return id_funcionario;
	}
	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}
	public String getNome_fornecedor() {
		return nome_fornecedor;
	}
	public void setNome_fornecedor(String nome_fornecedor) {
		this.nome_fornecedor = nome_fornecedor;
	}
	public String getCnpj_fornecedor() {
		return cnpj_fornecedor;
	}
	public void setCnpj_fornecedor(String cnpj_fornecedor) {
		this.cnpj_fornecedor = cnpj_fornecedor;
	}
	
	
	//CONTATO FORNECEDOR//
	public int getId_fornecedor_contato() {
		return id_fornecedor_contato;
	}

	public void setId_fornecedor_contato(int id_fornecedor_contato) {
		this.id_fornecedor_contato = id_fornecedor_contato;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getFk_id_fornecedor_contato() {
		return fk_id_fornecedor_contato;
	}

	public void setFk_id_fornecedor_contato(int fk_id_fornecedor_contato) {
		this.fk_id_fornecedor_contato = fk_id_fornecedor_contato;
	}

	
	//ENDERECO FORNECEDOR//
	public int getId_fornecedor_endereco() {
		return id_fornecedor_endereco;
	}

	public void setId_fornecedor_endereco(int id_fornecedor_endereco) {
		this.id_fornecedor_endereco = id_fornecedor_endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getFk_id_fornecedor_endereco() {
		return fk_id_fornecedor_endereco;
	}

	public void setFk_id_fornecedor_endereco(int fk_id_fornecedor_endereco) {
		this.fk_id_fornecedor_endereco = fk_id_fornecedor_endereco;
	}
	
	
}
