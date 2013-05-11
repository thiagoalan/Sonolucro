package com.financeiro.sonolucro.web.navegacao.index;

import com.financeiro.sonolucro.bean.api.CartaoBean;
import com.financeiro.sonolucro.bean.api.MovimentacaoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.controle.api.CartaoControle;
import com.financeiro.sonolucro.controle.api.MovimentacaoControle;
import com.financeiro.sonolucro.controle.api.UsuarioControle;
import com.financeiro.sonolucro.util.BeanUtil;
import com.financeiro.sonolucro.util.DataUtil;
import com.financeiro.sonolucro.web.navegacao.movimentacao.GraficoMovimentacaoUtil;
import com.financeiro.sonolucro.util.MensagemUtil;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroViewException;
import com.financeiro.sonolucro.util.ValorUtil;
import com.financeiro.sonolucro.web.navegacao.cartao.CartaoBeanNavegacao;
import com.financeiro.sonolucro.web.navegacao.movimentacao.AgendaMovimentacaoNavegacaoControle;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Classe responsavel pela controle das informações da tela principal
 *
 * @author Rodrigo Romero
 * @since 22/08/2012
 * @version 1.0
 */
@Named(value = "indexNavegacaoControle")
@SessionScoped
public class IndexNavegacaoControle implements Serializable
{

    protected static final String BEAN_NAME = "indexNavegacaoControle";
    protected static final String TELA_PRINCIPAL = "principal";
    private static final long serialVersionUID = -5002244502106507552L;
    
    @Inject
    private MovimentacaoControle movControle;
    @Inject
    private UsuarioControle usuarioControle;
    @Inject
    private CartaoControle cartaoControle;
    @Inject
    private IndexNavegacao navegacao;
    @Inject
    private BeanUtil beanUtil;
    @Inject
    private AgendaMovimentacaoNavegacaoControle agendaControle;
    private List<MovimentacaoBean> movimentacoes;
    private UsuarioBean usuarioLogado;
    private SimpleDateFormat dateFormat = DataUtil.formatoPadrao();

