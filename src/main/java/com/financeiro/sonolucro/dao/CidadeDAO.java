package com.financeiro.sonolucro.dao;

import com.financeiro.sonolucro.bean.api.CidadeBean;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author rodrigo
 * @since 30/12/2012
 * @version 1.0.0
 *
 */
@Singleton
public class CidadeDAO extends DAO<CidadeBean>
{

    public static final String ENTIDADE = "Cidade";
    @PersistenceContext
    private EntityManager manager;

    @Override
    public CidadeBean salvar(CidadeBean bean) throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CidadeBean alterar(CidadeBean bean) throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void apagar(CidadeBean bean) throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CidadeBean> listar() throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CidadeBean> listar(Long idEstado) throws SonolucroDAOException
    {
        try
        {
            final String JPQL = "select c from " + ENTIDADE + " c "
                    + " where c.estado.id = '" + idEstado + "'";
            Query query = manager.createQuery(JPQL);
            List<CidadeBean> cidades = query.getResultList();

            return cidades;
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao listar cidades.", e);
        }
    }

    public CidadeBean buscarCidade(Long idCidade) throws SonolucroDAOException
    {
        try
        {
            final String JPQL = "select c from " + ENTIDADE + " c "
                    + " where c.id ='" + idCidade + "'";
            Query query = manager.createQuery(JPQL);
            CidadeBean cidade = (CidadeBean) query.getSingleResult();

            return cidade;
        }
        catch (NoResultException e)
        {
            throw new SonolucroDAOException(e);
        }
    }
}
