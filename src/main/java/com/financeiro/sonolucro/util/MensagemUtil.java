package com.financeiro.sonolucro.util;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

/**
 * @since 05/10/2012
 * @author Rodrigo Romero
 * @version 1.0
 *
 */
public class MensagemUtil implements Serializable
{

    private static final long serialVersionUID = 7104410815416751775L;
    
    public static final String[] MESES_ABREVIADOS =
    {
        MensagemUtil.MesesDoAno.JANEIRO.MES_ABREVIADO,
        MensagemUtil.MesesDoAno.FEVEREIRO.MES_ABREVIADO,
        MensagemUtil.MesesDoAno.MARCO.MES_ABREVIADO,
        MensagemUtil.MesesDoAno.ABRIL.MES_ABREVIADO,
        MensagemUtil.MesesDoAno.MAIO.MES_ABREVIADO,
        MensagemUtil.MesesDoAno.JUNHO.MES_ABREVIADO,
        MensagemUtil.MesesDoAno.JULHO.MES_ABREVIADO,
        MensagemUtil.MesesDoAno.AGOSTO.MES_ABREVIADO,
        MensagemUtil.MesesDoAno.SETEMBRO.MES_ABREVIADO,
        MensagemUtil.MesesDoAno.OUTUBRO.MES_ABREVIADO,
        MensagemUtil.MesesDoAno.NOVEMBRO.MES_ABREVIADO,
        MensagemUtil.MesesDoAno.DEZEMBRO.MES_ABREVIADO,
    };
    public static final String[] MESES =
    {
        MensagemUtil.MesesDoAno.JANEIRO.MES,
        MensagemUtil.MesesDoAno.FEVEREIRO.MES,
        MensagemUtil.MesesDoAno.MARCO.MES,
        MensagemUtil.MesesDoAno.ABRIL.MES,
        MensagemUtil.MesesDoAno.MAIO.MES,
        MensagemUtil.MesesDoAno.JUNHO.MES,
        MensagemUtil.MesesDoAno.JULHO.MES,
        MensagemUtil.MesesDoAno.AGOSTO.MES,
        MensagemUtil.MesesDoAno.SETEMBRO.MES,
        MensagemUtil.MesesDoAno.OUTUBRO.MES,
        MensagemUtil.MesesDoAno.NOVEMBRO.MES,
        MensagemUtil.MesesDoAno.DEZEMBRO.MES
    };

    public static String getMensagem(String propriedade)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");

        return bundle.getString(propriedade);
    }

    public static String getMensagem(String propriedade, Object... parametros)
    {
        String mensagem = getMensagem(propriedade);
        MessageFormat formatter = new MessageFormat(mensagem);

        return formatter.format(parametros);
    }

    public static void mensagemOperacaoSucesso(String idComponenteMensagem, String mensagem)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(idComponenteMensagem, new FacesMessage(mensagem));
    }

    public static void mensagemOperacaoErro(String idComponenteMensagem, String mensagem)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(idComponenteMensagem, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null));
    }

    public static void mensagemObservacaoOperacao(String idComponenteMensagem, String mensagem)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(idComponenteMensagem, new FacesMessage(mensagem));
    }

    public enum MesesDoAno
    {

        JANEIRO(1, getMensagem("mes_janeiro"), getMensagem("mes_abreviado_janeiro")),
        FEVEREIRO(2, getMensagem("mes_fevereiro"), getMensagem("mes_abreviado_fevereiro")),
        MARCO(3, getMensagem("mes_marco"), getMensagem("mes_abreviado_marco")),
        ABRIL(4, getMensagem("mes_abril"), getMensagem("mes_abreviado_abril")),
        MAIO(5, getMensagem("mes_maio"), getMensagem("mes_abreviado_maio")),
        JUNHO(6, getMensagem("mes_junho"), getMensagem("mes_abreviado_junho")),
        JULHO(7, getMensagem("mes_julho"), getMensagem("mes_abreviado_julho")),
        AGOSTO(8, getMensagem("mes_agosto"), getMensagem("mes_abreviado_agosto")),
        SETEMBRO(9, getMensagem("mes_setembro"), getMensagem("mes_abreviado_setembro")),
        OUTUBRO(10, getMensagem("mes_outubro"), getMensagem("mes_abreviado_outubro")),
        NOVEMBRO(11, getMensagem("mes_novembro"), getMensagem("mes_abreviado_novembro")),
        DEZEMBRO(12, getMensagem("mes_dezembro"), getMensagem("mes_abreviado_dezembro"));
        public final int NUMERO_MES;
        public final String MES;
        public final String MES_ABREVIADO;

        private MesesDoAno(Integer numeroMes, String mes, String mesAbreviado)
        {
            NUMERO_MES = numeroMes;
            MES = mes;
            MES_ABREVIADO = mesAbreviado;
        }
    }
}
