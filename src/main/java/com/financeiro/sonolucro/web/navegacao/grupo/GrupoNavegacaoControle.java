package com.financeiro.sonolucro.web.navegacao.grupo;

import com.financeiro.sonolucro.bean.api.GrupoBean;
import com.financeiro.sonolucro.controle.api.GrupoControle;
import com.financeiro.sonolucro.util.BeanUtil;
import com.financeiro.sonolucro.util.MensagemUtil;
import com.financeiro.sonolucro.util.RequestUtils;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroViewException;
import com.financeiro.sonolucro.web.NavegacaoControle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

/**
 * Controle da View grupo. Faz a validação do bean grupo, e interage com as view
 * que disponibilizam informação atraves do GrupoNavegacao
 *
 * @author rodrigo
 * @since 22/08/2012
 * @version 1.0
 *
 */
@Named(value = "grupoNavegacaoControle")
@ViewAccessScoped  
public class GrupoNavegacaoControle extends NavegacaoControle<GrupoBean> implements Serializable
{

    private static final long serialVersionUID = 1852290669678065392L;
    protected static final String BEAN_NAME = "grupoNavegacaoControle";
    protected static final String ID_COMPONENTE_MENSAGEM = "messageCadastroGrupo";
    protected static final String TELA_GRUPO = "grupo";
   
    @Inject
    private Conversation conversation;
    @Inject
    private GrupoControle controle;
    @Inject
    private GrupoNavegacao navegacao;
    @Inject
    private BeanUtil beanUtil;

    @Override
    public void salvar(ActionEvent event)
    {
        try
        {
            GrupoBean grupo = navegacao.getGrupo();
            preparaBeanParaSalvar(grupo);
            controle.salvar(grupo);
            preparaTela();

            MensagemUtil.mensagemOperacaoSucesso(this.ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("grupo_msg_sucesso_salvar_grupo"));

        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(this.ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("grupo_msg_erro_salvar_grupo"));

            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(this.ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("grupo_msg_erro_salvar_grupo"));
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(ActionEvent event)
    {
        try
        {
            GrupoBean grupo = navegacao.getGrupo();
            controle.apagar(grupo);
            preparaTela();

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("grupo_msg_sucesso_apagar_grupo"));

        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("grupo_msg_erro_apagar_grupo"));
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("grupo_msg_erro_apagar_grupo"));
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(ActionEvent event)
    {
        try
        {
            preparaBeanParaAlterar(navegacao.getGrupo());
            controle.alterar(navegacao.getGrupo());

            preparaTela();

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("grupo_msg_sucesso_alterar_grupo"));

        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("grupo_msg_erro_apagar_grupo"));
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("grupo_msg_erro_apagar_grupo"));
            e.printStackTrace();
        }
    }

    @Override
    public void preparaAlteracao(ActionEvent event)
    {
        try
        {
            GrupoBean grupo = (GrupoBean) RequestUtils.getRequest().getAttribute("grupo");

            if (grupo != null)
            {
                navegacao.setGrupo(grupo);

                if (grupo.getStatus())
                    navegacao.setStatusItemSelecionado(1);
                else
                    navegacao.setStatusItemSelecionado(2);
            }
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void preparaBeanParaSalvar(GrupoBean grupo) throws SonolucroViewException
    {
        try
        {
            grupo.setId(null);
            grupo.setUsuario(beanUtil.getUsuarioLogado());

            Integer statusInt = navegacao.getStatusItemSelecionado();

            if (statusInt.equals(1))
                grupo.setStatus(true);
            else
                grupo.setStatus(false);
        }
        catch (NullPointerException e)
        {
            String erroStr = String.format("Erro ao preparar grupo para salvar: %s", e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
    }

    @Override
    protected void preparaBeanParaAlterar(GrupoBean grupo) throws SonolucroViewException
    {
        try
        {
            Integer statusInt = navegacao.getStatusItemSelecionado();

            if (statusInt.equals(1))
                grupo.setStatus(true);
            else
                grupo.setStatus(false);
        }
        catch (NullPointerException e)
        {
            String erroStr = String.format("Erro ao preparar grupo para alterar: %s", e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
    }

    @Override
    protected void preparaTela() throws SonolucroViewException
    {
        try
        {
            limpaTela();
            List<GrupoBean> grupos = controle.listar(beanUtil.getUsuarioLogado());
            navegacao.setGrupos(grupos);
            iniciarStatus();
        }
        catch (SonolucroControleException e)
        {
            String erroStr = String.format("Erro ao preparar tela: %s", e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
    }

    @Override
    protected void limpaTela()
    {
        navegacao.setGrupo(null);
        navegacao.setGrupos(null);
        navegacao.setNomeDoGrupo(null);
        navegacao.setStatusItemSelecionado(null);
    }

    public String irParaTelaGrupo()
    {
        try
        {
            preparaTela();
            return TELA_GRUPO;
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private void iniciarStatus()
    {
        List<SelectItem> statusItens = new ArrayList<SelectItem>();

        statusItens.add(new SelectItem(null, ""));
        statusItens.add(new SelectItem(1, MensagemUtil.getMensagem("grupo_status_ativado")));
        statusItens.add(new SelectItem(2, MensagemUtil.getMensagem("grupo_status_desativado")));

        navegacao.setStatusItens(statusItens);

    }

}
