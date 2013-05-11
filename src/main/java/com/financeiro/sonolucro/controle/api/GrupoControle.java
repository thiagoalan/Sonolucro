package com.financeiro.sonolucro.controle.api;

import java.util.List;

import com.financeiro.sonolucro.bean.api.GrupoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.util.SonolucroControleException;

/*
 * Inteface para controle da entidade Grupo.
 * 
 * @author  Rodrigo
 * @since 10/08/2012
 * @version 1.0.0
 * 
 */

public interface GrupoControle<T extends GrupoBean>
{
	public T salvar(T grupo) throws SonolucroControleException; 
	
	public T alterar(T grupo) throws SonolucroControleException; 
	
	public void apagar(T grupo) throws SonolucroControleException;
	
	public List<T> listar(UsuarioBean usuario) throws SonolucroControleException; 
 
}
