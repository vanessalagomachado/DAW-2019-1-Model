/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author vanes
 */
@Entity
public class Venda implements Serializable{
    @Id
    @SequenceGenerator(name = "venda_id", allocationSize = 1, sequenceName = "id_venda")
    @GeneratedValue(generator = "venda_id", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Temporal(TemporalType.DATE)
    @Column(name="data", nullable = false)
    private Calendar data;
    
    @Column(name="valor_total", columnDefinition = "numeric(10,2)")
    private Double valorTotal;
    
    @Column(name = "parcelas", nullable = false)
    private Integer parcelas;
    
    @ManyToOne
    @JoinColumn(name = "pessoafisica", referencedColumnName = "id", nullable = false)
    @NotNull(message = "O Cliente deve ser informado")
    @ForeignKey(name = "fk_pessoa_fisica")
    private PessoaFisica cliente;
    
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id", nullable = false)
    @NotNull(message = "O Vendedor deve ser informado")
    @ForeignKey(name = "fk_usuario")
    private Usuario vendedor;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, 
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VendasItens> itens = new ArrayList<>();
    
    @OneToMany(mappedBy = "parcelaID.venda", cascade = CascadeType.ALL, 
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Parcela> listaParcela = new ArrayList<>();
    
    public Venda() {
        valorTotal = 0.0;
        data = Calendar.getInstance();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public PessoaFisica getCliente() {
        return cliente;
    }

    public void setCliente(PessoaFisica cliente) {
        this.cliente = cliente;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public void addItem(VendasItens vi){
        itens.add(vi);
        vi.setVenda(this);
        valorTotal += vi.getValorTotal();
        vi.getProduto().retiraEstoque(vi.getQuantidade());
        
    }
    
    public void removeItem(VendasItens vi){
        itens.remove(vi);
        valorTotal -= vi.getValorTotal();
//        vi.setVenda(null);
    }
    
    public void removeItem(int index){
        VendasItens obj = itens.get(index);
        valorTotal -= obj.getValorTotal();
        itens.remove(obj);
//        obj.setVenda(null);
    }

//    @Override
//    public String toString() {
//        return "Venda{" + "id=" + id + ", data=" + data + ", valorTotal=" + valorTotal + ", parcelas=" + parcelas + ", cliente=" + cliente + ", vendedor=" + vendedor + ", itens=" + itens + '}';
//    }
    
    
    public void gerarParcelas(){
        Double valorParcela = valorTotal / parcelas;
        
        for (int i = 1; i <= parcelas; i++) {
            Parcela p = new Parcela();
            p.setValor(valorParcela);
            //p.setVencimento(Calendar.getInstance());
            Calendar dt_bkp = (Calendar)data.clone();
            //dt_bkp.add(Calendar.DAY_OF_YEAR, (30*i));
            dt_bkp.add(Calendar.MONTH, i);
            p.setVencimento(dt_bkp);
            
            ParcelaID pid = new ParcelaID();
            pid.setNumero(i);
            pid.setVenda(this);
            
            p.setParcelaID(pid);
            
            listaParcela.add(p);
        }
    }
    
    
}
