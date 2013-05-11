package com.financeiro.sonolucro.dao;

import com.financeiro.sonolucro.bean.api.EmpresaBean;
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
 *
 */
@Singleton
public class EmpresaDAO extends DAO<EmpresaBean>
{

    public static final String ENTIDADE = "Empresa";
    
    @PersistenceContext
    private EntityManager manager;

    @Override
    public EmpresaBean salvar(EmpresaBean empresa) throws SonolucroDAOException
    {
        try
        {
            manager.persist(empresa);
            return manager.merge(empresa);
        }
        catch (PersistenceException e)
        {
            throw new SonolucroDAOException("Erro ao salvar empresa: " + e.getMessage());
        }
    }

    @Override
    public EmpresaBean alterar(EmpresaBean empresa) throws SonolucroDAOException
    {
        try
        {
            return manager.merge(empresa); 
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao alterar Empresa.");
        }
    }

    @Override
    public void apagar(EmpresaBean empresa) throws SonolucroDAOException
    {
        try
        {
            empresa = manager.merge(empresa);
            manager.remove(empresa);
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao apagar empresa.");
        }
    }

    public List<EmpresaBean> listar() throws SonolucroDAOException
    {
        try
        {
            final String JPQL = "select e from " + ENTIDADE + " e ";

            Query query = manager.createQuery(JPQL);

            List<EmpresaBean> empresas = query.getResultList();

            return empresas;
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao pesquisar Empresas.");
        }
    }

    @Override
    public List<EmpresaBean> listar(Long id) throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
