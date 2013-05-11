/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.financeiro.sonolucro.controle.impl;

import com.financeiro.sonolucro.bean.api.AcaoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.controle.api.AcaoControle;
import com.financeiro.sonolucro.dao.AcaoDAO;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Rodrigo Romero
 * @since 23/03/2013
 * @version 1.0
 */


public class AcaoControleImpl implements AcaoControle<AcaoBean>, Serializable
{
    private static final long serialVersionUID = 7642617012428274864L;

    @Inject
    private AcaoDAO acaoDAO;

    @Override
    public AcaoBean salvar(AcaoBean acao) throws SonolucroControleException
    {
        try
        {
            return acaoDAO.salvar(acao);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }
    }

    @Override
    public AcaoBean alterar(AcaoBean acao) throws SonolucroControleException
    {
        try
        {
            return acaoDAO.alterar(acao);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }
    }

    @Override
    public void apagar(AcaoBean acao) throws SonolucroControleException
    {
        try
        {
            acaoDAO.apagar(acao);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }
    }

    @Override
    public List<AcaoBean> listar(UsuarioBean usuario) throws SonolucroControleException
    {
        try
        {
            return acaoDAO.listar(usuario.getId());
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }
    }
}
