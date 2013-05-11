
package com.financeiro.sonolucro.dao;

import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.util.List;

/**
 * Classe padrão para persistencia
 * 
 * @author rodrigo
 * @since 22/12/2012
 * @version 1.0
 */

public abstract class DAO<T extends Object>
{
    public abstract T salvar(T bean) throws SonolucroDAOException; 
    
    public abstract T alterar(T bean) throws SonolucroDAOException; 
    
    public abstract void apagar(T bean) throws SonolucroDAOException; 
   
    public abstract List<T> listar() throws SonolucroDAOException; 
    
    public abstract List<T> listar(Long i) throws SonolucroDAOException; 
    
}
