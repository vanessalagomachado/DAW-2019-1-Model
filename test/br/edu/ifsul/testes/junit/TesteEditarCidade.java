/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Cidade;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vanes
 */
public class TesteEditarCidade {
    EntityManager em;
    public TesteEditarCidade() {
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
//        boolean erro = false;
        try{
            Cidade c = em.find(Cidade.class, 2);
            c.setNome(null);

            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
        } catch (Exception e){
//            erro = true;
            e.printStackTrace();
            System.err.println("Erro: "+e);
        }
//        Assert.assertEquals(false, erro);
    }
    
}
