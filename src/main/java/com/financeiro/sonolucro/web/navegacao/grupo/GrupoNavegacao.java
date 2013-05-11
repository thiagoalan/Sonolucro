package com.financeiro.sonolucro.web.navegacao.grupo;

import java.util.List;


import com.financeiro.sonolucro.bean.api.GrupoBean;
import com.financeiro.sonolucro.bean.impl.GrupoBeanImpl;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.Conversation;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

/*
 * Recebe e envia informações da view Grupo.
 * 
 * @author Rodrigo Romero
 * @since 10/08/2012
 * @version 1.0
 * 
 */
@Named(value = "grupoNavegacao")
@ViewAccessScoped  
public class GrupoNavegacao implements Serializable
{

    private static final long serialVersionUID = -3449954105214210685L;
    protected static final String BEAN_NAME = "grupoNavegacao";
    
    @Inject
    private Conversation conversation;
    private GrupoBean grupo;
    private List<GrupoBean> grupos;
    private String nomeDoGrupo;
    private Integer statusItemSelecionado;
    private List<SelectItem> statusItens;


    public List<GrupoBean> getGrupos()
    {
        if (grupos == null)
            grupos = new ArrayList<GrupoBean>();

        return grupos;
    }

    public void setGrupos(List<GrupoBean> grupos)
    {
        this.grupos = grupos;
    }

    public GrupoBean getGrupo()
    {
        if (grupo == null)
            this.setGrupo(new GrupoBeanImpl());

        return grupo;
    }

    public void setGrupo(GrupoBean grupo)
    {
        this.grupo = grupo;
    }

    public String getNomeDoGrupo()
    {
        return nomeDoGrupo;
    }

    public void setNomeDoGrupo(String nomeDoGrupo)
    {
        this.nomeDoGrupo = nomeDoGrupo;
    }

    public Integer getStatusItemSelecionado()
    {
        return statusItemSelecionado;
    }

    public void setStatusItemSelecionado(Integer statusItemSelecionado)
    {
        this.statusItemSelecionado = statusItemSelecionado;
    }

    public List<SelectItem> getStatusItens()
    {
        if (statusItens == null)
            setStatusItens(new ArrayList<SelectItem>());

        return statusItens;
    }

    public void setStatusItens(List<SelectItem> statusItens)
    {
        this.statusItens = statusItens;
    }
}
