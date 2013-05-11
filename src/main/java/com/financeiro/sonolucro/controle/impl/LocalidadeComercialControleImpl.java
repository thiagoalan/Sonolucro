
package com.financeiro.sonolucro.controle.impl;

import com.financeiro.sonolucro.bean.api.LocalidadeComercialBean;
import com.financeiro.sonolucro.bean.api.PessoaBean;
import com.financeiro.sonolucro.controle.api.LocalidadeComercialControle;
import com.financeiro.sonolucro.dao.LocalidadeComercialDAO;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Rodrigo Romero
 * @since 16/02/2013
 * @version 1.0
 */

@Stateless
public class LocalidadeComercialControleImpl implements LocalidadeComercialControle<LocalidadeComercialBean>, Serializable
{
    private static final long serialVersionUID = -7958546225126508689L;

    @Inject
    private LocalidadeComercialDAO localidadeComercialDAO; 
    
    @Override
    public LocalidadeComercialBean salvar(LocalidadeComercialBean localidadeComercial) throws SonolucroControleException
    {
        try
        {
            return localidadeComercialDAO.salvar(localidadeComercial); 
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }

    @Override
    public LocalidadeComercialBean alterar(LocalidadeComercialBean localidadeComercial) throws SonolucroControleException
    {
        try
        {
            return localidadeComercialDAO.alterar(localidadeComercial); 
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }

    @Override
    public void apagar(LocalidadeComercialBean localidadeComercialBean) throws SonolucroControleException
    {
        try
        {
            localidadeComercialDAO.apagar(localidadeComercialBean);
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }

    @Override
    public List<LocalidadeComercialBean> listar() throws SonolucroControleException
    {
        try
        {
            return localidadeComercialDAO.listar(); 
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }
    
    @Override
    public List<PessoaBean> listarResponsaveisPorLocalidadeComercial(
            LocalidadeComercialBean localidadeComercial) throws SonolucroControleException
    {
        try
        {
            return localidadeComercialDAO.listarResponsaveisLocalidadesComerciaisPorLocalidade(
                    localidadeComercial.getId());
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }
    
}
