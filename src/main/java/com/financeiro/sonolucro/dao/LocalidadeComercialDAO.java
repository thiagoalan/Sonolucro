package com.financeiro.sonolucro.dao;

import com.financeiro.sonolucro.bean.api.LocalidadeComercialBean;
import com.financeiro.sonolucro.bean.api.PessoaBean;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * @author rodrigo
 * @since 16/02/2013
 * @version 1.0
 */
@Singleton
public class LocalidadeComercialDAO extends DAO<LocalidadeComercialBean>
{

    protected static final String ENTIDADE = "LocalidadeComercial";
    @PersistenceContext
    private EntityManager manager;

    @Override
    public LocalidadeComercialBean salvar(LocalidadeComercialBean localidadeComercial) throws SonolucroDAOException
    {
        try
        {
            manager.persist(localidadeComercial);
            return (LocalidadeComercialBean) manager.merge(localidadeComercial);
        }
        catch (PersistenceException e)
        {
            throw new SonolucroDAOException(e);
        }
    }

    @Override
    public LocalidadeComercialBean alterar(LocalidadeComercialBean localidadeComercial) throws SonolucroDAOException
    {
        try
        {
            return (LocalidadeComercialBean) manager.merge(localidadeComercial);

        }
        catch (Exception e)
        {
            throw new SonolucroDAOException(e);
        }
    }

    @Override
    public void apagar(LocalidadeComercialBean localidadeComercial) throws SonolucroDAOException
    {
        try
        {
            localidadeComercial = manager.merge(localidadeComercial); 
            manager.remove(localidadeComercial);
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException(e);
        }
    }

    @Override
    public List<LocalidadeComercialBean> listar() throws SonolucroDAOException
    {
        try
        {
            final String JPQL = "select lc from " + ENTIDADE + " as lc";
            Query query = manager.createQuery(JPQL);

            return query.getResultList();
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException(e);
        }
    }

    @Override
    public List<LocalidadeComercialBean> listar(Long id) throws SonolucroDAOException
    {
        return null;
    }

    public List<PessoaBean> listarResponsaveisLocalidadesComerciaisPorLocalidade(Long idLocalidadeComercial) throws SonolucroDAOException
    {
        try
        {
            StringBuilder jpql = new StringBuilder();
            jpql.append("select lc.responsaveisLocalidadesComerciais from ");
            jpql.append(ENTIDADE);
            jpql.append(" lc where lc.id = '" + idLocalidadeComercial + "'");

            Query query = manager.createQuery(jpql.toString());
            List<PessoaBean> responsaveis = query.getResultList();

            return responsaveis;
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException(e);
        }
    }
}
