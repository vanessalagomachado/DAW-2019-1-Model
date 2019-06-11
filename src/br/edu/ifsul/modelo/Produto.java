/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author vanes
 */
@Entity
public class Produto implements Serializable{
    @Id
    @SequenceGenerator(name = "produto_id", allocationSize = 1, sequenceName = "id_produto")
    @GeneratedValue(generator = "produto_id", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "nome", nullable = false, length = 30)
    @NotBlank(message = "Campo nome precisa ser informado")
    @Length(max=30, message = "Campo nome não pode conter mais que {max} caracteres")
    private String nome;
    
    @Column(name="preco", nullable = false, columnDefinition = "numeric(10,2)")
    @Min(value = 0, message = "O preço não pode ser negativo")
    private Double preco;
    
    @Column(name = "descricao", nullable = false, columnDefinition = "text")
    private String descricao;
    
    @Column(name="quantidade_estoque", nullable = false, columnDefinition = "numeric(10,2)")
    private Double quantidadeEstoque;

    @ManyToMany
    @JoinTable(name="desejos",
            joinColumns = 
                @JoinColumn(name = "produto", referencedColumnName = "id", nullable = false),
             inverseJoinColumns =       
                @JoinColumn(name="pessoafisica", referencedColumnName = "id", nullable = false)
            )
    private Set<PessoaFisica> listaDesejam = new HashSet<>();
    
    public Produto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Double quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public void adicionarPessoaDesejo(PessoaFisica p){
        listaDesejam.add(p);
    }
    
    public void removerPessoaDesejo(PessoaFisica p){
        listaDesejam.remove(p);
    }
    
    public void retiraEstoque(Double qnt){
        quantidadeEstoque-=qnt;
    }
    
}
