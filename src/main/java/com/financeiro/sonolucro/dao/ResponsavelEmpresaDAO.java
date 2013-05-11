
package com.financeiro.sonolucro.dao;

import com.financeiro.sonolucro.bean.api.PessoaBean;
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
 * @since 31/12/2012
 * @version 1.0.0
 * 
 */

@Singleton
public class ResponsavelEmpresaDAO extends DAO<PessoaBean>
{
    public static final String ENTIDADE = "ResponsavelEmpresa"; 
    
    @PersistenceContext
    private EntityManager manager; 

    @Override
    public PessoaBean salvar(PessoaBean pessoa) throws SonolucroDAOException
    {
        try
        {
            manager.persist(pessoa);
            return manager.merge(pessoa); 
        }
        catch(PersistenceException e)
        {
            throw new SonolucroDAOException("Erro ao salvar Pessoa Responsavel Empresa.", e); 
        }
    }

    @Override
    public PessoaBean alterar(PessoaBean pessoa) throws SonolucroDAOException
    {
        try
        {
            return manager.merge(pessoa); 
        }
        catch(Exception e)
        {
            throw new SonolucroDAOException("Erro ao alterar Pessoa Responsavel Empresa.", e); 
        }
    }

    @Override
    public void apagar(PessoaBean pessoa) throws SonolucroDAOException
    {
        try
        {
            pessoa = manager.merge(pessoa);
            manager.remove(pessoa);
        }
        catch(Exception e)
        {
            throw new SonolucroDAOException(e); 
        }
    }

    @Override
    public List<PessoaBean> listar() throws SonolucroDAOException
    {
        try
        {
            final String JPQL = "select r from " + ENTIDADE + " r ";
            
            Query query = manager.createQuery(JPQL); 
            List<PessoaBean> responsaveis = query.getResultList(); 
            
            return responsaveis; 
        }
        catch(Exception e)
        {
            throw new SonolucroDAOException(e);
        }
    }

    @Override
    public List<PessoaBean> listar(Long id) throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
