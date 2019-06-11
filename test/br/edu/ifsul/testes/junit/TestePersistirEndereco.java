/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Endereco;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vanes
 */
public class TestePersistirEndereco {
    
    EntityManager em;
    
    public TestePersistirEndereco() {
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
            Endereco e = new Endereco();
            e.setNickname("apto");
            e.setEndereco("Av. Brasil");
            e.setNumero("1000");
            e.setComplemento("apto 100");
            e.setCidade(em.find(Cidade.class, 1));
            
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            
        }catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
        }
    }
    
}
