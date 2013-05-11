package com.financeiro.sonolucro.controle.impl;

import com.financeiro.sonolucro.bean.api.ContaBean;
import java.util.List;

import javax.inject.Inject;

import com.financeiro.sonolucro.bean.api.GrupoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.controle.api.ContaControle;
import com.financeiro.sonolucro.controle.api.GrupoControle;
import com.financeiro.sonolucro.dao.ContaDAO;
import com.financeiro.sonolucro.dao.GrupoDAO;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.io.Serializable;
import javax.ejb.Stateless;

/*
 * Implementação padrão da interface de controle GrupoControle
 * 
 * @author Rodrigo
 * @since 10/08/2012
 * @version 1.0.0
 * 
 */

@Stateless
public class GrupoControleImpl implements GrupoControle<GrupoBean>, Serializable
{
    private static final long serialVersionUID = 735709175846072046L;

    @Inject
    private GrupoDAO grupoDAO;
    @Inject
    private ContaDAO contaDAO;
    @Inject
    private ContaControle contaControle;

    @Override
    public GrupoBean salvar(GrupoBean grupo) throws SonolucroControleException
    {
        try
        {
            return grupoDAO.salvar(grupo);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException("Erro ao salvar grupo.", e);
        }
    }

    @Override
    public GrupoBean alterar(GrupoBean grupo) throws SonolucroControleException
    {
        try
        {
            return grupoDAO.alterar(grupo);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException("Erro ao alterar grupo", e);
        }
    }

    @Override
    public void apagar(GrupoBean grupo) throws SonolucroControleException
    {
        try
        {
            preparaGrupoParaApagar(grupo);
            grupoDAO.apagar(grupo);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException("Erro ao apagar grupo.", e);
        }

    }

    @Override
    public List<GrupoBean> listar(UsuarioBean usuario) throws SonolucroControleException
    {
        try
        {
            return grupoDAO.listar(usuario.getId());
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException("Erro ao listar Grupo.", e);
        }
    }

    protected void preparaGrupoParaApagar(GrupoBean grupo) throws SonolucroControleException
    {
        try
        {
            List<ContaBean> contas = contaDAO.listarContaPorGrupo(grupo);

            if (contas != null)
            {
                for (ContaBean conta : contas)
                    contaControle.apagar(conta);
            }
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException("Erro ao apagar grupo", e);
        }
    }
}

