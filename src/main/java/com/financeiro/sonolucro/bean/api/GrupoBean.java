package com.financeiro.sonolucro.bean.api;

/**
 * GrupoBean Representa o agrupamento de contas Grupo Casa agrupamentos ex:
 * água, luz, aluguel, condominio.
 *
 * @since 05/08/2012
 * @author Rodrigo Romero
 * @version 1.0
 */
public interface GrupoBean
{

    public Long getId();

    public void setId(Long id);

    public String getNome();

    public void setNome(String nome);

    public UsuarioBean getUsuario();

    public void setUsuario(UsuarioBean usuario);

    public Boolean getStatus();

    public void setStatus(Boolean status);
    
}