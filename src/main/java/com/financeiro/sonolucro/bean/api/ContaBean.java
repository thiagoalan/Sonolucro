package com.financeiro.sonolucro.bean.api;

/**
 * @since 05/08/2012
 * @author Rodrigo Romero
 * @version 1.0
 */
public interface ContaBean
{

    public Long getId();

    public void setId(Long id);

    public String getNome();

    public void setNome(String nome);

    public Integer getTipo();

    public void setTipo(Integer tipo);

    public GrupoBean getGrupo();

    public void setGrupo(GrupoBean grupo);

    public Boolean getStatus();

    public void setStatus(Boolean status);    
    
}