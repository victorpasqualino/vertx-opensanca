package br.com.vertx.examples4;

import java.time.LocalDate;

import br.com.vertx.user.User;

public class InsertUserDTO {

    private String nome;
    private String documento;
    private LocalDate dataNascimento;

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * @return the dataNascimento
     */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public User toUser() {
        return new User(nome, documento, dataNascimento);
	}
	
	@Override
	public String toString() {
		return "InsertUserDTO[nome=" + nome + ", documento=" + documento + ", dataNascimento" + dataNascimento + "]";
	}

}