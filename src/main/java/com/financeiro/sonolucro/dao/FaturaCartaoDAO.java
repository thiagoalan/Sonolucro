
package com.financeiro.sonolucro.dao;

import com.financeiro.sonolucro.bean.api.FaturaCartaoBean;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * Classe para persistencia da Entidade FaturaCartao
 *
 * @author rodrigo
 * @since 22/12/2012
 * @version 1.0.0
 *
 */
@Singleton
public class FaturaCartaoDAO extends DAO<FaturaCartaoBean>
{
    public static final String ENTIDADE = "FaturaCartao"; 

    @PersistenceContext
    private EntityManager manager;

    public FaturaCartaoBean salvar(FaturaCartaoBean fatura) throws SonolucroDAOException
    {
        try
        {
            manager.persist(fatura);

            return manager.merge(fatura);
        }
        catch (PersistenceException e)
        {
            throw new SonolucroDAOException("Erro ao salvar Fatura", e);
        }
    }

    public FaturaCartaoBean alterar(FaturaCartaoBean fatura) throws SonolucroDAOException
    {
        try
        {
            
            return manager.merge(fatura);
            
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao alterar Fatura.", e);
        }
    }

    public void apagar(FaturaCartaoBean fatura) throws SonolucroDAOException
    {
        try
        {
            fatura = manager.merge(fatura);
            manager.remove(fatura);
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao apagar Fatura.", e);
        }
    }

    @Override
    public List<FaturaCartaoBean> listar() throws SonolucroDAOException
    {
        return null; 
    }

    @Override
    public List<FaturaCartaoBean> listar(Long idcartao) throws SonolucroDAOException
    {
        return null; 
    }
}
