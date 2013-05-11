
package com.financeiro.sonolucro.dao;

import com.financeiro.sonolucro.bean.api.DicaBean;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author rodrigo
 * @since 24/12/2012
 * @version 1.0.
 * 
 * 
 */
@Singleton
public class DicaDAO extends DAO<DicaBean>
{

    public static final String ENTIDADE = "Dica"; 
    
    @PersistenceContext
    private EntityManager manager; 
    
    @Override
    public DicaBean salvar(DicaBean dica) throws SonolucroDAOException
    {
        try
        {
            manager.persist(dica);
            return manager.merge(dica); 
        }
        catch(PersistenceException e)
        {
            throw new SonolucroDAOException("Erro ao salvar Dica", e); 
        }
            
    }

    @Override
    public DicaBean alterar(DicaBean dica) throws SonolucroDAOException
    {
        try
        {
            return manager.merge(dica); 
        }
        catch(Exception e)
        {
            throw new SonolucroDAOException("Erro ao alterar dica"); 
        }
    }

    @Override
    public void apagar(DicaBean dica) throws SonolucroDAOException
    {
        try
        {
            dica = manager.merge(dica); 
            manager.remove(dica);
        }
        catch(PersistenceException e)
        {
            throw new SonolucroDAOException("Erro ao apagar Dica."); 
        }
    }
    
    public List<DicaBean> listar() throws SonolucroDAOException
    {
        try
        {
            final String JPQL = "select d from " + ENTIDADE + " d "; 
            Query query = manager.createQuery(JPQL); 
            List<DicaBean> dicas = query.getResultList(); 
            
            return dicas; 
        }
        catch(Exception e)
        {
            throw new SonolucroDAOException("Erro ao listar Dicas."); 
        }
    }

    /*
     * List dicas pelo idioma.
     * @param Tipo long (id idioma)
     */
    @Override
    public List<DicaBean> listar(Long ididioma) throws SonolucroDAOException
    {
        try
        {
            final String JPQL = "select i from " + ENTIDADE + " i "
                    + " where i.idioma.id = '"+ididioma+"'";
            Query query = manager.createQuery(JPQL);
            
            List<DicaBean> dicas = query.getResultList(); 
            
            return dicas; 
        }
        catch(Exception e)
        {
            throw new SonolucroDAOException("Erro ao listar dicas.", e); 
        }
    }
    
}
