package com.financeiro.sonolucro.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import javax.ejb.Singleton;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

/**
 * Classe para persistencia do Usuario Bean
 *
 * @author rodrigo
 * @since 10/08/2012
 * @version 1.0.0
 */
@Singleton
public class UsuarioDAO extends DAO<UsuarioBean>
{

    @PersistenceContext
    private EntityManager manager;
    private final static String ENTIDADE = "Usuario";

    @Override
    public UsuarioBean salvar(UsuarioBean usuario) throws SonolucroDAOException
    {
        try
        {
            manager.persist(usuario);
            return (UsuarioBean) manager.merge(usuario);
        }
        catch (PersistenceException e)
        {
            throw new SonolucroDAOException("Erro ao salvar Usuário.");
        }
    }

    @Override
    public UsuarioBean alterar(UsuarioBean usuario) throws SonolucroDAOException
    {
        try
        {
            return manager.merge(usuario);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new SonolucroDAOException("Erro ao alterar Usuário.");
        }
    }

    public UsuarioBean buscarPorEmail(String email) throws SonolucroDAOException
    {
        try
        {
            String jpql = "select u from Usuario u where u.email = '" + email + "'";

            Query query = manager.createQuery(jpql);

            UsuarioBean usuarioLogado = (UsuarioBean) query.getSingleResult();

            return usuarioLogado;
        }
        catch (NoResultException e)
        {
            return null;
        }
        catch(Exception e)
        {
            throw new SonolucroDAOException(e); 
        }
    }

    @Override
    public List<UsuarioBean> listar() throws SonolucroDAOException
    {
        try
        {
            String jpql = "select u from " + ENTIDADE +" u order by u.nome";
            Query query = manager.createQuery(jpql);

            return query.getResultList();
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao listar Usuários.", e);
        }
    }

    @Override
    public void apagar(UsuarioBean bean) throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UsuarioBean> listar(Long id) throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
