package com.financeiro.sonolucro.controle.api;

import com.financeiro.sonolucro.bean.api.IdiomaBean;
import java.util.List;

import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.util.SonolucroControleException;
import java.util.Collection;

/**
 * Interface para controle da Entidade Usuário
 *
 * @author rodrigo
 * @since 10/08/2012
 * @version 1.0.0
 */
public interface UsuarioControle<T extends UsuarioBean>
{

    public T salvar(T usuario) throws SonolucroControleException;

    public T alterar(T usuario) throws SonolucroControleException;

    public void apagar(T usuario) throws SonolucroControleException;

    public T buscarPorEmail(String email) throws SonolucroControleException;

    public List<IdiomaBean> listarIdiomas() throws SonolucroControleException;

    public Collection<T> listarUsuarios() throws SonolucroControleException;
}
