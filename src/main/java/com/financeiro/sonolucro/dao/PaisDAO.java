
package com.financeiro.sonolucro.dao;

import com.financeiro.sonolucro.bean.api.PaisBean;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author rodrigo
 * @since 30/12/2012
 * @version 1.0.0
 * 
 */

@Singleton
public class PaisDAO extends DAO<PaisBean>
{

    public static final String ENTIDADE = "Pais"; 
    
    @PersistenceContext 
    private EntityManager manager; 
    
    @Override
    public PaisBean salvar(PaisBean pais) throws SonolucroDAOException
    {
        try
        {
            manager.persist(pais);
            return manager.merge(pais); 
        }
        catch(PersistenceException e)
        {
            throw new SonolucroDAOException("Erro ao salvar pais"); 
        }
    }

    @Override
    public PaisBean alterar(PaisBean bean) throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void apagar(PaisBean bean) throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PaisBean> listar() throws SonolucroDAOException
    {
        try
        {
            final String JPQL = "select p from " + ENTIDADE + " p ";
            Query query = manager.createQuery(JPQL); 
            List<PaisBean> paises = query.getResultList(); 
            
            return paises; 
        }
        catch(Exception e)
        {
            throw new SonolucroDAOException("Erro ao listar paises.", e); 
        }
    }

    @Override
    public List<PaisBean> listar(Long id) throws SonolucroDAOException
    {
        return null; 
    }
    
    public PaisBean buscarPais(Long id) throws SonolucroDAOException
    {
        try
        {
            final String JPQL = "select p from " + ENTIDADE + " p "
                    + "where p.id = '" +id+ "'"; 
            Query query = manager.createQuery(JPQL); 
            
            PaisBean pais = (PaisBean) query.getSingleResult(); 
            
            return pais;
            
        }
        catch(NoResultException e)
        {
            throw new SonolucroDAOException(e); 
        }
    }
}
