package com.financeiro.sonolucro.controle.impl;

import com.financeiro.sonolucro.bean.api.CidadeBean;
import com.financeiro.sonolucro.bean.api.DicaBean;
import com.financeiro.sonolucro.bean.api.EstadoBean;
import com.financeiro.sonolucro.bean.api.IdiomaBean;
import com.financeiro.sonolucro.bean.api.PaisBean;
import com.financeiro.sonolucro.bean.api.PessoaBean;
import com.financeiro.sonolucro.controle.api.SonolucroControle;
import com.financeiro.sonolucro.dao.CidadeDAO;
import com.financeiro.sonolucro.dao.DicaDAO;
import com.financeiro.sonolucro.dao.EstadoDAO;
import com.financeiro.sonolucro.dao.IdiomaDAO;
import com.financeiro.sonolucro.dao.PaisDAO;
import com.financeiro.sonolucro.dao.ResponsavelEmpresaDAO;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * Implementação padrão de SonolucroControle. Controle Administrativo do
 * Sonolucro.
 *
 * @author Rodrigo Romero
 * @since 25/12/2012
 * @version 1.0
 *
 */
@Stateless
public class SonolucroControleImpl implements SonolucroControle, Serializable
{
    private static final long serialVersionUID = -223201341660157871L;

    @Inject
    private DicaDAO dicaDAO;
    @Inject
    private IdiomaDAO idiomaDAO;
    @Inject
    private PaisDAO paisDAO;
    @Inject
    private EstadoDAO estadoDAO;
    @Inject
    private CidadeDAO cidadeDAO;
    @Inject 
    private ResponsavelEmpresaDAO responsavelEmpresaDAO; 

    @Override
    public DicaBean salvarDica(DicaBean dica) throws SonolucroControleException
    {
        try
        {
            return dicaDAO.salvar(dica);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }

    }

    @Override
    public DicaBean alterarDica(DicaBean dica) throws SonolucroControleException
    {
        try
        {
            return dicaDAO.alterar(dica);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }
    }

    @Override
    public void apagarDica(DicaBean dica) throws SonolucroControleException
    {
        try
        {
            dicaDAO.apagar(dica);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }
    }

    @Override
    public List<DicaBean> listarDicas() throws SonolucroControleException
    {
        try
        {
            List<DicaBean> dicas = dicaDAO.listar();
            return dicas;
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }
    }

    @Override
    public List<DicaBean> listarDicaPorIdioma(IdiomaBean idioma) throws SonolucroControleException
    {
        try
        {
            return dicaDAO.listar(idioma.getId().longValue());
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }
    }

    @Override
    public List<IdiomaBean> listarIdiomas() throws SonolucroControleException
    {
        try
        {
            List<IdiomaBean> idiomas = idiomaDAO.listar();

            return idiomas;
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }
    }

    @Override
    public List<PaisBean> listarPaises() throws SonolucroControleException
    {
        try
        {
            return paisDAO.listar();
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }
    }

    @Override
    public List<EstadoBean> listarEstados(PaisBean pais) throws SonolucroControleException
    {
        try
        {
            return estadoDAO.listar(pais.getId()); 
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }

    @Override
    public List<CidadeBean> listarCidades(EstadoBean estado) throws SonolucroControleException
    {
        try
        {
            return cidadeDAO.listar(estado.getId()); 
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }
    
    @Override
    public PessoaBean salvarResponsavelEmpresa(PessoaBean pessoa) throws SonolucroControleException
    {
        try
        {
            return responsavelEmpresaDAO.salvar(pessoa); 
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }
    
    @Override
    public PessoaBean alterarResponsavelEmpresa(PessoaBean pessoa) throws SonolucroControleException
    {
        try
        {
            return responsavelEmpresaDAO.alterar(pessoa); 
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }
    
    @Override
    public void apagarResponsavelEmpresa(PessoaBean pessoa) throws SonolucroControleException
    {
        try
        {
            responsavelEmpresaDAO.apagar(pessoa);
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }
    
    @Override
    public PaisBean buscarPais(Long id) throws SonolucroControleException
    {
        try
        {
            return paisDAO.buscarPais(id); 
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }
    
    @Override
    public EstadoBean buscarEstado(Long id) throws SonolucroControleException
    {
        try
        {
            return estadoDAO.buscarEstado(id); 
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }
    
    @Override
    public CidadeBean buscarCidade(Long id) throws SonolucroControleException
    {
        try
        {
            return cidadeDAO.buscarCidade(id);
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }
    
    @Override
    public List<PessoaBean> listarResponsavelEmpresa() throws SonolucroControleException
    {
        try
        {
            return responsavelEmpresaDAO.listar(); 
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }
}
