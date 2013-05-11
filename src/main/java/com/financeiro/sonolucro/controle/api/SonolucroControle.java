
package com.financeiro.sonolucro.controle.api;

import com.financeiro.sonolucro.bean.api.CidadeBean;
import com.financeiro.sonolucro.bean.api.DicaBean;
import com.financeiro.sonolucro.bean.api.EstadoBean;
import com.financeiro.sonolucro.bean.api.IdiomaBean;
import com.financeiro.sonolucro.bean.api.PaisBean;
import com.financeiro.sonolucro.bean.api.PessoaBean;
import com.financeiro.sonolucro.util.SonolucroControleException;
import java.util.List;

/**
 * Interface de controle de uso generico
 * 
 * @author rodrigo
 * @since 17/11/2012
 * @version 1.0.0
 */
public interface SonolucroControle
{
    public DicaBean salvarDica(DicaBean dica) throws SonolucroControleException; 
    
    public DicaBean alterarDica(DicaBean dica) throws SonolucroControleException; 
    
    public void apagarDica(DicaBean dica) throws SonolucroControleException; 
    
    public List<DicaBean> listarDicas() throws SonolucroControleException; 
    
    public List<DicaBean> listarDicaPorIdioma(IdiomaBean idioma) throws SonolucroControleException; 
    
    public List<IdiomaBean> listarIdiomas() throws SonolucroControleException;
    
    public List<PaisBean> listarPaises() throws SonolucroControleException;
    
    public PaisBean buscarPais(Long id) throws SonolucroControleException; 
    
    public List<EstadoBean> listarEstados(PaisBean pais) throws SonolucroControleException;
    
    public EstadoBean buscarEstado(Long id) throws SonolucroControleException; 
    
    public List<CidadeBean> listarCidades(EstadoBean estado) throws SonolucroControleException;

    public CidadeBean buscarCidade(Long idCidade) throws SonolucroControleException; 
    
    public PessoaBean salvarResponsavelEmpresa(PessoaBean pessoa) throws SonolucroControleException; 
    
    public PessoaBean alterarResponsavelEmpresa(PessoaBean pessoa) throws SonolucroControleException;
    
    public void apagarResponsavelEmpresa(PessoaBean pessoa) throws SonolucroControleException; 
    
    public List<PessoaBean> listarResponsavelEmpresa() throws SonolucroControleException;
}
