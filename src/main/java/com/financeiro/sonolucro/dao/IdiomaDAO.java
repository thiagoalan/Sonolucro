
package com.financeiro.sonolucro.dao;

import com.financeiro.sonolucro.bean.api.IdiomaBean;
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
 * @since 25/12/2012
 * @version 1.0.0
 */

@Singleton
public class IdiomaDAO extends DAO<IdiomaBean>
{

    public static final String ENTIDADE = "Idioma"; 
    
    @PersistenceContext
    private EntityManager manager;

    @Override
    public IdiomaBean salvar(IdiomaBean bean) throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IdiomaBean alterar(IdiomaBean bean) throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void apagar(IdiomaBean bean) throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<IdiomaBean> listar() throws SonolucroDAOException
    {
        try
        {
            String jpql = "select i from Idioma i";
            Query query = manager.createQuery(jpql);

            return query.getResultList();
        }
        catch (PersistenceException e)
        {
            throw new SonolucroDAOException("Erro ao listar idiomas.");
        }
    }

    @Override
    public List<IdiomaBean> listar(Long value) throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
