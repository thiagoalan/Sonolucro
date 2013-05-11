
package com.financeiro.sonolucro.bean.api;

/**
 * Interface padrão
 * Representação de dicas, ajuda a usuario a operar o sistema gera e conselhos
 * financeiros.
 * 
 * @author rodrigo
 * @since 23/12/2012
 * @version 1.0
 */
public interface DicaBean
{
    public Long getId();
    
    public void setId(Long id); 
    
    public String getTitulo();
    
    public void setTitulo(String titulo); 
    
    public String getTexto();
    
    public void setTexto(String texto); 
    
    public IdiomaBean getIdioma(); 
    
    public void setIdioma(IdiomaBean idioma);
    
    public Integer getCategoria(); 
    
    public void setCategoria(Integer categoria); 
    
    public String getAutor(); 
    
    public void setAutor(String autor); 
     
}
