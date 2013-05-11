package com.financeiro.sonolucro.dao;

import com.financeiro.sonolucro.bean.api.EstadoBean;
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
 */
@Singleton
public class EstadoDAO extends DAO<EstadoBean>
{

    public static final String ENTIDADE = "Estado";
    
    @PersistenceContext
    private EntityManager manager;

    @Override
    public EstadoBean salvar(EstadoBean bean) throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EstadoBean alterar(EstadoBean bean) throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void apagar(EstadoBean bean) throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<EstadoBean> listar() throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<EstadoBean> listar(Long idPais) throws SonolucroDAOException
    {
        try
        {
            final String JPQL = "select e from " + ENTIDADE + " e "
                    + " where e.pais.id = '" + idPais + "' ";
            Query query = manager.createQuery(JPQL);
            
            List<EstadoBean> estados = query.getResultList();
            
            return estados; 
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao listar estados.", e); 
        }
    }
    
    public EstadoBean buscarEstado(Long id) throws SonolucroDAOException
    {
        try
        {
            final String JPQL = "select e from " + ENTIDADE + " e "
                    + " where e.id = '" + id + "'";
            Query query = manager.createQuery(JPQL);
            
            EstadoBean estado = (EstadoBean) query.getSingleResult(); 
            
            return estado; 
        }
        catch(NoResultException e)
        {
            throw new SonolucroDAOException("Erro ao buscar Estado", e); 
        }
    }
}
