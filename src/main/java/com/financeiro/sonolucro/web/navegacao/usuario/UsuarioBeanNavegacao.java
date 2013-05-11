
package com.financeiro.sonolucro.web.navegacao.usuario;

import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.bean.impl.UsuarioBeanImpl;

/**
 *
 * @author Rodrigo Romero
 * @since 28/02/2013
 * @version 1.0
 */
public class UsuarioBeanNavegacao extends UsuarioBeanImpl
{
   
    private String permissaoStr; 
    
    public UsuarioBeanNavegacao()
    {
        
    }
    
    public UsuarioBeanNavegacao(UsuarioBean usuario)
    {
        super(usuario); 
    }

    public String getPermissaoStr()
    {
        return permissaoStr;
    }

    public void setPermissaoStr(String permissaoStr)
    {
        this.permissaoStr = permissaoStr;
    }
}
