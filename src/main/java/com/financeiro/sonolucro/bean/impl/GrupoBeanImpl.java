package com.financeiro.sonolucro.bean.impl;

import java.io.Serializable;
import com.financeiro.sonolucro.bean.api.GrupoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.ForeignKey;


/**
 * Implementação padrão da Interface GrupoBean Represanta o agrupamento de
 * contas Grupo Casa agrupamentos ex: água, luz, aluguel, condominio.
 *
 * @author Rodrigo Romero
 * @since 10/08/2012
 * @version 1.0
 */

@Entity(name = "Grupo")
@Table(name = "grupo")
public class GrupoBeanImpl implements GrupoBean, Serializable
{

    private static final long serialVersionUID = 7690819278378639408L;
    
    
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = true, length = 20)
    private String nome;
    @ManyToOne(targetEntity = UsuarioBeanImpl.class)
    @JoinColumn(name = "idusuario")
    @ForeignKey(name = "fk_grupo1")
    private UsuarioBean usuario;
    @Column(name = "status", nullable = false)
    private Boolean status; 

    public GrupoBeanImpl()
    {
    }
    
    public GrupoBeanImpl(GrupoBean grupo)
    {
        setId(grupo.getId()); 
        setNome(grupo.getNome()); 
        setUsuario(grupo.getUsuario());
        setStatus(grupo.getStatus()); 
    }

    public GrupoBeanImpl(String nome, UsuarioBean usuario, Boolean status)
    {
        setNome(nome);
        setUsuario(usuario);
        setStatus(status);
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
    public UsuarioBean getUsuario()
    {
        return usuario;
    }

    @Override
    public void setUsuario(UsuarioBean usuario)
    {
        this.usuario = usuario;
    }

    @Override
    public Boolean getStatus()
    {
        return status;
    }

    @Override
    public void setStatus(Boolean status)
    {
        this.status = status;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 97 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final GrupoBeanImpl other = (GrupoBeanImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
            return false;
        return true;
    }
    
}
