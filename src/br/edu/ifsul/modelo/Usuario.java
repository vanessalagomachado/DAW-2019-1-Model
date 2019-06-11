/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author vanes
 */
@Entity
public class Usuario extends PessoaFisica implements Serializable{
    @Column(name="nome_usuario", nullable = false, length = 20)
    @NotBlank(message = "Campo nome do usuário deve ser informado")
    @Length(max=20, message = "Campo nome do usuário não pode conter mais que {max} caracteres")
    private String nomeUsuario;
    @Column(name="senha", nullable = false, length = 10)
    @Length(max=10, message = "Campo senha não pode conter mais que {max} caracteres")
    private String senha;
    @Column(name="ativo", nullable = false)
    private Boolean ativo;

    public Usuario() {
        ativo = Boolean.TRUE;
    }
    
    

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    
}
