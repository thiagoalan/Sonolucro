
package com.financeiro.sonolucro.controle.impl;

import com.financeiro.sonolucro.bean.api.EmpresaBean;
import com.financeiro.sonolucro.controle.api.EmpresaControle;
import com.financeiro.sonolucro.dao.EmpresaDAO;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author rodrigo
 * @since 30/12/2012
 * @version 1.0
 */
@Stateless
public class EmpresaControleImpl implements EmpresaControle<EmpresaBean>, Serializable
{
    private static final long serialVersionUID = 2855504927205535600L;

    @Inject
    private EmpresaDAO empresaDAO; 
    
    @Override
    public EmpresaBean salvar(EmpresaBean empresa) throws SonolucroControleException
    {
        try
        {
            return empresaDAO.salvar(empresa); 
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }

    @Override
    public EmpresaBean alterar(EmpresaBean empresa) throws SonolucroControleException
    {
        try
        {
            return empresaDAO.alterar(empresa); 
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }
    
    @Override
    public void apagar(EmpresaBean empresa) throws SonolucroControleException
    {
        try
        {
            empresaDAO.apagar(empresa);
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }

    @Override
    public List<EmpresaBean> listar() throws SonolucroControleException
    {
        try
        {
            return empresaDAO.listar(); 
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }

    @Override
    public EmpresaBean buscarPorCnpj(String cnpj) throws SonolucroControleException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<EmpresaBean> listarPorTipo(String tipo) throws SonolucroControleException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<EmpresaBean> listarPorPais(String pais) throws SonolucroControleException 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<EmpresaBean> listarPorPaisEstado(String pais) throws SonolucroControleException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<EmpresaBean> listarPorEstadoCidade(String estado, String cidade) throws SonolucroControleException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<EmpresaBean> pesquisarEmpresasClienteOuNao(Boolean ecliente) throws SonolucroControleException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
