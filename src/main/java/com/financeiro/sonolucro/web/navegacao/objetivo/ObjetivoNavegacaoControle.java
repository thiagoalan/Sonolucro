package com.financeiro.sonolucro.web.navegacao.objetivo;

import com.financeiro.sonolucro.bean.api.ObjetivoBean;
import com.financeiro.sonolucro.bean.api.ObjetivoValorGuardadoBean;
import com.financeiro.sonolucro.bean.impl.ObjetivoBeanImpl;
import com.financeiro.sonolucro.bean.impl.ObjetivoValorGuardadoBeanImpl;
import com.financeiro.sonolucro.controle.api.ObjetivoControle;
import com.financeiro.sonolucro.util.BeanUtil;
import com.financeiro.sonolucro.util.MensagemUtil;
import com.financeiro.sonolucro.util.RequestUtils;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroViewException;
import com.financeiro.sonolucro.util.ValorUtil;
import com.financeiro.sonolucro.web.NavegacaoControle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

/**
 *
 * @author Rodrigo Romero
 * @since 27/12/2012
 * @version 1.0
 *
 */
@Named(value = "objetivoNavegacaoControle")
@ViewAccessScoped  
public class ObjetivoNavegacaoControle extends NavegacaoControle<ObjetivoBean> implements Serializable
{
    protected static final String TELA_OBJETIVO = "objetivo";
    protected static final String TELA_EDICAO = "objetivoEdicao";
    protected static final String BEAN_NAME = "objetivoNavegacaoControle";
    protected static final String ID_COMPONENTE_MENSAGEM = "messageCadastroObjetivo";
    private static final long serialVersionUID = -8235885923800271950L;
    
    @Inject
    private ObjetivoNavegacao navegacao;
    @Inject
    private ObjetivoControle controle;
    @Inject
    private Conversation conversation;
    @Inject
    private BeanUtil beanUtil;
    private ObjetivoNavegacaoUtil objetivoUtil;
    private ValorUtil valorUtil;

    public ObjetivoNavegacaoControle()
    {
        valorUtil = new ValorUtil();
        objetivoUtil = new ObjetivoNavegacaoUtil();
    }

    @Override
    public void salvar(ActionEvent event)
    {
        try
        {
            ObjetivoBean objetivo = new ObjetivoBeanImpl(navegacao.getObjetivo());

            if (!validaDataPrevista(objetivo.getDataInicialPrevista(), objetivo.getDataFinalPrevista()))
            {
                MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, MensagemUtil.getMensagem("objetivo_msg_erro_data"));
                return;
            }

            preparaBeanParaSalvar(objetivo);
            controle.salvar(objetivo);

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM, MensagemUtil.getMensagem("objetivo_msg_sucesso_salvar"));

