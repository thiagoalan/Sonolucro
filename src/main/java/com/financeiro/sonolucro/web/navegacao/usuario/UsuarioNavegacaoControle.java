package com.financeiro.sonolucro.web.navegacao.usuario;

import com.financeiro.sonolucro.bean.api.IdiomaBean;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.bean.impl.IdiomaBeanImpl;
import com.financeiro.sonolucro.bean.impl.UsuarioBeanImpl;
import com.financeiro.sonolucro.controle.api.UsuarioControle;
import com.financeiro.sonolucro.util.BeanUtil;
import com.financeiro.sonolucro.util.MensagemUtil;
import com.financeiro.sonolucro.util.RequestUtils;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroViewException;
import com.financeiro.sonolucro.web.NavegacaoControle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import javax.enterprise.context.Conversation;
import javax.faces.context.ExternalContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

@Named(value = "usuarioNavegacaoControle")
@ViewAccessScoped  
public class UsuarioNavegacaoControle extends NavegacaoControle<UsuarioBean> implements Serializable
{

    protected static final String BEAN_NAME = "usuarioNavegacaoControle";
    protected static final String TELA_LOGIN = "login";
    protected static final String TELA_CADASTRO = "cadastro";
    protected static final String TELA_USUARIO = "usuario";
    protected static final String TELA_USUARIO_PESQUISA = "usuarioPesquisa";
    protected static final String TELA_USUARIO_PESQUISA_EDICAO = "usuarioPesquisaEdicao";
    protected static final String ID_MENSAGEM_TELA_CADASTRO = "messageCadastroUsuario";
    protected static final String ID_MENSAGEM_TELA_EDICAO = "messageEdicaoUsuario";
    protected static final String ID_MENSAGEM_TELA_EDICAO_PESQUISA = "messageEdicaoPesquisaUsuario";
    @Inject
    private UsuarioControle controle;
    @Inject
    private UsuarioNavegacao navegacao;
    @Inject
    private Conversation conversation;
    @Inject
    BeanUtil beanUtil;
    private List<IdiomaBeanImpl> idiomas;

