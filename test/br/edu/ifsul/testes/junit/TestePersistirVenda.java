/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.modelo.Venda;
import br.edu.ifsul.modelo.VendasItens;

import java.util.Calendar;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vanes
 */
public class TestePersistirVenda {
    
    EntityManager em;
    
    public TestePersistirVenda() {
        
    }
    
    @Before
    public void setUp() {
        em = EntityManagerUtil.getEntityManager();
    }
    
    @After
    public void tearDown() {
        em.close();
    }
    
    
    @Test
    public void teste(){
        try{
        VendasItens vi1 = new VendasItens();
        vi1.setQuantidade(2.0);
        vi1.setProduto(em.find(Produto.class, 2));
//        System.out.println(vi1);
        
        Venda venda = new Venda();
        //Calendar hoje = Calendar.getInstance();
        //venda.setData(hoje);
        venda.setParcelas(4);
        venda.setCliente(em.find(PessoaFisica.class, 6));
        venda.setVendedor(em.find(Usuario.class, 9));
        venda.addItem(vi1);
        
//        vi1.setQuantidade(3.0);
//            System.out.println("Venda-Item após: "+vi1);
//            System.out.println("Venda: "+venda);
        
        venda.gerarParcelas();
        
        em.getTransaction().begin();
        em.persist(venda);
//        em.persist(vi1);
        em.getTransaction().commit();
        
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERRO: "+e);
        }
    }
}
