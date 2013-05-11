package com.financeiro.sonolucro.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.financeiro.sonolucro.bean.api.CartaoBean;
import com.financeiro.sonolucro.bean.api.FaturaCartaoBean;
import com.financeiro.sonolucro.bean.impl.CartaoBeanImpl;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import javax.ejb.Singleton;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/*
 * Classe para persistencia da Entidade Grupo
 * 
 * @author Rodrigo
 * @since 10/08/2012
 * @version 1.0.0
 * 
 */
@Singleton
public class CartaoDAO extends DAO<CartaoBean>
{

    @PersistenceContext
    private EntityManager manager;
    private static final String ENTIDADE = "Cartao";

    public CartaoBean salvar(CartaoBean cartao) throws SonolucroDAOException
    {
        try
        {
            manager.persist(cartao);
            return (CartaoBean) manager.find(CartaoBeanImpl.class, cartao.getId());
        }
        catch (PersistenceException e)
        {
            e.printStackTrace();
            throw new SonolucroDAOException("Erro ao salvar Cartão.", e);
        }
    }

    public CartaoBean alterar(CartaoBean cartao) throws SonolucroDAOException
    {
        try
        {

            return manager.merge(cartao);

        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao alterar Cartão.", e);
        }
    }

    public void apagar(CartaoBean cartao) throws SonolucroDAOException
    {
        try
        {
            cartao = manager.merge(cartao);
            manager.remove(cartao);
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao apagar Cartão.", e);
        }
    }

    public List<CartaoBean> listar(Long idusuario) throws SonolucroDAOException
    {
        try
        {
            String jpql = "select c from Cartao as c"
                    + " inner join c.usuario as u where u.id = '"
                    + idusuario + "'";

            Query query = manager.createQuery(jpql);

            return query.getResultList();
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao listar cartões.");
        }

    }

    public CartaoBean buscarCartao(Long id) throws SonolucroDAOException
    {
        try
        {
            CartaoBean cartao = manager.find(CartaoBeanImpl.class, id);
            return cartao;
        }
        catch (NoResultException e)
        {
            String erroStr = String.format("Erro buscar no banco de dados: %s",
                    e.getMessage());
            throw new SonolucroDAOException(erroStr, e);
        }

    }

    public String getNomeEntidade()
    {
        return ENTIDADE;
    }

    @Override
    public List<CartaoBean> listar() throws SonolucroDAOException
    {
        return null; 
    }

}
