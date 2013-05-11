
package com.financeiro.sonolucro.util;

/**
 * Classe de exceção DAO padrão
 * @author rodrigo
 * @since 30/11/2012
 * @version 1.0.0
 */
public class SonolucroDAOException extends Exception
{
    private static final long serialVersionUID = -2321774010389666839L;
    private String message; 
    
    public SonolucroDAOException()
    {
        super(); 
    }
    
    public SonolucroDAOException(Exception e)
    {
        super(e); 
    }
    
    public SonolucroDAOException(String message)
    {
        super(message); 
    }
    
    public SonolucroDAOException(String message, Exception e)
    {
        super(message, e); 
    }
    
    @Override
    public String getMessage()
    {
        return super.getMessage(); 
    }
    
            
}
