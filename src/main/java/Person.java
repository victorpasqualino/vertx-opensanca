
public class Person {

	private String nome;
	private String idade;
	private String cpf;

	public Person(String nome, String idade, String cpf) {
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the idade
	 */
	public String getIdade() {
		return idade;
	}

	/**
	 * @param idade the idade to set
	 */
	public void setIdade(String idade) {
		this.idade = idade;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Person[nome=" + nome + ", idade=" + idade + ", cpf=" + cpf + "]";
	}

}