package com.financeiro.sonolucro.controle.api;

import java.util.Date;
import java.util.List;

import com.financeiro.sonolucro.bean.api.MovimentacaoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.util.SonolucroControleException;

public interface MovimentacaoControle<T extends MovimentacaoBean>
{

    public T salvar(T mov) throws SonolucroControleException;

    public T alterar(T mov) throws SonolucroControleException;

    public void apagar(T mov) throws SonolucroControleException;

    public List<T> listar(UsuarioBean usuario) throws SonolucroControleException;

    public List<T> listaPorDataLancamento(UsuarioBean usuario) throws SonolucroControleException;

    public List<T> listarPorData(UsuarioBean usuario,
                                 Date dataInicial, Date dataFinal) throws SonolucroControleException;

}