    public void iniciaIndex()
    {
        try
        {
            preencheInformacoesDoUsuario();
            processaSaldo();
            processaUltimasMovimentacoes();
            processaMovimentacoesAVencer();
            iniciaGrafico();
            iniciaCartoes();
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

    private void preencheInformacoesDoUsuario() throws SonolucroViewException
    {
        try
        {
            usuarioLogado = beanUtil.getUsuarioLogado();

            if (movimentacoes == null)
                movimentacoes = movControle.listar(usuarioLogado);

            navegacao.setNomeDoUsuario(usuarioLogado.toString());
            navegacao.setUltimoAcesso(new DataUtil().dataStringUsuarioLogado(
                    usuarioLogado.getDataAcesso(), usuarioLogado.getIdioma().getSigla()));
        }
        catch (SonolucroControleException e)
        {
            e.printStackTrace();
            throw new SonolucroViewException("Erro ao processar informações do Usuário.", e);
        }

    }

    private void iniciaGrafico() throws SonolucroViewException
    {
        try
        {
            Calendar calendar = Calendar.getInstance();

            navegacao.setAnoGrafico(calendar.get(calendar.YEAR));

            processaGraficosAnual();
        }
        catch (SonolucroViewException e)
        {
            String erroStr = String.format("Erro ao iniciar gráfico index: %s", e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
    }

    private void processaGraficosAnual() throws SonolucroViewException
    {
        try
        {
            navegacao.setGraficoLinha(new GraficoMovimentacaoUtil().criaGraficoLinhaMovimentacao(
                    usuarioLogado, movimentacoes, MensagemUtil.getMensagem("movimentacao_saldo"), navegacao.getAnoGrafico()));
        }
        catch (RuntimeException e)
        {
            String erroStr = String.format("Erro ao processar gráfico anual: %s", e.getMessage());
            throw new SonolucroViewException(erroStr);
        }
    }

    private void processaSaldo() throws SonolucroViewException
    {
        try
        {
            processaSaldoPrevisto();
            Date dataAtual = new Date();

            if (movimentacoes.size() > 0)
            {

                for (Integer index = movimentacoes.size() - 1; index >= 0; index--)
                {
                    Date dataMov = movimentacoes.get(index).getDataVencimento();

                    if (dataMov.equals(dataAtual) || dataMov.before(dataAtual))
                    {
                        navegacao.setSaldoAtual(movimentacoes.get(index).getSaldo());
                        break;
                    }
                }
            }
            else
                navegacao.setSaldoAtual(0.00f);

        }
        catch (NullPointerException e)
        {
            String erroStr = String.format("Erro ao processar saldo da index: %s",
                    e.getMessage());
            e.printStackTrace();
            throw new SonolucroViewException(erroStr, e);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.printf("\n%s\nUsuário sem movimentações", e.getMessage());
        }
    }

    private void processaSaldoPrevisto()
    {
        try
        {
            navegacao.setSaldoPrevisto(movimentacoes.get(movimentacoes.size() - 1).getSaldo());
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            navegacao.setSaldoPrevisto(0.00f);
        }
    }

    private void processaUltimasMovimentacoes() throws SonolucroViewException
    {
        try
        {
            List<MovimentacaoBean> movs = movControle.listaPorDataLancamento(usuarioLogado);

            if (movs != null)
            {
                Calendar calendar = Calendar.getInstance();

                calendar.set(calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH) + 7);

                for (Integer i = 0; i < movs.size(); i++)
                {
                    if (navegacao.getUltimasMovimentacoes().size() < 5)
                        navegacao.getUltimasMovimentacoes().add(movs.get(i));
                    else
                        break;

                }
            }
        }
        catch (RuntimeException e)
        {
            String erroStr = String.format("Erro ao processar ultimas movimentações: %s", e.getMessage());
            e.printStackTrace();
            throw new SonolucroViewException(erroStr, e);
        }
        catch (SonolucroControleException e)
        {
            String erroStr = String.format("Erro ao processar ultimas movimentações: %s", e.getMessage());
            e.printStackTrace();
            throw new SonolucroViewException(erroStr, e);
        }
    }

    private void processaMovimentacoesAVencer() throws SonolucroViewException
    {
        Calendar calendar = Calendar.getInstance();

        try
        {
            final Date dataInicial = dateFormat.parse((calendar.get(Calendar.DAY_OF_MONTH) - 1) + "/"
                    + (calendar.get(calendar.MONTH) + 1) + "/" + calendar.get(calendar.YEAR));

            calendar.set(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH) + 7);

            final Date dataFinal = calendar.getTime();
            
            for (MovimentacaoBean mov : movimentacoes)
            {
                if ((mov.equals(dataInicial)
                        || mov.getDataVencimento().after(dataInicial))
                        && (mov.equals(dataInicial)
                        || mov.getDataVencimento().before(dataFinal)))
                {
                    navegacao.getMovimentacoesAVencer().add(mov);

                }
                if (navegacao.getMovimentacoesAVencer().size() == 5)
                    break;
            }
        }
        catch (ParseException e)
        {
            String erroStr = String.format(
                    "Erro ao processar movimentações a vencer: %s", e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            String erroStr = String.format(
                    "Erro ao processar movimentações a vencer: %s", e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
        catch (RuntimeException e)
        {
            String erroStr = String.format(
                    "Erro ao processar movimentações a vencer: %s", e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
    }

    private void iniciaCartoes()
    {
        try
        {
            List<CartaoBean> cartoes = cartaoControle.listar(usuarioLogado, true);
            List<CartaoBeanNavegacao> cartoesDeCredito = new ArrayList<CartaoBeanNavegacao>();

            for (CartaoBean cartao : cartoes)
            {
                if (cartao.getStatus())
                {
                    CartaoBeanNavegacao cartaoDeCredito = new CartaoBeanNavegacao(cartao);
                    cartaoDeCredito.setSaldoPorcento(new ValorUtil().processaPorcentagem(cartao.getLimite(), cartao.getLimite() - cartao.getSaldo()));
                    cartoesDeCredito.add(cartaoDeCredito);
                }
            }

            navegacao.setCartoesDeCredito(cartoesDeCredito);
        }
        catch (RuntimeException e)
        {
            e.getMessage();
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            e.printStackTrace();
        }
    }

    private void limpaTela()
    {
        movimentacoes = null;
        navegacao.setMovimentacoesAVencer(null);
        navegacao.setUltimasMovimentacoes(null);
        navegacao.setGraficoLinha(null);
    }

    public String irParaTelaPrincipal()
    {
        limpaTela();
        return TELA_PRINCIPAL;
    }

    public String getInit()
    {
        iniciaIndex();
        agendaControle.iniciarAgenda();
        return "";
    }
}
