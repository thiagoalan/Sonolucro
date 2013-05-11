
package com.financeiro.sonolucro.bean.impl;

import com.financeiro.sonolucro.bean.api.DicaBean;
import com.financeiro.sonolucro.bean.api.IdiomaBean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * Implementação padrão da interface DicaBean
 * 
 * @author rodrigo
 * @since 24/12/2012
 * @version 1.0.0
 */

@Entity(name = "Dica")
@Table(name = "dicas")
public class DicaBeanImpl implements DicaBean, Serializable
{
    public static final Integer CATEGORIA_SISTEMA = 1; 
    public static final Integer CATEGORIA_ECONOMIA = 2;
    public static final Integer CATEGORIA_INVESTIMENTO = 3; 
    
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, length = 50)
    private String titulo; 
    @Lob
    @Column(nullable = false, length = 2000)
    private String texto;
    @ManyToOne(targetEntity = IdiomaBeanImpl.class)
    @JoinColumn(name = "ididioma")
    @ForeignKey(name = "fk_dica1")
    private IdiomaBean idioma;
    @Column(name = "categoria", nullable = false)
    private Integer categoria;
    @Column(name = "autor", nullable = true, length = 50) 
    private String autor; 
    
    public DicaBeanImpl()
    {
        
    }
    
    public DicaBeanImpl(DicaBean dica)
    {
        setId(dica.getId()); 
        setTitulo(dica.getTitulo());
        setTexto(dica.getTexto()); 
        setIdioma(dica.getIdioma()); 
        setCategoria(dica.getCategoria());
        setAutor(dica.getAutor()); 
    }
    
    public DicaBeanImpl(String titulo, String texto, IdiomaBean idioma,
            Integer categoria, String autor)
    {
        setTitulo(titulo); 
        setTexto(texto); 
        setIdioma(idioma); 
        setCategoria(categoria);
        setAutor(autor); 
    }
    
    @Override
    public Long getId()
    {
        return id;
    }

    @Override
    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public String getTitulo()
    {
        return titulo;
    }

    @Override
    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    @Override
    public String getTexto()
    {
        return texto;
    }

    @Override
    public void setTexto(String texto)
    {
        this.texto = texto;
    }

    @Override
    public IdiomaBean getIdioma()
    {
        return idioma;
    }

    @Override
    public void setIdioma(IdiomaBean idioma)
    {
        this.idioma = idioma;
    }

    @Override
    public Integer getCategoria()
    {
        return categoria;
    }

    @Override
    public void setCategoria(Integer categoria)
    {
        this.categoria = categoria;
    }

    @Override
    public String getAutor()
    {
        return autor;
    }

    @Override
    public void setAutor(String autor)
    {
        this.autor = autor;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final DicaBeanImpl other = (DicaBeanImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
            return false;
        return true;
    }
    
    
}
