
package com.financeiro.sonolucro.util;

/**
 * Classe padrão para exceções de validação ou montagem de conteúdo 
 * da view
 * 
 * @author rodrigo
 * @since 01/12/2012
 * @version 1.0.0
 */
public class SonolucroViewException extends Exception
{
    private static final long serialVersionUID = 2134398815020605639L;
    private String message; 
    private String messageUsuario; 
    
    public SonolucroViewException()
    {
        super(); 
    }
    
    public SonolucroViewException(Exception e)
    {
        super(e); 
    }
    
    public SonolucroViewException(String message)
    {
        super(message);
    }
    
    public SonolucroViewException(String message, String messageUsuario)
    {
        super(message);
        setMessageUsuario(messageUsuario);
    }
    
    public SonolucroViewException(String message, Exception e)
    {
        super(message, e); 
    }
    
    public SonolucroViewException(String message, String messageUsuario, Exception e)
    {
        super(message, e); 
        setMessageUsuario(messageUsuario); 
    }
    
    @Override
    public String getMessage()
    {
        return super.getMessage();  
    }
    
    public String getMessageUsuario()
    {
        return messageUsuario; 
    }
    
    public void setMessageUsuario(String messageUsuario)
    {
        this.messageUsuario = messageUsuario; 
    }
    
}
