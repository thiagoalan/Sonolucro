
package com.financeiro.sonolucro.controle.api;

import com.financeiro.sonolucro.bean.api.ObjetivoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.util.SonolucroControleException;
import java.util.List;

/**
 * @author Rodrigo Romero
 * @since 04/03/2013
 * @version 1.0
 */
public interface ObjetivoControle<T extends ObjetivoBean>
{
    public T salvar(T objetivo) throws SonolucroControleException; 
    
    public T alterar(T objetivo) throws SonolucroControleException; 
    
    public void apagar(T objetivo) throws SonolucroControleException; 
    
    public List<T> listar(UsuarioBean usuario) throws SonolucroControleException; 
}
