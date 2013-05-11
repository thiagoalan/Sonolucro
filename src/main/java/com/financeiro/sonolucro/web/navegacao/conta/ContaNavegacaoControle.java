package com.financeiro.sonolucro.web.navegacao.conta;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.financeiro.sonolucro.bean.api.ContaBean;
import com.financeiro.sonolucro.bean.api.GrupoBean;
import com.financeiro.sonolucro.bean.api.MovimentacaoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.bean.impl.ContaBeanImpl;
import com.financeiro.sonolucro.controle.api.ContaControle;
import com.financeiro.sonolucro.controle.api.GrupoControle;
import com.financeiro.sonolucro.controle.api.MovimentacaoControle;
import com.financeiro.sonolucro.util.BeanUtil;
import com.financeiro.sonolucro.util.DataUtil;
import com.financeiro.sonolucro.util.MensagemUtil;
import com.financeiro.sonolucro.util.RequestUtils;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroViewException;
import com.financeiro.sonolucro.web.NavegacaoControle;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.Conversation;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

/*
 * Controle da View conta. 
 * Faz a validação do bean conta, e interage com as view que disponibilizam
 * informação atraves do ContaNavegacao
 * 
 * @author Rodrigo
 * @version 1.0.0
 * @since 10/08/2012
 */
@Named(value = "contaNavegacaoControle")
@ViewAccessScoped  
public class ContaNavegacaoControle extends NavegacaoControle<ContaBean> implements Serializable
{

    private static final long serialVersionUID = 3640786969608375551L;
    protected static final String BEAN_NAME = "contaNavegacaoControle";
    protected static final String ID_COMPONENTE_MENSAGEM = "messageCadastroConta";
    protected static final String TELA_CONTA = "conta";
    protected static final String TELA_GRAFICO = "graficoConta";
    
    @Inject
    private ContaNavegacao navegacao;
    @Inject
    private ContaControle contaControle;
    @Inject
    private MovimentacaoControle movControle;
    @Inject
    private GrupoControle grupoControle;
    @Inject
    private BeanUtil beanUtil;
    private List<GrupoBean> grupos;
    private List<ContaBean> contas;
    private GraficoContaUtil graficoUtil;

    public ContaNavegacaoControle()
    {
        graficoUtil = new GraficoContaUtil(); 
    }
    
