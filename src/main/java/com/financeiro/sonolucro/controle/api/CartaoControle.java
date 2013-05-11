package com.financeiro.sonolucro.controle.api;

import java.util.List;

import com.financeiro.sonolucro.bean.api.CartaoBean;
import com.financeiro.sonolucro.bean.api.FaturaCartaoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.util.SonolucroControleException;

/*
 * Interface para controle da entidade Cartão
 * 
 * @author Rodrigo
 * @since 10/08/2012
 * @version 1.0
 */
public interface CartaoControle<T extends CartaoBean>
{

    public T salvar(T cartao) throws SonolucroControleException;

    public T alterar(T cartao) throws SonolucroControleException;

    public void apagar(T cartao) throws SonolucroControleException;

    public List<T> listar(UsuarioBean usuario, Boolean processarSaldo) throws SonolucroControleException;

    public T buscarCartao(Long id) throws SonolucroControleException;

    public FaturaCartaoBean alterarFatura(FaturaCartaoBean fatura) throws SonolucroControleException;
}
