package com.financeiro.sonolucro.web.navegacao.movimentacao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import java.text.ParseException;

import com.financeiro.sonolucro.bean.api.CartaoBean;
import com.financeiro.sonolucro.bean.api.ContaBean;
import com.financeiro.sonolucro.bean.api.MovimentacaoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.bean.impl.ContaBeanImpl;
import com.financeiro.sonolucro.controle.api.CartaoControle;
import com.financeiro.sonolucro.controle.api.ContaControle;
import com.financeiro.sonolucro.controle.api.MovimentacaoControle;
import com.financeiro.sonolucro.util.BeanUtil;
import com.financeiro.sonolucro.util.DataUtil;
import com.financeiro.sonolucro.util.MensagemUtil;
import com.financeiro.sonolucro.util.RequestUtils;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroViewException;
import com.financeiro.sonolucro.util.ValorUtil;
import com.financeiro.sonolucro.web.NavegacaoControle;
import java.io.Serializable;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

/**
 * Classe responsável pelo controle das view que envolvem Movimentação
 * financeira, laçamento de contas.
 *
 * @author Rodrigo Romero
 * @since 22/08/2012
 * @version 1.0
 */
@Named(value = "movimentacaoNavegacaoControle")
@ViewAccessScoped
public class MovimentacaoNavegacaoControle extends NavegacaoControle<MovimentacaoBean>
        implements Serializable
{

    private static final long serialVersionUID = 114662898907416907L;
    protected static final String TELA_MOVIMENTACAO = "movimentacao";
    protected static final String TELA_GRAFICO = "graficoMovimentacao";
    protected static final String BEAN_NAME = "movimentacaoNavegacaoControle";
    protected static final String ID_COMPONENTE_MENSAGEM = "messageCadastroMovimentacao";
    @Inject
    private MovimentacaoControle controle;
    @Inject
    private ContaControle contaControle;
    @Inject
    private CartaoControle cartaoControle;
    @Inject
    private MovimentacaoNavegacao navegacao;
    @Inject
    private BeanUtil beanUtil;
    private List<ContaBean> contas;
    private List<CartaoBean> cartoes;
    private ValorUtil valorUtil;
    private GraficoMovimentacaoUtil graficoUtil;

    public MovimentacaoNavegacaoControle()
    {
        graficoUtil = new GraficoMovimentacaoUtil();
        valorUtil = new ValorUtil();
    }

    @Override
    public void salvar(ActionEvent event)
    {
        try
        {
            MovimentacaoBean mov = navegacao.getMovimentacao();
            preparaBeanParaSalvar(mov);
            controle.salvar(mov);
            limpaTela();

            filtrarMovimentacoes(navegacao.getMovimentacoes());

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_sucesso_salvar_movimentacao"));
        }
        catch (RuntimeException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_erro_salvar_movimentacao"));
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_erro_salvar_movimentacao"));
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_erro_salvar_movimentacao"));
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(ActionEvent event)
    {
        try
        {
            MovimentacaoBean mov = navegacao.getMovimentacao();
            preparaBeanParaAlterar(mov);
            controle.alterar(mov);

            limpaTela();

            filtrarMovimentacoes(navegacao.getMovimentacoes());

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_sucesso_alterar_movimentacao"));
        }
        catch (RuntimeException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_erro_alterar_movimentacao"));
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_erro_alterar_movimentacao"));
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_erro_alterar_movimentacao"));
            e.printStackTrace();
        }

    }

    @Override
    public void apagar(ActionEvent event)
    {
        try
        {
            controle.apagar(navegacao.getMovimentacao());

            limpaTela();

            filtrarMovimentacoes(navegacao.getMovimentacoes());

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_sucesso_apagar_movimentacao"));
        }
        catch (RuntimeException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_erro_apagar_movimentacao"));
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_erro_apagar_movimentacao"));
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_erro_apagar_movimentacao"));
            e.printStackTrace();
        }
    }

    @Override
    public void preparaAlteracao(ActionEvent event)
    {
        try
        {
            MovimentacaoBean mov = (MovimentacaoBean) RequestUtils.getRequest().getAttribute("mov");

            if (mov != null)
            {
                navegacao.setMovimentacao(mov);
                navegacao.setContaItemSelecionado(mov.getConta().getId());
                navegacao.setValorStr(valorUtil.getValoStr(mov.getValor()));

                if (mov.getCartao() != null)
                    navegacao.setCartaoItemSelecionado(mov.getCartao().getId());
                else
                    navegacao.setCartaoItemSelecionado(0l);
            }
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
    }

    public void pesquisarGrafico(ActionEvent event)
    {
        Integer ano = navegacao.getAnoGrafico();

        try
        {
            if (ano != null)
            {
                UsuarioBean usuario = beanUtil.getUsuarioLogado();
                List<MovimentacaoBean> movs = controle.listar(usuario);

                if (navegacao.getTipoDoGrafico().equals(1))
                {
                    navegacao.setGraficoLinha(graficoUtil.criaGraficoLinhaMovimentacao(
                            usuario, movs, MensagemUtil.getMensagem("movimentacao_saldo"), ano));
                    navegacao.setGraficoBarra(null);
                }
                else if (navegacao.getTipoDoGrafico().equals(2))
                {
                    navegacao.setGraficoBarra(graficoUtil.criaGraficoBarraMovimentacao(
                            usuario, movs, MensagemUtil.getMensagem("movimentacao_saldo"), ano));
                    navegacao.setGraficoLinha(null);
                }
            }

        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            e.printStackTrace();
        }

    }

    public void pesquisar(ActionEvent event)
    {
        try
        {
            filtrarMovimentacoes(navegacao.getMovimentacoes());
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void preparaBeanParaAlterar(MovimentacaoBean mov) throws SonolucroViewException
    {
        try
        {
            Long idCartao = navegacao.getCartaoItemSelecionado();

            Float valor = valorUtil.getValorReal(navegacao.getValorStr());
            mov.setValor(valor);

            if (contas == null)
                contas = contaControle.listar(beanUtil.getUsuarioLogado());

            for (ContaBean conta : contas)
            {
                if (conta.getId().equals(navegacao.getContaItemSelecionado()))
                {
                    mov.setConta(conta);
                    break;
                }
            }

            if (mov.getConta().getTipo() != ContaBeanImpl.TIPO_RECEITA
                    && (idCartao != null && idCartao != 0))
            {
                if (cartoes == null)
                    cartoes = cartaoControle.listar(beanUtil.getUsuarioLogado(), Boolean.FALSE);

                for (CartaoBean cartao : cartoes)
                {
                    if (cartao.getId().equals(idCartao))
                    {
                        mov.setCartao(cartao);
                        break;
                    }
                }
            }
            else
                mov.setCartao(null);
        }
        catch (NullPointerException e)
        {
            throw new SonolucroViewException(e);
        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    private void iniciaGrafico() throws SonolucroViewException
    {
        try
        {
            Calendar calendar = Calendar.getInstance();

            Integer ano = calendar.get(calendar.YEAR);

            navegacao.setAnoGrafico(ano);
            navegacao.setTipoDoGrafico(1);

            UsuarioBean usuario = beanUtil.getUsuarioLogado();

            List<MovimentacaoBean> movs = controle.listar(usuario);

            if (navegacao.getTipoDoGrafico().equals(1))
                navegacao.setGraficoLinha(graficoUtil.criaGraficoLinhaMovimentacao(
                        usuario, movs, MensagemUtil.getMensagem("movimentacao_saldo"), ano));


        }
        catch (RuntimeException e)
        {
            String erroStr = String.format("Erro ao iniciar gráfico: %s", e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
        catch (SonolucroControleException e)
        {
            String erroStr = String.format("Erro ao iniciar gráfico: %s", e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
    }

    @Override
    protected void preparaTela() throws SonolucroViewException
    {
        try
        {
            limpaTela();
            UsuarioBean usuario = beanUtil.getUsuarioLogado();
            Calendar calendar = Calendar.getInstance();

            SimpleDateFormat dateFormat = DataUtil.formatoPadrao();

            final String dataInicialStr = "01/" + (calendar.get(calendar.MONTH) + 1) + "/" + calendar.get(calendar.YEAR);
            calendar.setTime(dateFormat.parse(dataInicialStr));

            final Date dataInicial = dateFormat.parse((calendar.get(calendar.DAY_OF_MONTH)) + "/"
                    + (calendar.get(calendar.MONTH) + 1) + "/" + calendar.get(calendar.YEAR));

            calendar.setTime(dateFormat.parse((calendar.get(calendar.DAY_OF_MONTH) - 1) + "/"
                    + (calendar.get(calendar.MONTH) + 2) + "/" + calendar.get(calendar.YEAR)));

            final Date dataFinal = calendar.getTime();

            navegacao.setDataInicial(dataInicial);
            navegacao.setDataFinal(dataFinal);

            filtrarMovimentacoes(navegacao.getMovimentacoes());

            navegacao.getMovimentacao().setSequencia(500);

            iniciaListaDeContas(usuario);
            iniciaListaDeCartoes(usuario);

        }
        catch (RuntimeException e)
        {

            throw new SonolucroViewException(e);
        }
        catch (ParseException e)
        {

            throw new SonolucroViewException(e);
        }

    }

    protected void iniciaListaDeContas(UsuarioBean usuario) throws SonolucroViewException
    {
        try
        {
            this.contas = contaControle.listar(usuario);

            if (contas != null)
            {
                List<SelectItem> contasItem = new ArrayList<SelectItem>();

                for (ContaBean conta : contas)
                {
                    if (conta.getStatus())
                        contasItem.add(new SelectItem(conta.getId(), conta.getNome()));
                }

                navegacao.setContasItem(contasItem);
            }
        }
        catch (SonolucroControleException e)
        {
            String erroStr = String.format("Erro ao iniciar lista de contas: %s", e.getMessage());
            throw new SonolucroViewException(erroStr);
        }
    }

    protected void iniciaListaDeCartoes(UsuarioBean usuario) throws SonolucroViewException
    {
        try
        {
            this.cartoes = cartaoControle.listar(beanUtil.getUsuarioLogado(), false);

            if (cartoes != null)
            {
                List<SelectItem> cartoesItem = new ArrayList<SelectItem>();

                for (CartaoBean cartao : cartoes)
                {
                    if (cartao.getStatus())
                        cartoesItem.add(new SelectItem(cartao.getId(), cartao.toString()));
                }

                navegacao.setCartoesItem(cartoesItem);
            }


        }
        catch (SonolucroControleException e)
        {
            System.out.println(e.getMessage());
            throw new SonolucroViewException(e.getMessage(), e);
        }
    }

    public String irParaTelaMovimentacao()
    {
        try
        {
            preparaTela();
            return TELA_MOVIMENTACAO;
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public String irParaTelaGrafico()
    {
        try
        {
            limpaTela();
            iniciaGrafico();
            return TELA_GRAFICO;
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void preparaBeanParaSalvar(MovimentacaoBean mov) throws SonolucroViewException
    {
        try
        {
            Long contaId = navegacao.getContaItemSelecionado();
            Long cartaoId = navegacao.getCartaoItemSelecionado();
            mov.setDataLancamento(new Date());

            Float valor = valorUtil.getValorReal(navegacao.getValorStr());
            mov.setValor(valor);

            if (contas == null)
                contas = contaControle.listar(beanUtil.getUsuarioLogado());

            for (ContaBean conta : contas)
            {
                if (conta.getId().equals(contaId))
                {
                    mov.setConta(conta);
                    break;
                }
            }

            if (mov.getConta().getTipo() != ContaBeanImpl.TIPO_RECEITA
                    && (cartaoId != null && cartaoId != 0))
            {
                if (cartoes == null)
                    cartoes = cartaoControle.listar(beanUtil.getUsuarioLogado(), Boolean.FALSE);

                for (CartaoBean cartao : cartoes)
                {
                    if (cartao.getId().equals(cartaoId))
                    {
                        mov.setCartao(cartao);
                        break;
                    }
                }
            }


            mov.setId(null);
        }
        catch (NullPointerException e)
        {
            String erroStr = String.format("Erro ao preparar movimentação para salvar: %s",
                    e.getMessage());

            throw new SonolucroViewException(erroStr, e);
        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    private void filtrarMovimentacoes(List<MovimentacaoBean> movs) throws SonolucroViewException
    {
        try
        {
            movs.clear();
            movs.addAll(controle.listarPorData(beanUtil.getUsuarioLogado(),
                    navegacao.getDataInicial(), navegacao.getDataFinal()));
        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
        catch (NullPointerException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    protected void limpaTela()
    {
        navegacao.setMovimentacao(null);
        navegacao.getMovimentacao().setSequencia(500);
        navegacao.getMovimentacoes().clear();
        //navegacao.setContasItem(null);
        navegacao.setContaItemSelecionado(null);
        navegacao.setGraficoLinha(null);
        navegacao.setGraficoBarra(null);
        //navegacao.setCartoesItem(null);
        navegacao.setCartaoItemSelecionado(null);
        navegacao.setValorStr("");
    }

    protected MovimentacaoControle getControle()
    {
        return controle;
    }

    protected ContaControle getContaControle()
    {
        return contaControle;
    }

    protected CartaoControle getCartaoControle()
    {
        return cartaoControle;
    }

    protected MovimentacaoNavegacao getNavegacao()
    {
        return navegacao;
    }

    protected BeanUtil getBeanUtil()
    {
        return beanUtil;
    }
}
