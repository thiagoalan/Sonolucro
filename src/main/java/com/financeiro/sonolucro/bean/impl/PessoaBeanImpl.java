
package com.financeiro.sonolucro.bean.impl;

import com.financeiro.sonolucro.bean.api.CidadeBean;
import com.financeiro.sonolucro.bean.api.PessoaBean;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author rodrigo
 */
public class PessoaBeanImpl implements PessoaBean, Serializable
{
    private static final long serialVersionUID = -57353621374601750L;
    
    private Long id; 
    private String nome; 
    private String sobrenome; 
    private Integer sexo; 
    private Date dataNascimento; 
    private String cpf; 
    private CidadeBean cidadeOndeReside;
    private String telefone; 
    private String email; 

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getSobrenome()
    {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome)
    {
        this.sobrenome = sobrenome;
    }

    public Integer getSexo()
    {
        return sexo;
    }

    public void setSexo(Integer sexo)
    {
        this.sexo = sexo;
    }

    public Date getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public CidadeBean getCidadeOndeReside()
    {
        return cidadeOndeReside;
    }

    public void setCidadeOndeReside(CidadeBean cidadeOndeReside)
    {
        this.cidadeOndeReside = cidadeOndeReside;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PessoaBeanImpl other = (PessoaBeanImpl) obj;
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

   
    
}
