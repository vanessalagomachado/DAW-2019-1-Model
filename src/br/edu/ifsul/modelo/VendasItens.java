/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import br.edu.ifsul.modelo.Produto;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;


/**
 *
 * @author vanes
 */
@Entity
@Table(name="vendas_itens")
public class VendasItens implements Serializable{
    @Id
    @SequenceGenerator(name = "vendas_itens_id", sequenceName = "vendas_itens_id", allocationSize = 1)
    @GeneratedValue(generator = "vendas_itens_id", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotNull(message = "Informe a quantidade desejada")
    @Column(name = "quantidade", columnDefinition = "numeric(10,2)", nullable = false)
    private Double quantidade;
    
    @Column(name = "valor_unitario", columnDefinition = "numeric(10,2)", nullable = false)
    private Double valorUnitario;
    
    @Column(name="valor_total", columnDefinition = "numeric(10,2)")
    private Double valorTotal;
    
    @ManyToOne
    @JoinColumn(name = "produto", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_produto")
    private Produto produto;
    
    @ManyToOne
    @JoinColumn(name = "venda", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_venda")
    private Venda venda;
    

    public VendasItens() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

    public Double getValorUnitario() {
        return valorUnitario;
    }

//    public void setValorUnitario(Double valorUnitario) {
//        this.valorUnitario = valorUnitario;
//    }

    public Double getValorTotal() {
        return valorTotal;
    }

//    public void setValorTotal(Double valorTotal) {
//        this.valorTotal = valorTotal;
//    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        valorUnitario = produto.getPreco();
        valorTotal = quantidade * valorUnitario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final VendasItens other = (VendasItens) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

//    @Override
//    public String toString() {
//        return "VendasItens{" + "id=" + id + ", quantidade=" + quantidade + ", valorUnitario=" + valorUnitario + ", valorTotal=" + valorTotal + ", produto=" + produto + ", venda=" + venda + '}';
//    }
    
    
}
