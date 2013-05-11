package com.financeiro.sonolucro.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.financeiro.sonolucro.bean.api.ContaBean;
import com.financeiro.sonolucro.bean.api.GrupoBean;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import javax.ejb.Singleton;
import javax.persistence.PersistenceException;

/*
 * Classe para persistencia da Entidade Conta
 * 
 * @author Rodrigo Romero
 * @since 10/08/2012
 * @version 1.0
 * 
 */
@Singleton
public class ContaDAO extends DAO<ContaBean>
{

    @PersistenceContext
    private EntityManager manager;
    private static final String ENTIDADE = "Conta";

    public ContaBean salvar(ContaBean conta) throws SonolucroDAOException
    {
        try
        {
            manager.persist(conta);
            return (ContaBean) manager.merge(conta);
        }
        catch (PersistenceException e)
        {
            System.out.println(e.getCause());
            e.printStackTrace();
            throw new SonolucroDAOException("Erro ao salvar Conta.", e);
        }
    }

    public List<ContaBean> listar(Long idusuario) throws SonolucroDAOException
    {
        try
        {
            final String JPQL = "select c from Conta as c"
                    + " inner join c.grupo as g "
                    + " inner join g.usuario as u "
                    + " where u.id = '" + idusuario
                    + "' order by c.tipo desc, c.nome asc";

            Query query = manager.createQuery(JPQL);

            List<ContaBean> contas = (List<ContaBean>) query.getResultList();


            return contas;
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao listar contas.");
        }
    }

    public void apagar(ContaBean conta) throws SonolucroDAOException
    {
        try
        {
            conta = manager.merge(conta); 
            manager.remove(conta);
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao apagar Conta", e);
        }

    }

    public ContaBean alterar(ContaBean conta) throws SonolucroDAOException
    {
        try
        {
            return manager.merge(conta);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage()); 
            throw new SonolucroDAOException("Erro ao alterar Conta.", e);
        }

    }

    public List<ContaBean> listarContaPorGrupo(GrupoBean grupo) throws SonolucroDAOException
    {
        try
        {
            final String JPQL = "select c from Conta as c"
                    + " inner join c.grupo as g"
                    + " where g.id = '" + grupo.getId() + "' order by c.nome";

            Query query = manager.createQuery(JPQL);
            List<ContaBean> contas = query.getResultList();

            return contas;
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao listar contas.");
        }

    }

    @Override
    public List<ContaBean> listar() throws SonolucroDAOException
    {
        return null;
    }
}
