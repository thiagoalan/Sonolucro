package com.financeiro.sonolucro.web.navegacao.movimentacao;

import com.financeiro.sonolucro.bean.api.MovimentacaoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.util.MensagemUtil;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroViewException;
import com.financeiro.sonolucro.util.ValorUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Rodrigo Romero
 * @since 09/04/2013
 * @version 1.0
 */
@Named(value = "agendaMovimentacaoNavegacaoControle")
@ViewAccessScoped  
public class AgendaMovimentacaoNavegacaoControle extends MovimentacaoNavegacaoControle
{

    private static final long serialVersionUID = -7742855621832306196L;
    protected static final String BEAN_NAME = "agendaMovimentacaoNavegacaoControle";
    protected static final String ID_COMPONENTE_MENSAGEM = "messagePrincipal";
    
    private ScheduleEvent eventoSelecionado = null;
    private ValorUtil valorUtil = new ValorUtil();

    @Override
    public void salvar(ActionEvent event)
    {
        try
        {
            MovimentacaoBean mov = getNavegacao().getMovimentacao();
            preparaBeanParaSalvar(mov);
            getControle().salvar(mov);

            ScheduleModel model = getNavegacao().getEventModel();
            model.addEvent(new DefaultScheduleEvent(mov.getConta().getNome(),
                    mov.getDataVencimento(), mov.getDataVencimento(), mov));

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_sucesso_salvar_movimentacao"));

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
        catch (RuntimeException e)
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
            MovimentacaoBean mov = getNavegacao().getMovimentacao();
            preparaBeanParaAlterar(mov);
            getControle().alterar(mov);

            if (eventoSelecionado != null)
            {
                getNavegacao().getEventModel().deleteEvent(eventoSelecionado);
                getNavegacao().getEventModel().addEvent(new DefaultScheduleEvent(mov.getConta().getNome(),
                    mov.getDataVencimento(), mov.getDataVencimento(), mov));
                
            }

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_sucesso_alterar_movimentacao"));

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
        catch (RuntimeException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_erro_alterar_movimentacao"));
            e.printStackTrace();
        }
    }

    public void moverMovimentacao(ScheduleEntryMoveEvent event)
    {
        try
        {
            Integer dia = event.getDayDelta();

            MovimentacaoBean mov = (MovimentacaoBean) event.getScheduleEvent().getData();
            Date dataVencimento = mov.getDataVencimento();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataVencimento);
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) - dia);
            mov.setDataVencimento(calendar.getTime());
            getNavegacao().setMovimentacao(mov);
            getControle().alterar(mov);

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_sucesso_alterar_movimentacao"));
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_erro_alterar_movimentacao"));
            e.printStackTrace();
        }
        catch (RuntimeException e)
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
            MovimentacaoBean mov = getNavegacao().getMovimentacao();
            getControle().apagar(mov);

            if (eventoSelecionado != null)
                getNavegacao().getEventModel().deleteEvent(eventoSelecionado);

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_sucesso_apagar_movimentacao"));
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_erro_apagar_movimentacao"));
            e.printStackTrace();
        }
        catch (RuntimeException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("movimentacao_erro_apagar_movimentacao"));
            e.printStackTrace();
        }
    }

    public void iniciarAgenda()
    {
        try
        {
            ScheduleModel model = new DefaultScheduleModel();

            listarAgenda(model);

            getNavegacao().setEventModel(model);

            UsuarioBean usuario = getBeanUtil().getUsuarioLogado();
            iniciaListaDeContas(usuario);
            iniciaListaDeCartoes(usuario);

        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
        }
    }

    private void listarAgenda(ScheduleModel model) throws SonolucroViewException
    {
        try
        {
            List<MovimentacaoBean> movimentacoes = getControle().listar(getBeanUtil().getUsuarioLogado());

            for (MovimentacaoBean mov : movimentacoes)
            {
                ScheduleEvent event = new DefaultScheduleEvent(mov.getConta().getNome(), mov.getDataVencimento(), mov.getDataVencimento(), mov);
                model.addEvent(event);
            }
        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    public void prepararCadastroAgenda(DateSelectEvent event)
    {
        try
        {
            limpaTela();
            getNavegacao().getMovimentacao().setDataVencimento(event.getDate());
            getNavegacao().getMovimentacao().setSequencia(500);
            getNavegacao().setAlteracao(Boolean.FALSE);
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }

    }

    public void prepararAlteracaoMovimentacao(ScheduleEntrySelectEvent event)
    {
        try
        {
            limpaTela();
            eventoSelecionado = event.getScheduleEvent();
            MovimentacaoBean mov = (MovimentacaoBean) eventoSelecionado.getData();
            getNavegacao().setMovimentacao(mov);
            getNavegacao().setContaItemSelecionado(mov.getConta().getId());
            
            if (mov.getCartao() != null)
                getNavegacao().setCartaoItemSelecionado(mov.getCartao().getId());
            
            getNavegacao().setValorStr(valorUtil.getValoStr(mov.getValor()));
            getNavegacao().setAlteracao(Boolean.TRUE);
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void limpaTela()
    {
        getNavegacao().setMovimentacao(null);
        getNavegacao().setCartaoItemSelecionado(null);
        getNavegacao().setContaItemSelecionado(null);
        getNavegacao().setValorStr(null);
        eventoSelecionado = null;
    }

}
