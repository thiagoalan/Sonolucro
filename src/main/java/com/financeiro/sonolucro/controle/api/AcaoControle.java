
package com.financeiro.sonolucro.controle.api;

import com.financeiro.sonolucro.bean.api.AcaoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.util.SonolucroControleException;
import java.util.List;

/**
 * @author Rodrigo Romero
 * @since 23/03/2013
 * @version 1.0
 */
public interface AcaoControle<T extends AcaoBean>
{
    public T salvar(T acao) throws SonolucroControleException; 
    
    public T alterar(T acao) throws SonolucroControleException; 
    
    public void apagar(T acao) throws SonolucroControleException; 
    
    public List<T> listar(UsuarioBean usuario) throws SonolucroControleException; 
}