    @Override
    public void salvar(ActionEvent event)
    {
        try
        {
            ContaBean conta = navegacao.getConta();
            preparaBeanParaSalvar(conta);
            contaControle.salvar(conta);
            preparaTela();

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("conta_msg_sucesso_salvar_conta"));
        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("conta_msg_erro_salvar_conta"));
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("conta_msg_erro_salvar_conta"));
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(ActionEvent event)
    {
        try
        {
            ContaBean conta = new ContaBeanImpl(navegacao.getConta());
            preparaBeanParaAlterar(conta);
            contaControle.alterar(conta);
            preparaTela();

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("conta_msg_sucesso_alterar_conta"));

        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("conta_msg_erro_alterar_conta"));
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("conta_msg_erro_alterar_conta"));
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(ActionEvent event)
    {
        try
        {
            ContaBean conta = new ContaBeanImpl(navegacao.getConta());
            contaControle.apagar(conta);
            preparaTela();

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("conta_msg_sucesso_apagada_conta"));

        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("conta_msg_erro_apagar_conta"));
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("conta_msg_erro_apagar_conta"));
            e.printStackTrace();
        }
    }

    public void pesquisarGrafico(ActionEvent event)
    {

        try
        {
            List<MovimentacaoBean> movs = new ArrayList<MovimentacaoBean>();

            Float[] saldos = processaSaldoDaContaPorTipoEData(movs, navegacao.getDataInicialGrafico(),
                    navegacao.getDataFinalGrafico());

            navegacao.setSaldoDespesa(saldos[0]);
            navegacao.setSaldoReceita(saldos[1]);

            navegacao.setGrafico(graficoUtil.criaGraficoDePizza(
                    saldos[0], saldos[1]));

            contas = contaControle.listar(beanUtil.getUsuarioLogado()); 
            
            navegacao.setGraficoDespesa(graficoUtil.criaGraficoDePizza(contas, movs, ContaBeanImpl.TIPO_DESPESA));
            navegacao.setGraficoReceita(graficoUtil.criaGraficoDePizza(contas, movs, ContaBeanImpl.TIPO_RECEITA));
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
        catch(SonolucroControleException e)
        {
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void preparaAlteracao(ActionEvent action)
    {
        try
        {
            ContaBean conta = (ContaBean) RequestUtils.getRequest().getAttribute("conta");

            if (conta != null)
            {
                if (conta.getStatus())
                    navegacao.setStatusItemSelecionado(1);
                else
                    navegacao.setStatusItemSelecionado(2);


                navegacao.setConta(conta);
                navegacao.setGrupoItemSelecionado(conta.getGrupo().getId());

            }
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
    }

    public String irParaTelaConta()
    {
        try
        {
            iniciaGrupos();
            preparaTela();
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
            return null;
        }

        return TELA_CONTA;
    }

    public String irParaTelaGraficoConta()
    {
        try
        {
            limpaTela();
            iniciaGraficos();
            iniciaContas();
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
            return null;
        }
        return TELA_GRAFICO;
    }

    private void iniciaGraficos() throws SonolucroViewException
    {
        try
        {
            Calendar data = Calendar.getInstance();

            UsuarioBean usuario = beanUtil.getUsuarioLogado();
             
            iniciaContas();
            List<MovimentacaoBean> movs = new ArrayList<MovimentacaoBean>();

            final String dataInicialStr = "01/" + (data.get(data.MONTH) + 1) + "/" + data.get(data.YEAR);
            final Date dataInicial = new DataUtil().formatoUsuarioLogado(usuario.getIdioma().getSigla()).parse(dataInicialStr);

            data.set(data.get(Calendar.YEAR), data.get(Calendar.MONTH) + 1,
                    (data.get(Calendar.DAY_OF_MONTH) - data.get(Calendar.DAY_OF_MONTH)));

            final Date dataFinal = data.getTime();

            navegacao.setDataInicialGrafico(dataInicial);
            navegacao.setDataFinalGrafico(dataFinal);

            Float[] saldos = processaSaldoDaContaPorTipoEData(movs, navegacao.getDataInicialGrafico(),
                    navegacao.getDataFinalGrafico());

            navegacao.setSaldoDespesa(saldos[0]);
            navegacao.setSaldoReceita(saldos[1]);

            navegacao.setGrafico(graficoUtil.criaGraficoDePizza(saldos[0],
                    saldos[1]));

            navegacao.setGraficoDespesa(graficoUtil.criaGraficoDePizza(contas, movs, ContaBeanImpl.TIPO_DESPESA));
            navegacao.setGraficoReceita(graficoUtil.criaGraficoDePizza(contas, movs, ContaBeanImpl.TIPO_RECEITA));
        }
        catch (ParseException e)
        {
            String erroStr = String.format("Erro ao iniciar Gráfico: %s", e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
        catch (RuntimeException e)
        {
            String erroStr = String.format("Erro ao iniciar Gráfico: %s", e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
        catch (SonolucroViewException e)
        {
            String erroStr = String.format("Erro ao iniciar Gráfico: %s", e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
    }

    private Float[] processaSaldoDaContaPorTipoEData(List<MovimentacaoBean> movsPorData, Date dataInicial,
                                                     Date dataFinal) throws SonolucroViewException
    {

        try
        {
            List<MovimentacaoBean> movs = movControle.listar(beanUtil.getUsuarioLogado());

            Float saldoDespesa = 0f;
            Float saldoReceita = 0f;

            for (MovimentacaoBean mov : movs)
            {
                if ((mov.getDataVencimento().after(dataInicial) || mov.getDataVencimento().equals(dataInicial))
                        && (mov.getDataVencimento().before(dataFinal) || mov.getDataVencimento().equals(dataFinal)))
                {
                    movsPorData.add(mov);

                    if (mov.getConta().getTipo().equals(1))
                        saldoDespesa += mov.getValor();
                    else
                        saldoReceita += mov.getValor();
                }
            }

            Float[] saldos =
            {
                saldoDespesa, saldoReceita
            };

            return saldos;
        }
        catch (RuntimeException e)
        {
            String erroStr = String.format(
                    "Erro ao processar saldo da conta por tipo e data por: %s",
                    e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
        catch (SonolucroControleException e)
        {
            String erroStr = String.format(
                    "Erro ao processar saldo da conta por tipo e data por: %s",
                    e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
    }

    @Override
    protected void limpaTela()
    {
        navegacao.setConta(null);
        navegacao.setGrupoItemSelecionado(null);
        navegacao.setNomeDaConta(null);
        navegacao.setGrafico(null);
        navegacao.setDataFinalGrafico(null);
        navegacao.setDataInicialGrafico(null);
        navegacao.setStatusItemSelecionado(null);
        navegacao.setTipoItemSelecionado(null);
        navegacao.setGraficoDespesa(null);
        navegacao.setGraficoReceita(null);

    }

    @Override
    protected void preparaBeanParaSalvar(ContaBean conta) throws SonolucroViewException
    {
        try
        {
            conta.setId(null);

            if (navegacao.getStatusItemSelecionado().equals(1))
                conta.setStatus(true);
            else
                conta.setStatus(false);


            if (navegacao.getGrupoItemSelecionado() != null && !navegacao.getGrupoItemSelecionado().equals(0))
            {
                if(grupos == null)
                  grupos = grupoControle.listar(beanUtil.getUsuarioLogado()); 
                
                for (GrupoBean grupo : grupos)
                {
                    if (grupo.getId().equals(navegacao.getGrupoItemSelecionado()))
                    {
                        conta.setGrupo(grupo);
                        break;
                    }
                }
            }
            else
                throw new NullPointerException();
        }
        catch (NullPointerException e)
        {
            String erroStr = String.format(
                    "Erro ao preparar conta para salvar: %s",
                    e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
        catch (RuntimeException e)
        {
            String erroStr = String.format(
                    "Erro ao preparar conta para salvar: %s",
                    e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
        catch(SonolucroControleException e)
        {
            throw new SonolucroViewException(e); 
        }
    }

    private void iniciaGrupos() throws SonolucroViewException
    {
        try
        {
            grupos = grupoControle.listar(beanUtil.getUsuarioLogado());

            if (grupos != null)
            {
                List<SelectItem> gruposItem = new ArrayList<SelectItem>();

                for (GrupoBean grupo : grupos)
                {
                    if (grupo.getStatus())
                        gruposItem.add(new SelectItem(grupo.getId(),
                                grupo.getNome()));
                }

                navegacao.setGruposItem(gruposItem);
            }
        }
        catch (NullPointerException e)
        {
            String erroStr = String.format(
                    "Erro iniciar grupos item: %s",
                    e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
        catch (RuntimeException e)
        {
            String erroStr = String.format(
                    "Erro ao iniciar grupos item: %s",
                    e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
        catch (SonolucroControleException e)
        {
            String erroStr = String.format(
                    "Erro ao iniciar grupos item: %s",
                    e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
    }

    @Override
    protected void preparaBeanParaAlterar(ContaBean conta) throws SonolucroViewException
    {
        try
        {
            if (navegacao.getStatusItemSelecionado().equals(1))
                conta.setStatus(true);
            else
                conta.setStatus(false);

            if (grupos == null)
               grupos = grupoControle.listar(beanUtil.getUsuarioLogado());

            for (GrupoBean grupo : grupos)
            {
                if (grupo.getId().equals(navegacao.getGrupoItemSelecionado()))
                {
                    conta.setGrupo(grupo);
                    break;
                }
            }
        }
        catch (SonolucroControleException e)
        {
            String erroStr = String.format(
                    "Erro ao preparar conta para alterar: %s",
                    e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
        catch (RuntimeException e)
        {
            String erroStr = String.format(
                    "Erro ao preparar conta para alterar: %s",
                    e.getMessage());
            throw new SonolucroViewException(erroStr, e);
        }
    }

    @Override
    protected void preparaTela() throws SonolucroViewException
    {
        limpaTela();
        iniciaContas();
        iniciaGrupos();
        iniciarTipos();
        iniciarStatus();
    }

    private void iniciaContas() throws SonolucroViewException
    {
        try
        {
            contas = contaControle.listar(beanUtil.getUsuarioLogado());

            if (contas != null)
            {
                navegacao.setContas(new ArrayList<ContaBeanNavegacao>());

                for (ContaBean contaBean : contas)
                {
                    ContaBeanNavegacao conta = new ContaBeanNavegacao(contaBean);
                    conta.setTipoStr(montarTipoStr(conta.getTipo()));
                    navegacao.getContas().add(conta);
                }

            }
        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    private void iniciarTipos()
    {
        List<SelectItem> tiposItem = new ArrayList<SelectItem>();

        tiposItem.add(new SelectItem(null, ""));
        tiposItem.add(new SelectItem(1, MensagemUtil.getMensagem("conta_tipo_despesa")));
        tiposItem.add(new SelectItem(2, MensagemUtil.getMensagem("conta_tipo_receita")));

        navegacao.setTiposItem(tiposItem);

    }

    private void iniciarStatus()
    {
        List<SelectItem> statusItens = new ArrayList<SelectItem>();

        statusItens.add(new SelectItem(1, MensagemUtil.getMensagem("conta_status_ativado")));
        statusItens.add(new SelectItem(2, MensagemUtil.getMensagem("conta_status_desativado")));

        navegacao.setStatusItens(statusItens);
    }

    private String montarTipoStr(Integer tipo)
    {
        try
        {
            if (tipo.intValue() == ContaBeanImpl.TIPO_RECEITA.intValue())
                return MensagemUtil.getMensagem("conta_tipo_receita");
            else
                return MensagemUtil.getMensagem("conta_tipo_despesa");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
}
