package com.financeiro.sonolucro.web.navegacao.cartao;

import com.financeiro.sonolucro.bean.api.CartaoBean;
import com.financeiro.sonolucro.bean.api.FaturaCartaoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.bean.impl.CartaoBeanImpl;
import com.financeiro.sonolucro.controle.api.CartaoControle;
import com.financeiro.sonolucro.util.BeanUtil;
import com.financeiro.sonolucro.util.MensagemUtil;
import com.financeiro.sonolucro.util.RequestUtils;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroViewException;
import com.financeiro.sonolucro.util.ValorUtil;
import com.financeiro.sonolucro.web.NavegacaoControle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

/**
 *
 * Controle da View cartao. Faz a validação do bean cartao, e interage com as
 * view que disponibilizam informação atraves do CartaoNavegacao
 *
 * @author Rodrigo Romero
 * @since 10/08/2012
 * @version 1.0
 */
@Named(value = "cartaoNavegacaoControle")
@ViewAccessScoped  
public class CartaoNavegacaoControle extends NavegacaoControle<CartaoBean> implements Serializable
{

    private static final long serialVersionUID = -308898548873868325L;
    protected static final String BEAN_NAME = "cartaoNavegacaoControle";
    protected static final String TELA_CARTAO = "cartao";
    protected static final String ID_COMPONENTE_MENSAGEM = "messageCadastroCartao";
    
    @Inject
    private Conversation conversation;
    @Inject
    private CartaoControle controle;
    @Inject
    private CartaoNavegacao navegacao;
    @Inject
    private BeanUtil beanUtil;
    private ValorUtil valorUtil = new ValorUtil();

    @Override
    public void salvar(ActionEvent event)
    {
        try
        {
            CartaoBean cartao = navegacao.getCartao();
            preparaBeanParaSalvar(cartao);
            controle.salvar(cartao);
            preparaTela();

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("cartao_msg_sucesso_salvar_cartao"));
        }
        catch (RuntimeException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("cartao_msg_erro_salvar_cartao"));
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("cartao_msg_erro_salvar_cartao"));
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("cartao_msg_erro_salvar_cartao"));
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(ActionEvent event)
    {
        try
        {
            CartaoBean cartao = new CartaoBeanImpl(navegacao.getCartao());
            preparaBeanParaAlterar(cartao);
            controle.alterar(cartao);
            preparaTela();

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("cartao_msg_sucesso_alterar_cartao"));
        }
        catch (RuntimeException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("cartao_msg_erro_alterar_cartao"));
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("cartao_msg_erro_alterar_cartao"));
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("cartao_msg_erro_alterar_cartao"));
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(ActionEvent event)
    {
        try
        {
            CartaoBean cartao = new CartaoBeanImpl(navegacao.getCartao());
            controle.apagar(cartao);
            preparaTela();

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("cartao_msg_sucesso_apagar_cartao"));
        }
        catch (RuntimeException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("cartao_msg_erro_apagar_cartao"));
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("cartao_msg_erro_apagar_cartao"));
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("cartao_msg_erro_apagar_cartao"));
            e.printStackTrace();
        }
    }

    @Override
    public void preparaAlteracao(ActionEvent event)
    {
        try
        {
            CartaoBean cartao = (CartaoBean) RequestUtils.getRequest().getAttribute("cartao");

            if (cartao != null)
            {

                if (cartao.getStatus())
                    navegacao.setStatusItemSelecionado(1);
                else
                    navegacao.setStatusItemSelecionado(2);

                navegacao.setCartao(cartao);

            }
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
    }

    public void prepararFaturas(ActionEvent event)
    {
        try
        {
            CartaoBean cartao = (CartaoBean) RequestUtils.getRequest().getAttribute("cartao");

            List<FaturaCartaoBean> faturas = (List) cartao.getFaturas();

            for (FaturaCartaoBean fatura : faturas)
                fatura.setValorPagoStr(valorUtil.getValoStr(fatura.getValorPago()));

            navegacao.setCartao(cartao);

        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
    }

    public void alterarFatura(ActionEvent event)
    {
        try
        {

            FaturaCartaoBean fatura = (FaturaCartaoBean) RequestUtils.getRequest().getAttribute("fatura");

            Float valorPago = valorUtil.getValorReal(fatura.getValorPagoStr());
            fatura.setValorPago(valorPago);

            if (fatura.getValorPago() > fatura.getValorTotal())
                fatura.setValorPago(fatura.getValorTotal());

            controle.alterarFatura(fatura);
            
            this.iniciaCartoes();

        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void preparaBeanParaSalvar(CartaoBean cartao)
    {
        if (cartao.getUsuario() == null)
        {
            UsuarioBean usuario = beanUtil.getUsuarioLogado();
            cartao.setUsuario(usuario);
        }

        if (navegacao.getStatusItemSelecionado().equals(1))
            cartao.setStatus(true);
        else
            cartao.setStatus(false);

        cartao.setId(null);
    }

    @Override
    protected void preparaBeanParaAlterar(CartaoBean cartao)
    {
        if (cartao.getUsuario() == null)
        {
            UsuarioBean usuario = beanUtil.getUsuarioLogado();
            cartao.setUsuario(usuario);
        }

        if (navegacao.getStatusItemSelecionado().equals(1))
            cartao.setStatus(true);
        else
            cartao.setStatus(false);
    }

    @Override
    protected void limpaTela()
    {
        navegacao.setCartao(null);
        navegacao.setCartoes(null);
        navegacao.setStatusItemSelecionado(null);
    }

    private void iniciaCartoes() throws SonolucroViewException
    {
        try
        {
            List<CartaoBean> cartoes = controle.listar(beanUtil.getUsuarioLogado(), true);

            if (cartoes != null)
            {
                List<CartaoBeanNavegacao> cartoesBeanNavegacao = new ArrayList<CartaoBeanNavegacao>();

                for (CartaoBean cartao : cartoes)
                {
                    CartaoBeanNavegacao cartaoBeanNavegacao = new CartaoBeanNavegacao(cartao);
                    cartoesBeanNavegacao.add(cartaoBeanNavegacao);
                }

                navegacao.setCartoes(cartoesBeanNavegacao);
            }
        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    public void preparaTela() throws SonolucroViewException
    {
        limpaTela();
        navegacao.getCartao().setDiaFechamentoFatura(20);
        navegacao.getCartao().setDiaVencimento(1);
        iniciaCartoes();
    }

    public String irParaTelaCartao()
    {
        try
        {
            preparaTela();
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
            return null;
        }

        return TELA_CARTAO;
    }
}
