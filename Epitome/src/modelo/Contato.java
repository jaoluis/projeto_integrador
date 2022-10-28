package modelo;

public class Contato {
	private String email;
	private String telefone;
	
	public Contato() {
		this.email = "";
		this.telefone = "";	
	}
	
	public Contato(String email, String telefone) {
		super();
		this.email = email;
		this.telefone = telefone;
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

	public boolean isBlank() {
		if (this.email.equals("")) {
			return true;
		}
		if (this.telefone.equals("")) {
			return true;
		}
		return false;
	}
	
	
}
