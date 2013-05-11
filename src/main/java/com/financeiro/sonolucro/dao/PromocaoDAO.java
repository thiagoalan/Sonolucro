package com.financeiro.sonolucro.dao;

import com.financeiro.sonolucro.bean.api.PromocaoBean;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 *
 * @author rodrigo
 * @since 25/12/2012
 * @version 1.0.0
 *
 */
@Singleton
public class PromocaoDAO extends DAO<PromocaoBean>
{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public PromocaoBean salvar(PromocaoBean promocao) throws SonolucroDAOException
    {
        try
        {
            manager.persist(promocao);
            return manager.merge(promocao);
        }
        catch (PersistenceException e)
        {
            throw new SonolucroDAOException("Erro ao salvar Promoção.");
        }
    }

    @Override
    public PromocaoBean alterar(PromocaoBean promocao) throws SonolucroDAOException
    {
        try
        {

            return manager.merge(promocao);
        }
        catch (PersistenceException e)
        {
            throw new SonolucroDAOException("Erro ao alterar promoção.");
        }
    }

    @Override
    public void apagar(PromocaoBean promocao) throws SonolucroDAOException
    {
        try
        {
            promocao = manager.merge(promocao);
            manager.remove(promocao);
        }
        catch (PersistenceException e)
        {
            throw new SonolucroDAOException("Erro ao apagar promoção.");
        }
    }

    @Override
    public List<PromocaoBean> listar() throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PromocaoBean> listar(Long idempresa) throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
