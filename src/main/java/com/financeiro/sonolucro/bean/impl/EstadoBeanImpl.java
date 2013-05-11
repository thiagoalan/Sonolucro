
package com.financeiro.sonolucro.bean.impl;

import com.financeiro.sonolucro.bean.api.EstadoBean;
import com.financeiro.sonolucro.bean.api.PaisBean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

/**
 * Implementaçao padrão da interface EstadoBean, representa os estados
 * dos Países
 * 
 * @author Rodrigo Romero
 * @since 17/11/2012
 * @version 1.0
 */

@Entity(name = "Estado")
@Table(name = "estado")
public class EstadoBeanImpl implements EstadoBean, Serializable
{
    private static final long serialVersionUID = 5285137907237477086L;

    @Id
    private Long id;
    @Column(nullable = false, length = 20)
    private String nome; 
    @Column(nullable = false, length = 4)
    private String sigla;
    @ManyToOne(targetEntity = PaisBeanImpl.class)
    @JoinColumn(name = "idpais", nullable = false)
    @ForeignKey(name = "fk_estado1")
    private PaisBean pais;
   
    
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
    public PaisBean getPais()
    {
        return pais;
    }

    @Override
    public void setPais(PaisBean pais)
    {
        this.pais = pais;
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
    public String toString()
    {
        return getSigla(); 
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final EstadoBeanImpl other = (EstadoBeanImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
            return false;
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome))
            return false;
        if ((this.sigla == null) ? (other.sigla != null) : !this.sigla.equals(other.sigla))
            return false;
        if (this.pais != other.pais && (this.pais == null || !this.pais.equals(other.pais)))
            return false;
        return true;
    }
    
    
}
