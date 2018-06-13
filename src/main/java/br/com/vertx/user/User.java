package br.com.vertx.user;

import java.time.LocalDate;
import java.util.UUID;

public class User {

    private String id;
    private String nome;
    private String documento;
    private LocalDate dataNascimento;

    public User() {
    }

    public User(String nome, String documento, LocalDate dataNascimento) {
        this(UUID.randomUUID().toString(), nome, documento, dataNascimento);
    }

    public User(String id, String nome, String documento, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * @return the dataNascimento
     */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}