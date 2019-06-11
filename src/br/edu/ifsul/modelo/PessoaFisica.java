/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author vanes
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class PessoaFisica extends Pessoa implements Serializable{
    
    @Column(name = "rg", nullable = false, length = 10)
    private String rg;
    
    @CPF(message = "CPF inválido")
    @Column(name = "cpf", nullable = false, length = 14)
    private String cpf;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "nascimento", nullable = false)
    private Calendar nascimento;
    
    
    @ManyToMany
    @JoinTable(name="desejos",
            joinColumns = 
                @JoinColumn(name = "pessoafisica", referencedColumnName = "id", nullable = false),
             inverseJoinColumns =       
                @JoinColumn(name="produto", referencedColumnName = "id", nullable = false)
            )
    private Set<Produto> listaDesejo = new HashSet<>();
    
//    @OneToMany(mappedBy = "pessoafisica", cascade = CascadeType.ALL, 
//            orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<Endereco> enderecos = new ArrayList<>();
//    

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Calendar getNascimento() {
        return nascimento;
    }

    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }
    
    public void adicionarProdutoDesejo(Produto p){
        listaDesejo.add(p);
        //p.adicionarPessoaDesejo(this);
    }

//    public List<Endereco> getEnderecos() {
//        return enderecos;
//    }
//    
//    public void addEndereco(Endereco obj){
//        enderecos.add(obj);
//        
//    }
//    
//    public void removeEndereco(Endereco obj){
//        enderecos.remove(obj);
//        
//    }
//    
//    public void removeEndereco(int index){
//        Endereco obj = enderecos.get(index);
//        enderecos.remove(obj);
//
//    }

    public Set<Produto> getListaDesejo() {
        return listaDesejo;
    }

    
}
