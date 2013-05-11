package com.financeiro.sonolucro.controle.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.financeiro.sonolucro.bean.api.MovimentacaoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.controle.api.MovimentacaoControle;
import com.financeiro.sonolucro.dao.CartaoDAO;
import com.financeiro.sonolucro.dao.ContaDAO;
import com.financeiro.sonolucro.dao.MovimentacaoDAO;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import javax.ejb.Stateless;

@Stateless
public class MovimentacaoControleImpl implements MovimentacaoControle<MovimentacaoBean>, Serializable
{

    private static final long serialVersionUID = 8221136652695706572L;
    @Inject
    MovimentacaoDAO movimentacaoDAO;
    @Inject
    CartaoDAO cartaoDAO;
    @Inject
    ContaDAO contaDAO;

    @Override
    public MovimentacaoBean salvar(MovimentacaoBean mov) throws SonolucroControleException
    {
        try
        {
            return movimentacaoDAO.salvar(mov);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e.getMessage(), e);
        }

    }

    @Override
    public MovimentacaoBean alterar(MovimentacaoBean mov) throws SonolucroControleException
    {
        try
        {
            return movimentacaoDAO.alterar(mov);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e.getMessage(), e);
        }
    }

    @Override
    public void apagar(MovimentacaoBean mov) throws SonolucroControleException
    {
        try
        {
            movimentacaoDAO.apagar(mov);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e.getMessage(), e);
        }
    }

    @Override
    public List<MovimentacaoBean> listar(UsuarioBean usuario) throws SonolucroControleException
    {
        try
        {
            List<MovimentacaoBean> movs = movimentacaoDAO.listar(usuario.getId());

            processaSaldo(movs);

            return movs;
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e.getMessage(), e);
        }
    }

    @Override
    public List<MovimentacaoBean> listarPorData(UsuarioBean usuario, Date dataInicial,
                                                Date dataFinal) throws SonolucroControleException
    {
        List<MovimentacaoBean> movs = new ArrayList<MovimentacaoBean>();

        filtrarMovimentacaoPorData(movs, dataInicial, dataFinal, usuario);

        return movs;

    }

    @Override
    public List<MovimentacaoBean> listaPorDataLancamento(UsuarioBean usuario) throws SonolucroControleException
    {
        try
        {
            return movimentacaoDAO.listaPelaDataLancamento(usuario);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException("Erro ao listar Movimentações.", e);
        }
    }

    public Float processaSaldo(List<MovimentacaoBean> movs, Boolean ordenarLista) throws SonolucroControleException
    {
        if (ordenarLista)
            Collections.sort((List) movs);

        return processaSaldo(movs);
    }

    protected synchronized Float processaSaldo(List<MovimentacaoBean> movs) throws SonolucroControleException
    {
        try
        {
            Float saldo = 0f;

            if (movs != null)
            {
                for (MovimentacaoBean mov : movs)
                {
                    if (mov.getConta().getTipo().equals(1))
                    {
                        mov.setDebito(mov.getValor() * -1);
                        mov.setCredito(0f);
                        saldo += mov.getDebito();
                        mov.setSaldo(saldo);
                    }
                    else
                    {
                        mov.setDebito(0f);
                        mov.setCredito(mov.getValor());
                        saldo += mov.getCredito();
                        mov.setSaldo(saldo);
                    }
                }

                return saldo;
            }
            else
                return saldo;
        }
        catch (RuntimeException e)
        {
            throw new SonolucroControleException("Erro ao processar saldo das movimentações.", e);
        }
    }

    protected void filtrarMovimentacaoPorData(List<MovimentacaoBean> movs, Date dataInicial, Date dataFinal,
                                              UsuarioBean usuario) throws SonolucroControleException
    {
        try
        {
            movs.clear();
            List<MovimentacaoBean> movimentacoes = listar(usuario);

            if (movimentacoes != null && movimentacoes.size() > 0)
            {
                for (MovimentacaoBean mov : movimentacoes)
                {
                    if ((mov.getDataVencimento().after(dataInicial) || mov.getDataVencimento().equals(dataInicial))
                            && (mov.getDataVencimento().before(dataFinal) || mov.getDataVencimento().equals(dataFinal)))
                        movs.add(mov);
                }
            }
        }
        catch (RuntimeException e)
        {
            throw new SonolucroControleException(e);
        }
    }
}
