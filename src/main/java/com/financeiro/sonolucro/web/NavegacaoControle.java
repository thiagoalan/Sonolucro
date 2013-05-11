
package com.financeiro.sonolucro.web;

import com.financeiro.sonolucro.util.SonolucroViewException;
import javax.faces.event.ActionEvent;

/**
 *
 * @author rodrigo
 * @since 26/12/2012
 * @version 1.0
*/
public abstract class NavegacaoControle<T extends Object>
{
    
    public abstract void salvar(ActionEvent event);
    
    public abstract void alterar(ActionEvent event); 
    
    public abstract void apagar(ActionEvent event); 
    
    public abstract void preparaAlteracao(ActionEvent event); 
    
    protected abstract void limpaTela();
    
    protected abstract void preparaBeanParaSalvar(T bean) throws SonolucroViewException; 
    
    protected abstract void preparaBeanParaAlterar(T bean) throws SonolucroViewException;
    
    protected abstract void preparaTela() throws SonolucroViewException;
    
}
