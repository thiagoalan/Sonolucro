
package com.financeiro.sonolucro.dao;

import com.financeiro.sonolucro.bean.api.AcaoBean;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Rodrigo Romero
 * @since 23/03/2013
 * @version 1.0
 */

//@Singleton
public class AcaoDAO extends DAO<AcaoBean>
{
    public static final String ENTIDADE = "Acao"; 
    
  //  @PersistenceContext
    private EntityManager  manager; 
    
    @Override
    public AcaoBean salvar(AcaoBean acao) throws SonolucroDAOException
    {
        try
        {
            manager.persist(acao);
            return manager.merge(acao); 
        }
        catch(PersistenceException e)
        {
            throw new SonolucroDAOException(e); 
        }
    }

    @Override
    public AcaoBean alterar(AcaoBean acao) throws SonolucroDAOException
    {
        try
        {
            return manager.merge(acao); 
        }
        catch(Exception e)
        {
            throw new SonolucroDAOException(e); 
        }
    }

    @Override
    public void apagar(AcaoBean acao) throws SonolucroDAOException
    {
        try
        {
           acao = manager.merge(acao);
           manager.remove(acao);
        }
        catch(Exception e)
        {
            throw new SonolucroDAOException(e); 
        }
    }

    @Override
    public List<AcaoBean> listar() throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<AcaoBean> listar(Long idUsuario) throws SonolucroDAOException
    {
        try
        {
            StringBuilder jpql = new StringBuilder(); 
            jpql.append("select a from " + ENTIDADE + " a "); 
            jpql.append("where a.usuario.id = '"+idUsuario+"'");
            
            Query query = manager.createQuery(jpql.toString());
            
            return query.getResultList(); 
                    
        }
        catch(Exception e)
        {
            throw new SonolucroDAOException(e); 
        }
    }
    
}
