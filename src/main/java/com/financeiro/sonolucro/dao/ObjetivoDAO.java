package com.financeiro.sonolucro.dao;

import com.financeiro.sonolucro.bean.api.ObjetivoBean;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * @author Rodrigo Romero
 * @since 03/03/2013
 * @version 1.0
 */
@Singleton
public class ObjetivoDAO extends DAO<ObjetivoBean>
{

    public static final String ENTIDADE = "Objetivo";
    
    @PersistenceContext
    private EntityManager manager;

    @Override
    public ObjetivoBean salvar(ObjetivoBean bean) throws SonolucroDAOException
    {
        try
        {
            manager.persist(bean);
            return (ObjetivoBean) manager.merge(bean);
        }
        catch (PersistenceException e)
        {
            throw new SonolucroDAOException(e);
        }
    }

    @Override
    public ObjetivoBean alterar(ObjetivoBean bean) throws SonolucroDAOException
    {
        try
        {
            return manager.merge(bean);
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException(e);
        }
    }

    @Override
    public void apagar(ObjetivoBean objetivo) throws SonolucroDAOException
    {
        try
        {
            objetivo = manager.merge(objetivo);
            manager.remove(objetivo);
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException(e);
        }
    }

    @Override
    public List<ObjetivoBean> listar() throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ObjetivoBean> listar(Long idUsuario) throws SonolucroDAOException
    {
        try
        {
            StringBuilder jpql = new StringBuilder();
            jpql.append("select o from " + ENTIDADE + " o ");
            jpql.append(" where o.usuario = '" + idUsuario + "'");
            jpql.append(" order by o.dataInicialPrevista"); 

            Query query = manager.createQuery(jpql.toString());

            return query.getResultList();
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException(e);
        }
    }
}
