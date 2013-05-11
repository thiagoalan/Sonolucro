package com.financeiro.sonolucro.bean.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.financeiro.sonolucro.bean.api.IdiomaBean;

@Entity(name = "Idioma")
@Table(name = "usuario_idioma")
public class IdiomaBeanImpl implements IdiomaBean, Serializable
{

    private static final long serialVersionUID = 8892358449817749712L;
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = true, length = 15)
    private String idioma;
    @Column(nullable = true, length = 6)
    private String sigla;
    @Column(nullable = true)
    private Integer numeroIdioma;

    public IdiomaBeanImpl()
    {
    }

    public IdiomaBeanImpl(String idioma, String sigla, Integer numeroIdioma)
    {
        setIdioma(idioma);
        setSigla(sigla);
        setNumeroIdioma(numeroIdioma);
    }

    @Override
    public Integer getId()
    {
        return id;
    }

    @Override
    public void setId(Integer id)
    {
        this.id = id;
    }

    @Override
    public String getIdioma()
    {
        return idioma;
    }

    @Override
    public void setIdioma(String idioma)
    {
        this.idioma = idioma;
    }

    @Override
    public String getSigla()
    {

        return sigla;
    }

    @Override
    public String setSigla(String sigla)
    {
        return sigla;
    }

    @Override
    public Integer getNumeroIdioma()
    {
        return numeroIdioma;
    }

    @Override
    public void setNumeroIdioma(Integer numeroIdioma)
    {
        this.numeroIdioma = numeroIdioma;
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
        final IdiomaBeanImpl other = (IdiomaBeanImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
            return false;
        if ((this.idioma == null) ? (other.idioma != null) : !this.idioma.equals(other.idioma))
            return false;
        if ((this.sigla == null) ? (other.sigla != null) : !this.sigla.equals(other.sigla))
            return false;
        if (this.numeroIdioma != other.numeroIdioma && (this.numeroIdioma == null || !this.numeroIdioma.equals(other.numeroIdioma)))
            return false;
        return true;
    }

    
}
