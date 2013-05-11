
package com.financeiro.sonolucro.bean.impl;

import com.financeiro.sonolucro.bean.api.CidadeBean;
import com.financeiro.sonolucro.bean.api.EstadoBean;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

/**
 * Implementação padrão da interface CidadeBean
 * representa as cidades dos Estados
 * @author Rodrigo Romero
 * @since 17/11/2012
 * @version 1.0
 */
@Entity(name = "Cidade")
@Table(name = "cidade")
public class CidadeBeanImpl implements CidadeBean, Serializable
{
    private static final long serialVersionUID = 5727016992882423105L;
    @Id
    private Long id;
    @Column(nullable = false, length = 100)
    private String nome;
    @ManyToOne(targetEntity = EstadoBeanImpl.class, cascade= CascadeType.REMOVE)
    @JoinColumn(name = "idestado", nullable = false)
    @ForeignKey(name = "fk_cidade1")
    private EstadoBean estado; 

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
    public EstadoBean getEstado()
    {
        return estado;
    }

    @Override
    public void setEstado(EstadoBean estado)
    {
        this.estado = estado;
    }
    
    @Override
    public String toString()
    {
        return String.format("%s %s", getNome(), getEstado());
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
        final CidadeBeanImpl other = (CidadeBeanImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
            return false;
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome))
            return false;
        if (this.estado != other.estado && (this.estado == null || !this.estado.equals(other.estado)))
            return false;
        return true;
    }
    
    
}
