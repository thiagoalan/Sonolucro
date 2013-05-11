package com.financeiro.sonolucro.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.financeiro.sonolucro.bean.api.GrupoBean;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import javax.ejb.Singleton;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/*
 * Classe para persistencia da Entidade Grupo
 * 
 * @author Rodrigo Romero
 * @since 10/08/2012
 * @version 1.0
 * 
 */
@Singleton
public class GrupoDAO extends DAO<GrupoBean>
{

    @PersistenceContext
    private EntityManager manager;
    private static final String ENTIDADE = "Grupo";

    
    public GrupoBean salvar(GrupoBean grupo) throws SonolucroDAOException
    {
        try
        {
            manager.persist(grupo);
            return (GrupoBean) manager.merge(grupo);
        }
        catch (PersistenceException e)
        {
            throw new SonolucroDAOException("Erro ao salvar grupo", e);
        }
    }

    public List<GrupoBean> listar(Long idusuario) throws SonolucroDAOException
    {
        String jpql = "select g from Grupo as g "
                + "inner join  g.usuario as u where u.id = '"
                + idusuario
                + "' order by g.nome";

        Query query = manager.createQuery(jpql);

        return query.getResultList();
    }

    public GrupoBean alterar(GrupoBean grupo) throws SonolucroDAOException
    {
        try
        {
            return manager.merge(grupo);
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao alterar Grupo", e);
        }
    }

    public void apagar(GrupoBean grupo) throws SonolucroDAOException
    {
        try
        {
            grupo = manager.merge(grupo);
            manager.remove(grupo);
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao remover grupo", e);
        }
    }

    public String getNomeEntidade()
    {
        return ENTIDADE;
    }

    @Override
    public List<GrupoBean> listar() throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
