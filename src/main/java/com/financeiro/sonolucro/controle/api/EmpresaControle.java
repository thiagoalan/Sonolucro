
package com.financeiro.sonolucro.controle.api;

import com.financeiro.sonolucro.bean.api.EmpresaBean;
import com.financeiro.sonolucro.util.SonolucroControleException;
import java.util.List;

/**
 *
 * @author rodrigo
 */
public interface EmpresaControle<T extends EmpresaBean>
{
    public T salvar(T empresa) throws SonolucroControleException;
    
    public T alterar(T empresa) throws SonolucroControleException; 

    public void apagar(T empresa) throws SonolucroControleException; 
    
    public List<T> listar() throws SonolucroControleException;
    
    public T buscarPorCnpj(String cnpj) throws SonolucroControleException; 
    
    public List<T> listarPorTipo(String tipo) throws SonolucroControleException; 
    
    public List<T> listarPorPais(String pais) throws SonolucroControleException; 
    
    public List<T> listarPorPaisEstado(String pais) throws SonolucroControleException; 
    
    public List<T> listarPorEstadoCidade(String estado, String cidade) throws SonolucroControleException;
    
    public List<T> pesquisarEmpresasClienteOuNao(Boolean ecliente) throws SonolucroControleException; 
} 