            preparaTela();
        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, MensagemUtil.getMensagem("objetivo_msg_erro_salvar"));
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, MensagemUtil.getMensagem("objetivo_msg_erro_salvar"));
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(ActionEvent event)
    {
        try
        {
            ObjetivoBean objetivo = new ObjetivoBeanImpl(navegacao.getObjetivo());

            if (!validaDataPrevista(objetivo.getDataInicialPrevista(), objetivo.getDataFinalPrevista()))
            {
                MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, MensagemUtil.getMensagem("objetivo_msg_erro_data"));
                return;
            }

            preparaBeanParaAlterar(objetivo);
            controle.alterar(objetivo);
            atualizarTelaEdicao(new ObjetivoBeanNavegacao(objetivo));

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM, MensagemUtil.getMensagem("objetivo_msg_sucesso_alterar"));

        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, MensagemUtil.getMensagem("objetivo_msg_erro_alterar"));
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, MensagemUtil.getMensagem("objetivo_msg_erro_alterar"));
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(ActionEvent event)
    {
        try
        {
            ObjetivoBean objetivo = new ObjetivoBeanImpl(navegacao.getObjetivo());
            controle.apagar(objetivo);

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM, MensagemUtil.getMensagem("objetivo_msg_sucesso_apagar"));

            preparaTela();
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, MensagemUtil.getMensagem("objetivo_msg_erro_apagar"));
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, MensagemUtil.getMensagem("objetivo_msg_erro_apagar"));
            e.printStackTrace();
        }
    }

    @Override
    public void preparaAlteracao(ActionEvent event)
    {
        try
        {
            ObjetivoBeanNavegacao objetivo = (ObjetivoBeanNavegacao) RequestUtils.getRequest().getAttribute("objetivo");

            iniciaUltimoValorGuardado(objetivo);

            navegacao.setObjetivo(objetivo);

            navegacao.setValorTotalStr(valorUtil.getValoStr(objetivo.getValorTotal()));

            objetivoUtil.processaStatusObjetivo(objetivo);
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

    private void atualizarTelaEdicao(ObjetivoBeanNavegacao objetivo) throws SonolucroViewException
    {
        try
        {
            iniciaUltimoValorGuardado(objetivo);

            navegacao.setObjetivo(objetivo);

            objetivoUtil.processaStatusObjetivo(objetivo);
        }
        catch (RuntimeException e)
        {
            throw new SonolucroViewException(e);
        }

    }

    @Override
    protected void limpaTela()
    {
        navegacao.setObjetivo(null);
        navegacao.setValorTotalStr("");
        navegacao.setObjetivos(null);
        navegacao.setUltimoValorGuardado(null);
        navegacao.setValorGuardadoStr("");

    }

    @Override
    protected void preparaBeanParaSalvar(ObjetivoBean objetivo) throws SonolucroViewException
    {
        try
        {
            objetivo.setValorTotal(valorUtil.getValorReal(navegacao.getValorTotalStr()));
            objetivo.setStatus(Boolean.TRUE);
            objetivo.setUsuario(beanUtil.getUsuarioLogado());
        }
        catch (RuntimeException e)
        {
            throw new SonolucroViewException(e);
        }

    }

    @Override
    protected void preparaBeanParaAlterar(ObjetivoBean objetivo) throws SonolucroViewException
    {
        try
        {
            objetivo.setValorTotal(valorUtil.getValorReal(navegacao.getValorTotalStr()));
        }
        catch (RuntimeException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    @Override
    protected void preparaTela() throws SonolucroViewException
    {
        limpaTela();
        iniciarObjetivos();
    }

    public String irParaTelaObjetivo()
    {
        try
        {
            preparaTela();
            return TELA_OBJETIVO;
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public String irParaTelaEdicao()
    {
        try
        {
            preparaAlteracao(null);
            preparaTelaValores();

            return TELA_EDICAO;
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void adicionarValor(ActionEvent event)
    {
        try
        {
            Float valor = valorUtil.getValorReal(navegacao.getValorGuardadoStr());

            ObjetivoValorGuardadoBean valorGuardado = navegacao.getValorGuardado();

            if (navegacao.getObjetivo().getValoresGuardados().contains(valorGuardado))
            {
                valorGuardado.setValor(valor);
                valorGuardado.setValorStr(navegacao.getValorGuardadoStr());
            }
            else
            {
                valorGuardado.setValor(valor);
                valorGuardado.setDataValor(new Date());
                valorGuardado.setValorStr(valorUtil.getValoStr(valor));
                navegacao.setUltimoValorGuardado(valorGuardado);
                navegacao.getObjetivo().getValoresGuardados().add(valorGuardado);

            }

            objetivoUtil.processaStatusObjetivo(navegacao.getObjetivo());

            preparaTelaValores();
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

    public void apagarValor(ActionEvent event)
    {
        ObjetivoValorGuardadoBean valorGuardado = (ObjetivoValorGuardadoBean) RequestUtils.getRequest().getAttribute("valor");
        ObjetivoBeanNavegacao objetivo = navegacao.getObjetivo();
        objetivo.getValoresGuardados().remove(valorGuardado);
        preparaTelaValores();
    }

    private void preparaTelaValores()
    {
        navegacao.setValorGuardado(new ObjetivoValorGuardadoBeanImpl());
        navegacao.setValorGuardadoStr("");

        listarValoresGuardados(navegacao.getObjetivo());

        iniciaUltimoValorGuardado(navegacao.getObjetivo());

    }

    private void listarValoresGuardados(ObjetivoBean objetivo)
    {
        for (ObjetivoValorGuardadoBean valor : objetivo.getValoresGuardados())
            valor.setValorStr(valorUtil.getValoStr(valor.getValor()));
    }

    public void prepararAdicaoValores(ActionEvent event)
    {
        navegacao.setValorGuardadoStr("");
        navegacao.setValorGuardado(new ObjetivoValorGuardadoBeanImpl());
        navegacao.getValorGuardado().setDataValor(new Date());
    }

    public void prepararAlteracaoValor(ActionEvent event)
    {
        try
        {
            ObjetivoValorGuardadoBean valorGuardado = (ObjetivoValorGuardadoBean) RequestUtils.getRequest().getAttribute("valor");

            String valorStr = valorUtil.getValoStr(valorGuardado.getValor());

            navegacao.setValorGuardado(valorGuardado);

            navegacao.setValorGuardadoStr(valorStr);
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
    }

    private void iniciaUltimoValorGuardado(ObjetivoBean objetivo)
    {
        List<ObjetivoValorGuardadoBean> valores = new ArrayList<ObjetivoValorGuardadoBean>(objetivo.getValoresGuardados());
        Integer size = valores.size();

        if (valores.size() > 0)
        {
            ObjetivoValorGuardadoBean ultimoValorGuardado = valores.get(size - 1);
            navegacao.setUltimoValorGuardado(ultimoValorGuardado);
        }
        else
            navegacao.setUltimoValorGuardado(new ObjetivoValorGuardadoBeanImpl());
    }

    private Boolean validaDataPrevista(Date dataInicial, Date dataFinal)
    {
        if (dataFinal != null)
        {
            if (dataInicial.before(dataFinal))
                return true;
            else
                return false;
        }

        return true;
    }

    private void iniciarObjetivos() throws SonolucroViewException
    {
        try
        {
            List<ObjetivoBean> objetivos = controle.listar(beanUtil.getUsuarioLogado());

            if (objetivos != null)
            {
                List<ObjetivoBeanNavegacao> objetivosNavegacao = new ArrayList<ObjetivoBeanNavegacao>();

                for (ObjetivoBean objetivo : objetivos)
                {
                    ObjetivoBeanNavegacao objetivoNavegacao = new ObjetivoBeanNavegacao(objetivo);
                    objetivoUtil.processaProgressoObjetivo(objetivoNavegacao);

                    if (objetivoNavegacao.getStatus())
                        objetivoNavegacao.setStatusStr(MensagemUtil.getMensagem("objetivo_status_true"));
                    else
                        objetivoNavegacao.setStatusStr(MensagemUtil.getMensagem("objetivo_status_false"));

                    objetivosNavegacao.add(objetivoNavegacao);
                }

                navegacao.setObjetivos(objetivosNavegacao);
            }

        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
    }
}
