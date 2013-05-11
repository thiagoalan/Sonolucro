package com.financeiro.sonolucro.controle.impl;

import java.util.List;

import com.financeiro.sonolucro.bean.api.ContaBean;
import com.financeiro.sonolucro.bean.api.MovimentacaoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.controle.api.ContaControle;
import com.financeiro.sonolucro.controle.api.MovimentacaoControle;
import com.financeiro.sonolucro.dao.ContaDAO;
import com.financeiro.sonolucro.dao.MovimentacaoDAO;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.inject.Inject;

/*
 *  Implementacão padrão da interface ContaControle
 *  
 * 
 * @author Rodrigo 
 * @since 10/08/2012
 * @version 1.0.0
 */

@Stateless
public class ContaControleImpl implements ContaControle<ContaBean>, Serializable
{
    private static final long serialVersionUID = 1194322293164040373L;

    @Inject
    private ContaDAO contaDAO;
    @Inject
    private MovimentacaoDAO movDAO;
    @Inject
    private MovimentacaoControle movControle;

    @Override
    public ContaBean salvar(ContaBean conta) throws SonolucroControleException
    {
        try
        {
            return contaDAO.salvar(conta);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }
    }

    @Override
    public ContaBean alterar(ContaBean conta) throws SonolucroControleException
    {
        try
        {
            return contaDAO.alterar(conta);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }
    }

    @Override
    public void apagar(ContaBean conta) throws SonolucroControleException
    {
        try
        {
            preparaContaParaApagar(conta);
            contaDAO.apagar(conta);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException("Erro ao apagar Conta.", e);
        }
    }

    @Override
    public List<ContaBean> listar(UsuarioBean usuario) throws SonolucroControleException
    {
        try
        {
            return contaDAO.listar(usuario.getId());
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException("Erro ao listas Contas.", e);
        }
    }

    @Override
    public List<ContaBean> listaContasPorTipo(ContaBean conta) throws SonolucroControleException
    {

        return null;
    }

    protected void preparaContaParaApagar(ContaBean conta) throws SonolucroControleException
    {
        try
        {
            List<MovimentacaoBean> movs = movDAO.listarMovimentacaoPorConta(conta);

            if (movs != null)
            {
                for (MovimentacaoBean mov : movs)
                    movControle.apagar(mov);
            }
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException("Erro ao apagar Conta.", e);
        }
    }
}
