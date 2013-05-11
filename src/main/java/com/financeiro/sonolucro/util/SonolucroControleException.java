
package com.financeiro.sonolucro.util;

import java.io.Serializable;

/**
 * Classe de excessão de controle padrão 
 * 
 * @author rodrigo
 * @since 19/11/2012
 * @version 1.0.0
 */
public class SonolucroControleException extends Exception implements Serializable
{
    private static final long serialVersionUID = -4659990471442203738L;
    private String message; 
    
    public SonolucroControleException()
    {
        super(); 
    }
    
    public SonolucroControleException(Exception e)
    {
        super(e); 
    }
    
    public SonolucroControleException(String message)
    {
        super(message); 
    }
    
    public SonolucroControleException(String message, Exception e)
    {
        super(message, e); 
    }
    
    public String getMessage()
    {
        return super.getMessage(); 
    }
}