    @Override
    public void salvar(ActionEvent event)
    {
        try
        {
            UsuarioBean usuario = navegacao.getUsuarioCadastro();

            if (!verificarEmail(usuario))
            {
                MensagemUtil.mensagemOperacaoErro(ID_MENSAGEM_TELA_CADASTRO, MensagemUtil.getMensagem("usuario_msg_email_cadastrado"));
                return;
            }

            preparaBeanParaSalvar(usuario);
            controle.salvar(usuario);
            //alterarIdioma();

            MensagemUtil.mensagemOperacaoSucesso(ID_MENSAGEM_TELA_CADASTRO, MensagemUtil.getMensagem("usuario_mensagem_sucesso"));
            MensagemUtil.mensagemOperacaoSucesso(ID_MENSAGEM_TELA_CADASTRO, MensagemUtil.getMensagem("usuario_mensagem_volta_tela_login"));
        }
        catch (RuntimeException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_MENSAGEM_TELA_CADASTRO, MensagemUtil.getMensagem("usuario_msg_erro_salvar"));
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_MENSAGEM_TELA_CADASTRO, MensagemUtil.getMensagem("usuario_msg_erro_salvar"));
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_MENSAGEM_TELA_CADASTRO, MensagemUtil.getMensagem("usuario_msg_erro_salvar"));
            e.printStackTrace();
        }

    }

    @Override
    public void alterar(ActionEvent event)
    {
        UsuarioBean usuario = new UsuarioBeanImpl(navegacao.getUsuarioLogado());

        try
        {
            if (!navegacao.getSenha().equals(usuario.getSenha()))
            {
                MensagemUtil.mensagemOperacaoErro(ID_MENSAGEM_TELA_EDICAO,
                        MensagemUtil.getMensagem("usuario_msg_senha_incorreta"));
                return;
            }
            
            usuario.setSenha(navegacao.getNovaSenha()); 
            alterar(usuario, ID_MENSAGEM_TELA_EDICAO);
            preparaTelaUsuarioEdicao();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void alterarUsuarioPesquisa(ActionEvent event)
    {
        try
        {
            UsuarioBean usuario = new UsuarioBeanImpl(navegacao.getUsuarioPesquisaEdicao()); 
            
            if(navegacao.getAdiministrador())
            {
               usuario.getPermissao().clear();
               usuario.getPermissao().add(UsuarioBeanImpl.PERMISSAO_USUARIO); 
               usuario.getPermissao().add(UsuarioBeanImpl.PERMISSAO_ADMINISTRADOR); 
            }
            else
            {
                usuario.getPermissao().clear();
                usuario.getPermissao().add(UsuarioBeanImpl.PERMISSAO_USUARIO); 
            }
            
            alterar(usuario, ID_MENSAGEM_TELA_EDICAO_PESQUISA);
            this.preparaTelaUsuarioPesquisaEdicao();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void alterar(UsuarioBean usuario, String idMensagem)
    {
        try
        {
            if (!verificarEmail(usuario))
            {
                MensagemUtil.mensagemOperacaoErro(idMensagem, MensagemUtil.getMensagem("usuario_msg_email_cadastrado"));
                return;
            }

            preparaBeanParaAlterar(usuario);
            controle.alterar(usuario);
            alterarIdioma();
            beanUtil.setUsuarioLogado(null);

            MensagemUtil.mensagemOperacaoSucesso(idMensagem, MensagemUtil.getMensagem("usuario_msg_sucesso_alterar_usuario"));

        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(idMensagem, MensagemUtil.getMensagem("usuario_msg_erro_alterar"));
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(idMensagem, MensagemUtil.getMensagem("usuario_msg_erro_alterar"));
            e.printStackTrace();
        }
    }

    private Boolean verificarEmail(UsuarioBean usuario) throws SonolucroViewException
    {
        try
        {
            UsuarioBean usuarioBusca = controle.buscarPorEmail(usuario.getEmail());

            if (usuarioBusca == null)
                return true;
            else
            {
                if (usuario.getId() != null)
                {
                    if (usuario.getId().longValue() == usuarioBusca.getId().longValue())
                        return true;
                    else
                        return false;
                }

                return false;
            }
        }
        catch (SonolucroControleException e)
        {
            String erroStr = String.format("Erro ao buscar usuário por login: %s", e.getMessage());
            throw new SonolucroViewException(erroStr);
        }

    }

    public String irParaTelaUsuarioEdicao()
    {
        try
        {
            preparaTelaUsuarioEdicao();
            return TELA_USUARIO;
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public String irParaTelaUsuarioPesquisa()
    {
        try
        {
            this.preparaTelaUsuarioPesquisa();
            return TELA_USUARIO_PESQUISA;
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
            return TELA_USUARIO_PESQUISA;
        }
    }

    public String irParaTelaDeLogin()
    {
        limpaTela();
        return TELA_LOGIN;
    }

    public String irParaTelaDeCadastro()
    {
        try
        {
            preparaTelaCadastro();

            return TELA_CADASTRO;
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public String irParaTelaPesquisaEdicao()
    {
        try
        {
            preparaAlteracao(null);
            preparaTelaUsuarioPesquisaEdicao();
            return TELA_USUARIO_PESQUISA_EDICAO;
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void limpaTela()
    {
        navegacao.setSexoItemSelecionado(null);
        navegacao.setIdiomaItemSelecionado(null);
        navegacao.setAdiministrador(Boolean.FALSE);
        navegacao.setConfirmaSenha("");
        navegacao.setNovaSenha("");
        navegacao.setSenha("");
        navegacao.getUsuarios().clear();
        navegacao.setUsuarioCadastro(null);
        navegacao.setUsuarioLogado(null);
        navegacao.setUsuarioPesquisaEdicao(null);
    }

    public String logout()
    {
        HttpSession session = null;

        try
        {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext external = context.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) external.getRequest();
            session = request.getSession();
            session.invalidate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (session != null)
                session = null;

            return TELA_LOGIN;
        }
    }

    @Override
    protected void preparaBeanParaSalvar(UsuarioBean usuario) throws SonolucroViewException
    {
        try
        {
            usuario.setDataCadastro(new Date());
            usuario.setDataAcesso(new Date());
            usuario.getPermissao().add(UsuarioBeanImpl.PERMISSAO_USUARIO);
            usuario.setStatus(true);
            usuario.setIdioma(processaIdiomaSelecionado());

            usuario.setSexo(navegacao.getSexoItemSelecionado());

        }
        catch (SonolucroViewException e)
        {
            String erroStr = String.format("Erro ao preparar para salvar: %s", e.getMessage());
            throw new SonolucroViewException(erroStr);
        }
    }

    private void alterarIdioma()
    {
        if (navegacao.getUsuarioLogado() != null)
        {
            if (navegacao.getUsuarioLogado().getIdioma().equals(1))
                beanUtil.mudaIdiomaParaPtBr();
            else if (navegacao.getUsuarioLogado().getIdioma().equals(2))
                beanUtil.mudaIdiomaParaEnUS();
        }
    }

    private IdiomaBeanImpl processaIdiomaSelecionado() throws SonolucroViewException
    {
        try
        {
            IdiomaBeanImpl idioma = null;

            if (idiomas == null)
                idiomas = (List) controle.listarIdiomas();

            for (IdiomaBeanImpl idiomaUsuario : idiomas)
            {
                if (idiomaUsuario.getNumeroIdioma().equals(
                        navegacao.getIdiomaItemSelecionado()))
                {
                    idioma = idiomaUsuario;
                    break;
                }
            }
            return idioma;
        }
        catch (SonolucroControleException e)
        {
            String erroStr = String.format("Erro ao processar idioma selecionado: %s", e.getMessage());
            throw new SonolucroViewException(erroStr);
        }
    }

    @Override
    public void apagar(ActionEvent event)
    {
    }

    @Override
    public void preparaAlteracao(ActionEvent event)
    {
        UsuarioBean usuario = (UsuarioBean) RequestUtils.getRequest().getAttribute("usuario");
        navegacao.setUsuarioPesquisaEdicao(usuario);

    }

    @Override
    protected void preparaBeanParaAlterar(UsuarioBean usuario) throws SonolucroViewException
    {
        usuario.setIdioma(processaIdiomaSelecionado());
        usuario.setSexo(navegacao.getSexoItemSelecionado());
    }

    @Override
    protected void preparaTela() throws SonolucroViewException
    {
    }

    private void preparaTelaUsuarioPesquisa() throws SonolucroViewException
    {
        listarUsuarios();
    }

    private void preparaTelaUsuarioPesquisaEdicao() throws SonolucroViewException
    {

        Set<String> permissoes = navegacao.getUsuarioPesquisaEdicao().getPermissao();

        navegacao.setSexoItemSelecionado(navegacao.getUsuarioPesquisaEdicao().getSexo());
        navegacao.setIdiomaItemSelecionado(navegacao.getUsuarioPesquisaEdicao().getIdioma().getNumeroIdioma());
        navegacao.setAdiministrador(Boolean.FALSE);

        for (String permissao : permissoes)
        {
            if (permissao.equals(UsuarioBeanImpl.PERMISSAO_ADMINISTRADOR))
                navegacao.setAdiministrador(Boolean.TRUE);
        }

        listarIdiomas();
        listarSexo();
    }

    private void listarUsuarios() throws SonolucroViewException
    {
        try
        {
            List<UsuarioBean> usuarios = (List) controle.listarUsuarios();

            if (usuarios != null && usuarios.size() > 0)
            {
                List<UsuarioBeanNavegacao> usuariosNavegacao = new ArrayList<UsuarioBeanNavegacao>();

                for (UsuarioBean usuario : usuarios)
                {
                    UsuarioBeanNavegacao usuarioBeanNavegacao = new UsuarioBeanNavegacao(usuario);
                    usuarioBeanNavegacao.setPermissaoStr(beanUtil.permissaoStr(usuario));
                    usuariosNavegacao.add(usuarioBeanNavegacao);
                }

                navegacao.setUsuarios(usuariosNavegacao);
            }

        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    private void preparaTelaCadastro() throws SonolucroViewException
    {
        limpaTela();
        navegacao.setUsuarioCadastro(null);
        listarIdiomas();
        listarSexo();
    }

    private void preparaTelaUsuarioEdicao() throws SonolucroViewException
    {
        alterarIdioma();
        UsuarioBean usuario = navegacao.getUsuarioLogado();
        navegacao.setIdiomaItemSelecionado(usuario.getIdioma().getNumeroIdioma());
        navegacao.setSexoItemSelecionado(usuario.getSexo());
        listarIdiomas();
        listarSexo();
    }

    private void listarIdiomas() throws SonolucroViewException
    {
        try
        {
            List<IdiomaBean> idiomas = controle.listarIdiomas();

            if (idiomas != null && idiomas.size() > 0)
            {
                List<SelectItem> idiomasItem = new ArrayList<SelectItem>();

                for (IdiomaBean idioma : idiomas)
                    idiomasItem.add(new SelectItem(idioma.getId(), idioma.getIdioma()));

                navegacao.setIdiomasItem(idiomasItem);
            }
        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    private void listarSexo()
    {
        List<SelectItem> sexosItem = new ArrayList<SelectItem>();

        sexosItem.add(new SelectItem(1, MensagemUtil.getMensagem("usuario_sexo_fem")));
        sexosItem.add(new SelectItem(2, MensagemUtil.getMensagem("usuario_sexo_masc")));

        navegacao.setSexosItem(sexosItem);
    }
}
