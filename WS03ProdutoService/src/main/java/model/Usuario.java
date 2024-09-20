package model;



public class Usuario {
	private int id;
	private String nome;
	private String email;
	private String senha;
	private String tags;
	
	public Usuario() {
		id = -1;
		nome = "";
		email = "";
		senha = "";
		tags = "";
	}

	public Usuario(int id, String nome, String email, String senha, String tags) {
		setId(id);
		setNome(nome);
		setEmail(email);
		setSenha(senha);
		setTags(tags);
	}		
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getNome() {
		return nome;
	}

	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

	
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}


	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "\nUsuario: " + nome + 
				"\nE-mail: " + email + 
				"\nSenha: " + senha + 
				"\nTags: "+ tags;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Usuario) obj).getId());
	}	
}