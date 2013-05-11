
package com.financeiro.sonolucro.bean.impl;

import com.financeiro.sonolucro.bean.api.CidadeBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

/**
 * @author rodrigo
 * @since 17/11/2012
 * @version 1.0
 */
@Entity(name = "ResponsavelEmpresa")
@Table(name = "responsavel_empresa")
public class ResponsavelEmpresaBeanImpl extends PessoaBeanImpl
{
    private static final long serialVersionUID = -7476182700077118797L;

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String sobrenome;
    private Integer sexo;
    private String cpf;
    @ManyToOne(targetEntity = CidadeBeanImpl.class)
    @JoinColumn(name = "idcidade", nullable = false)
    @ForeignKey(name = "fk_responsavelempresa1")
    private CidadeBean cidadeOndeReside;
    private String telefone;
    private String email;
    
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
    public String getSobrenome()
    {
        return sobrenome;
    }

    @Override
    public void setSobrenome(String sobrenome)
    {
        this.sobrenome = sobrenome;
    }

    @Override
    public String getTelefone()
    {
        return telefone;
    }

    @Override
    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    @Override
    public String getEmail()
    {
        return email;
    }

    @Override
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    @Override
    public Integer getSexo()
    {
        return sexo; 
    }
    
    @Override
    public void setSexo(Integer sexo)
    {
        this.sexo = sexo;
    }
    
    @Override
    public CidadeBean getCidadeOndeReside()
    {
        return cidadeOndeReside; 
    }
    
    @Override
    public void setCidadeOndeReside(CidadeBean cidadeOndeReside)
    {
        this.cidadeOndeReside = cidadeOndeReside; 
    }
    
    @Override
    public String getCpf()
    {
        return cpf; 
    }
    
    @Override
    public void setCpf(String cpf)
    {
        this.cpf = cpf; 
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
        final ResponsavelEmpresaBeanImpl other = (ResponsavelEmpresaBeanImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
            return false;
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome))
            return false;
        if ((this.sobrenome == null) ? (other.sobrenome != null) : !this.sobrenome.equals(other.sobrenome))
            return false;
        if ((this.cpf == null) ? (other.cpf != null) : !this.cpf.equals(other.cpf))
            return false;
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email))
            return false;
        return true;
    }
    
    @Override
    public String toString()
    {
        return this.nome + " " + sobrenome; 
    }
}
