
package com.financeiro.sonolucro.bean.impl;

import com.financeiro.sonolucro.bean.api.PaisBean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Implementação padrão de PaisBean, representa os países
 * 
 * @author Rodrigo Romero
 * @since 17/11/2012
 * @version 1.0
 */

@Entity(name = "Pais")
@Table(name = "pais")
public class PaisBeanImpl implements PaisBean, Serializable
{
    private static final long serialVersionUID = 5850166268635373174L;
    @Id
    private Long id;
    @Column(nullable = false, length = 20)
    private String nome; 
    @Column(nullable = true, length = 4)
    private String sigla; 
    @Column(nullable = true, length = 12)
    private String continente;
   

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
    public String getNome()
    {
        return nome;
    }

    @Override
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    @Override
    public String getSigla()
    {
        return sigla;
    }

    @Override
    public void setSigla(String sigla)
    {
        this.sigla = sigla;
    }

    @Override
    public String getContinente()
    {
        return continente;
    }

    @Override
    public void setContinente(String continente)
    {
        this.continente = continente;
    }
    
    @Override
    public String toString()
    {
        return getNome();
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
        final PaisBeanImpl other = (PaisBeanImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
            return false;
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome))
            return false;
        if ((this.sigla == null) ? (other.sigla != null) : !this.sigla.equals(other.sigla))
            return false;
        if ((this.continente == null) ? (other.continente != null) : !this.continente.equals(other.continente))
            return false;
        return true;
    }
    
    
}
