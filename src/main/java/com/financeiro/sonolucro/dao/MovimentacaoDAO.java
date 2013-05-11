package com.financeiro.sonolucro.dao;

import com.financeiro.sonolucro.bean.api.CartaoBean;
import com.financeiro.sonolucro.bean.api.ContaBean;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.financeiro.sonolucro.bean.api.MovimentacaoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import javax.ejb.Singleton;
import javax.persistence.PersistenceException;

/**
 * Classe para persistencia da Entidade Movimentacao
 *
 * @author Rodrigo Romero
 * @since 10/08/2013
 * @version 1.0
 *
 */
@Singleton
public class MovimentacaoDAO extends DAO<MovimentacaoBean>
{

    @PersistenceContext
    private EntityManager manager;
    private static final String ENTIDADE = "Movimentacao";

    public MovimentacaoBean salvar(MovimentacaoBean mov) throws SonolucroDAOException
    {
        try
        {
            manager.persist(mov);
            return (MovimentacaoBean) manager.merge(mov);
        }
        catch (PersistenceException e)
        {
            throw new SonolucroDAOException("Erro ao salvar movimentacão", e);
        }

    }

    public MovimentacaoBean alterar(MovimentacaoBean mov) throws SonolucroDAOException
    {
        try
        {
            return manager.merge(mov);
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao alterar movimentacão.", e);
        }
    }

    public void apagar(MovimentacaoBean mov) throws SonolucroDAOException
    {
        try
        {
            mov = manager.merge(mov);
            manager.remove(mov);
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao apagar movimentação.", e);
        }
    }

    public List<MovimentacaoBean> listarPelaData(UsuarioBean usuario,
            Date dataInicial, Date dataFinal) throws SonolucroDAOException
    {
        try
        {
            final String JPQL = "select m from Movimentacao as m"
                    + " inner join m.conta as c "
                    + " inner join c.grupo as g "
                    + " inner join g.usuario as u"
                    + " where u.id = '" + usuario.getId() + "' and m.dataVencimento BETWEEN '"
                    + dataInicial + "' and '" + dataFinal
                    + "' order by m.dataVencimento";

            Query query = manager.createQuery(JPQL);

            List<MovimentacaoBean> movs = query.getResultList();

            return movs;
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao listar Movimentações.", e);
        }
    }

    public List<MovimentacaoBean> listaPelaDataLancamento(UsuarioBean usuario) throws SonolucroDAOException
    {
        try
        {
            final String JPQL = "select m from Movimentacao as m"
                    + " inner join m.conta as c "
                    + " inner join c.grupo as g "
                    + " inner join g.usuario as u"
                    + " where u.id = '" + usuario.getId() + "' order by m.dataLancamento desc";

            Query query = manager.createQuery(JPQL);

            List<MovimentacaoBean> movs = query.getResultList();

            return movs;
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao listar Movimentacões.", e);
        }
    }

    public List<MovimentacaoBean> listar(Long idusuario) throws SonolucroDAOException
    {

        try
        {
            final String JPQL = "select m from Movimentacao as m"
                    + " inner join m.conta as c "
                    + " inner join c.grupo as g "
                    + " inner join g.usuario as u"
                    + " where u.id = '" + idusuario
                    + "' order by m.dataVencimento, m.sequencia";

            Query query = manager.createQuery(JPQL);

            List<MovimentacaoBean> movs = query.getResultList();

            return movs;
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao listar movimentações.", e);
        }

    }

    public List<MovimentacaoBean> listarMovimentacaoPorConta(ContaBean conta) throws SonolucroDAOException
    {
        try
        {
            final String JPQL = "select m from Movimentacao as m"
                    + " inner join m.conta as c"
                    + " where c.id = '" + conta.getId() + "'";

            Query query = manager.createQuery(JPQL);
            List<MovimentacaoBean> movs = query.getResultList();

            return movs;
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao listar movimentações.", e);
        }
    }

    public List<MovimentacaoBean> listarMovimentacaoPorCartao(CartaoBean cartao) throws SonolucroDAOException
    {
        try
        {
            final String JPQL = "select m from Movimentacao as m"
                    + " inner join m.cartao as c"
                    + " where c.id = '" + cartao.getId() + "'";

            Query query = manager.createQuery(JPQL);
            List<MovimentacaoBean> movs = query.getResultList();

            return movs;
        }
        catch (Exception e)
        {
            throw new SonolucroDAOException("Erro ao listar movimentações.", e);
        }
    }

    @Override
    public List<MovimentacaoBean> listar() throws SonolucroDAOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
