package com.financeiro.sonolucro.controle.api;

import java.util.List;

import com.financeiro.sonolucro.bean.api.ContaBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.util.SonolucroControleException;

/*
 * Interface para controle da Entidade Conta
 * 
 * @author Rodrigo
 * @since 10/08/2012
 * @version 1.0.0
 * 
 */

public interface ContaControle<T extends ContaBean>
{
	public T salvar(T conta) throws SonolucroControleException; 
	
	public T alterar(T conta) throws SonolucroControleException; 
	
	public void apagar(T conta) throws SonolucroControleException;  
	
	public List<T> listaContasPorTipo(T conta) throws SonolucroControleException;
	
	public List<T> listar(UsuarioBean usuario) throws SonolucroControleException;

}
