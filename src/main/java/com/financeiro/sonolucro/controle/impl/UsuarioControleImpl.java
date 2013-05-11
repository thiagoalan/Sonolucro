package com.financeiro.sonolucro.controle.impl;

import java.io.Serializable;

import javax.inject.Inject;

import com.financeiro.sonolucro.bean.api.ContaBean;
import com.financeiro.sonolucro.bean.api.GrupoBean;
import com.financeiro.sonolucro.bean.api.IdiomaBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.bean.impl.ContaBeanImpl;
import com.financeiro.sonolucro.bean.impl.GrupoBeanImpl;
import com.financeiro.sonolucro.controle.api.UsuarioControle;
import com.financeiro.sonolucro.dao.ContaDAO;
import com.financeiro.sonolucro.dao.GrupoDAO;
import com.financeiro.sonolucro.dao.IdiomaDAO;
import com.financeiro.sonolucro.dao.UsuarioDAO;
import com.financeiro.sonolucro.util.MensagemUtil;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Implementação padrao da interface de controle UsuarioControle
 *
 * @author Rodrigo Romero
 * @since 10/08/2012
 * @version 1.0
 */
@Stateless
public class UsuarioControleImpl implements UsuarioControle<UsuarioBean>, Serializable
{
    private static final long serialVersionUID = 8515676102166378922L;

    @Inject
    private UsuarioDAO usuarioDAO;
    @Inject
    private GrupoDAO grupoDAO;
    @Inject
    private ContaDAO contaDAO;
    @Inject 
    private IdiomaDAO idiomaDAO; 

    @Override
    public UsuarioBean salvar(UsuarioBean usuario) throws SonolucroControleException
    {
        try
        {
            usuarioDAO.salvar(usuario);
            salvaGruposPadrao(usuario);

            return usuario;
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e.getMessage());
        }
    }

    @Override
    public UsuarioBean alterar(UsuarioBean usuario) throws SonolucroControleException
    {
        try
        {
            return usuarioDAO.alterar(usuario);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e.getMessage());
        }
    }

    @Override
    public void apagar(UsuarioBean usuario) throws SonolucroControleException
    {
        // TODO Auto-generated method stub
    }

    @Override
    public UsuarioBean buscarPorEmail(String email) throws SonolucroControleException
    {
        try
        {
            return usuarioDAO.buscarPorEmail(email);
        }
        catch(SonolucroDAOException e)
        {
            throw new SonolucroControleException(e); 
        }
    }

    @Override
    public Collection<UsuarioBean> listarUsuarios() throws SonolucroControleException
    {
        try
        {
            return usuarioDAO.listar();
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e.getMessage());
        }
    }

    private void salvaGruposPadrao(UsuarioBean usuario) throws SonolucroControleException
    {
        try
        {
            GrupoBean grupoMoradia = new GrupoBeanImpl(MensagemUtil.getMensagem(
                    "grupo_padrao_moradia"), usuario, true);
            grupoDAO.salvar(grupoMoradia);
            salvaContaPadrao(MensagemUtil.getMensagem(
                    "conta_padrao_luz"), ContaBeanImpl.TIPO_DESPESA, grupoMoradia, true);
            salvaContaPadrao(MensagemUtil.getMensagem(
                    "conta_padrao_agua"), ContaBeanImpl.TIPO_DESPESA, grupoMoradia, true);
            salvaContaPadrao(MensagemUtil.getMensagem(
                    "conta_padrao_telefone"), ContaBeanImpl.TIPO_DESPESA, grupoMoradia, true);

            GrupoBean grupoEducacao = new GrupoBeanImpl(MensagemUtil.getMensagem(
                    "grupo_padrao_educacao"), usuario, true);
            grupoDAO.salvar(grupoEducacao);
            salvaContaPadrao(MensagemUtil.getMensagem(
                    "conta_padrao_faculdade"), ContaBeanImpl.TIPO_DESPESA, grupoEducacao, true);

            GrupoBean grupoTransporte = new GrupoBeanImpl(MensagemUtil.getMensagem(
                    "grupo_padrao_transporte"), usuario, true);
            grupoDAO.salvar(grupoTransporte);
            salvaContaPadrao(MensagemUtil.getMensagem(
                    "conta_padrao_combustivel"), ContaBeanImpl.TIPO_DESPESA, grupoTransporte, true);

            GrupoBean grupoEmprego = new GrupoBeanImpl(MensagemUtil.getMensagem(
                    "grupo_padrao_emprego"), usuario, true);
            grupoDAO.salvar(grupoEmprego);
            salvaContaPadrao(MensagemUtil.getMensagem(
                    "conta_padrao_salario"), ContaBeanImpl.TIPO_RECEITA, grupoEmprego, true);

            GrupoBean grupoLazer = new GrupoBeanImpl(MensagemUtil.getMensagem(
                    "grupo_padrao_lazer"), usuario, true);
            grupoDAO.salvar(grupoLazer);
            salvaContaPadrao(MensagemUtil.getMensagem(
                    "conta_padrao_cinema"), ContaBeanImpl.TIPO_DESPESA, grupoLazer, true);
            salvaContaPadrao(MensagemUtil.getMensagem(
                    "conta_padrao_teatro"), ContaBeanImpl.TIPO_DESPESA, grupoLazer, true);

            GrupoBean grupoSaude = new GrupoBeanImpl(MensagemUtil.getMensagem(
                    "grupo_padrao_saude"), usuario, true);
            grupoDAO.salvar(grupoSaude);
            salvaContaPadrao(MensagemUtil.getMensagem(
                    "conta_padrao_convenio_medico"), ContaBeanImpl.TIPO_DESPESA, grupoSaude, true);
            salvaContaPadrao(MensagemUtil.getMensagem(
                    "conta_padrao_remedios"), ContaBeanImpl.TIPO_DESPESA, grupoSaude, true);

            GrupoBean grupoAlimentacao = new GrupoBeanImpl(MensagemUtil.getMensagem(
                    "grupo_padrao_alimentacao"), usuario, true);
            grupoDAO.salvar(grupoAlimentacao);
            salvaContaPadrao(MensagemUtil.getMensagem(
                    "conta_padrao_mercado"), ContaBeanImpl.TIPO_DESPESA, grupoAlimentacao, true);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException("Erro ao salvar grupos e contas padrões para o novo usuário");
        }
    }

    private void salvaContaPadrao(String nome, Integer tipo,
            GrupoBean grupo, Boolean status) throws SonolucroDAOException
    {
        ContaBean conta = new ContaBeanImpl(nome, tipo, grupo, status);
        contaDAO.salvar(conta);
    }

    public List<IdiomaBean> listarIdiomas() throws SonolucroControleException
    {
        try
        {
            return idiomaDAO.listar();
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException("Erro ao listar Idiomas.");
        }
    }
}
