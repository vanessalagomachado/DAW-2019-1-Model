package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Pais;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class TesteAtualizarPais {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = 
                Persistence.createEntityManagerFactory("DAW-2019-1-ModelPU");
        EntityManager em = emf.createEntityManager();        
        Pais p = em.find(Pais.class, 2);
        p.setNome("Uruguai");
        p.setIso("URU");
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
    }

}
