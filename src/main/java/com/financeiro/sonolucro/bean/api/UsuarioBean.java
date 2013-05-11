package com.financeiro.sonolucro.bean.api;

import java.util.Date;
import java.util.Set;

import com.financeiro.sonolucro.bean.impl.IdiomaBeanImpl;

/**
 * @since 05/08/2012
 * @author Rodrigo Romero
 * @version 1.0
 */
public interface UsuarioBean
{

    public Long getId();

    public void setId(Long id);

    public String getNome();

    public void setNome(String nome);
    
    public String getSobrenome(); 
    
    public void setSobrenome(String sobrenome); 

    public String getEmail();

    public void setEmail(String email);

    public String getSenha();

    public void setSenha(String senha);

    public Integer getSexo();

    public void setSexo(Integer sexo);

    public IdiomaBeanImpl getIdioma();

    public void setIdioma(IdiomaBeanImpl idioma);

    public Set<String> getPermissao();

    public void setPermissao(Set<String> permissao);

    public Date getDataCadastro();

    public void setDataCadastro(Date dataCadastro);

    public Date getDataNascimento();

    public void setDataNascimento(Date dataNascimento);

    public Date getDataAcesso();

    public void setDataAcesso(Date dataAcesso);

    public Boolean getStatus();

    public void setStatus(Boolean status);
   
}