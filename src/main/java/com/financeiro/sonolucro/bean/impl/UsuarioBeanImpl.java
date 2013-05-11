package com.financeiro.sonolucro.bean.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


import com.financeiro.sonolucro.bean.api.UsuarioBean;
import org.hibernate.annotations.ForeignKey;

@Entity(name = "Usuario")
@Table(name = "usuario")
public class UsuarioBeanImpl extends PessoaBeanImpl implements UsuarioBean, Serializable
{

    private static final long serialVersionUID = 8780541195380795026L;
    
    public static final String PERMISSAO_USUARIO = "ROLE_USUARIO";
    public static final String PERMISSAO_ADMINISTRADOR = "ROLE_ADMINISTRADOR";
    
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, length = 30)
    private String nome;
    @Column(nullable = false, length = 30)
    private String sobrenome;
    @Column(nullable = false, length = 30)
    private String email;
    @Column(nullable = false, length = 10)
    private String senha;
    @ManyToOne(targetEntity = IdiomaBeanImpl.class)
    @JoinColumn(name = "ididioma", nullable = false)
    @ForeignKey(name = "fk_usuario1")
    private IdiomaBeanImpl idioma;
    @Column(updatable = false, nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;
    @Column(nullable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataAcesso;
    @Column(nullable = false)
    private Integer sexo;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @JoinTable(
	 name = "usuario_permissao",
    uniqueConstraints =
    {
        @UniqueConstraint(columnNames =
        {
            "usuario", "permissao"
        })
    },
    joinColumns =
    @JoinColumn(name = "usuario"))
    @Column(name = "permissao", length = 50)
    private Set<String> permissao = new HashSet<String>();
    @Column(nullable = false)
    private Boolean status;

    public UsuarioBeanImpl()
    {
        
    }

    public UsuarioBeanImpl(UsuarioBean usuario)
    {
        setId(usuario.getId());
        setNome(usuario.getNome());
        setSobrenome(usuario.getSobrenome());
        setPermissao(usuario.getPermissao());
        setEmail(usuario.getEmail());
        setSenha(usuario.getSenha());
        setDataNascimento(usuario.getDataNascimento());
        setDataAcesso(usuario.getDataAcesso());
        setDataCadastro(usuario.getDataCadastro());
        setIdioma(usuario.getIdioma());
        setSexo(usuario.getSexo());
        setStatus(usuario.getStatus());
    }

    public UsuarioBeanImpl(String nome, String sobrenome, String email, String senha,
            IdiomaBeanImpl idioma, Date dataCadastro, Date dataNascimento, Date dataAcesso,
            Integer sexo, Boolean status)
    {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.idioma = idioma;
        this.dataCadastro = dataCadastro;
        this.dataNascimento = dataNascimento;
        this.dataAcesso = dataAcesso;
        this.sexo = sexo;
        this.status = status;
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
    public Set<String> getPermissao()
    {
        return permissao;
    }

    @Override
    public void setPermissao(Set<String> permissao)
    {
        this.permissao = permissao;
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
    public String getSenha()
    {
        return senha;
    }

    @Override
    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    @Override
    public IdiomaBeanImpl getIdioma()
    {
        return idioma;
    }

    @Override
    public void setIdioma(IdiomaBeanImpl idioma)
    {
        this.idioma = idioma;
    }

    @Override
    public Date getDataCadastro()
    {
        return dataCadastro;
    }

    @Override
    public void setDataCadastro(Date dataCadastro)
    {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public Date getDataNascimento()
    {
        return dataNascimento;
    }

    @Override
    public void setDataNascimento(Date dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public Date getDataAcesso()
    {
        return dataAcesso;
    }

    @Override
    public void setDataAcesso(Date dataAcesso)
    {
        this.dataAcesso = dataAcesso;
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
    public String toString()
    {
        return String.format("%s %s", getNome(), getSobrenome());
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final UsuarioBeanImpl other = (UsuarioBeanImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
            return false;
        return true;
    }
}
