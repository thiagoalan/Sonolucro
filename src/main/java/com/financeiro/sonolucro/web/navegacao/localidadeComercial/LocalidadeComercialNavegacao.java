
package com.financeiro.sonolucro.web.navegacao.localidadeComercial;

import com.financeiro.sonolucro.bean.api.EmpresaBean;
import com.financeiro.sonolucro.bean.api.LocalidadeComercialBean;
import com.financeiro.sonolucro.bean.api.PessoaBean;
import com.financeiro.sonolucro.bean.impl.EmpresaBeanImpl;
import com.financeiro.sonolucro.bean.impl.LocalidadeComercialBeanImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

/**
 * @author rodrigo
 * @since 30/12/2012
 * @version 1.0
 */
@Named(value = "localidadeComercialNavegacao")
@ViewAccessScoped  
public class LocalidadeComercialNavegacao implements Serializable
{
    protected static final String BEAN_NAME = "localidadeComercialNavegacao"; 
    
    @Inject
    private Conversation conversation; 
    private LocalidadeComercialBean localidadeComercial;
    private Long paisItemSelecionado; 
    private Long estadoItemSelecionado; 
    private Long cidadeItemSelecionado; 
    private List<SelectItem> paisesItem; 
    private List<SelectItem> estadosItem; 
    private List<SelectItem> cidadesItem; 
    private List<EmpresaBean> empresas; 
    private List<PessoaBean> responsaveis; 
    private EmpresaBean empresaBeanSelecionado; 
    private String empresaStr; 
    private Boolean empresaSelecionada = false;
    private PessoaBean[] responsaveisSelecionados;
    private String tela;
    private List<LocalidadeComercialBean> localidadesComerciais; 

    public LocalidadeComercialBean getLocalidadeComercial()
    {
        if(localidadeComercial == null)
            setLocalidadeComercial(new LocalidadeComercialBeanImpl()); 
        return localidadeComercial;
    }

    public void setLocalidadeComercial(LocalidadeComercialBean localidadeComercial)
    {
        this.localidadeComercial = localidadeComercial;
    }

    public Long getPaisItemSelecionado()
    {
        return paisItemSelecionado;
    }

    public void setPaisItemSelecionado(Long paisItemSelecionado)
    {
        this.paisItemSelecionado = paisItemSelecionado;
    }

    public Long getEstadoItemSelecionado()
    {
        return estadoItemSelecionado;
    }

    public void setEstadoItemSelecionado(Long estadoItemSelecionado)
    {
        this.estadoItemSelecionado = estadoItemSelecionado;
    }

    public Long getCidadeItemSelecionado()
    {
        return cidadeItemSelecionado;
    }

    public void setCidadeItemSelecionado(Long cidadeItemSelecionado)
    {
        this.cidadeItemSelecionado = cidadeItemSelecionado;
    }

    public List<SelectItem> getPaisesItem()
    {
        if(paisesItem == null)
            setPaisesItem(new ArrayList<SelectItem>()); 
        
        return paisesItem;
    }

    public void setPaisesItem(List<SelectItem> paisesItem)
    {
        this.paisesItem = paisesItem;
    }

    public List<SelectItem> getEstadosItem()
    {
        if(paisesItem == null)
            setEstadosItem(new ArrayList<SelectItem>()); 
        
        return estadosItem;
    }

    public void setEstadosItem(List<SelectItem> estadosItem)
    {
        this.estadosItem = estadosItem;
    }

    public List<SelectItem> getCidadesItem()
    {
        if(cidadesItem == null)
            setCidadesItem(new ArrayList<SelectItem>()); 
        
        return cidadesItem;
    }

    public void setCidadesItem(List<SelectItem> cidadesItem)
    {
        this.cidadesItem = cidadesItem;
    }

    public List<EmpresaBean> getEmpresas()
    {
        if(empresas == null)
            setEmpresas(new ArrayList<EmpresaBean>()); 
        
        return empresas;
    }

    public void setEmpresas(List<EmpresaBean> empresas)
    {
        this.empresas = empresas;
    }

    public List<PessoaBean> getResponsaveis()
    {
        if(responsaveis == null)
            setResponsaveis(new ArrayList<PessoaBean>()); 
        
        return responsaveis;
    }

    public void setResponsaveis(List<PessoaBean> responsaveis)
    {
        this.responsaveis = responsaveis;
    }

    public EmpresaBean getEmpresaBeanSelecionado()
    {
        if(empresaBeanSelecionado == null)
            setEmpresaBeanSelecionado(new EmpresaBeanImpl()); 
        
        return empresaBeanSelecionado;
    }

    public void setEmpresaBeanSelecionado(EmpresaBean empresaBeanSelecionado)
    {
        this.empresaBeanSelecionado = empresaBeanSelecionado;
    }

    public String getEmpresaStr()
    {
        return empresaStr;
    }

    public void setEmpresaStr(String empresaStr)
    {
        this.empresaStr = empresaStr;
    }

    public Boolean getEmpresaSelecionada()
    {
        return empresaSelecionada;
    }

    public void setEmpresaSelecionada(Boolean empresaSelecionada)
    {
        this.empresaSelecionada = empresaSelecionada;
    }

    public PessoaBean[] getResponsaveisSelecionados()
    {
        return responsaveisSelecionados;
    }

    public void setResponsaveisSelecionados(PessoaBean[] responsaveisSelecionados)
    {
        this.responsaveisSelecionados = responsaveisSelecionados;
    }

    public String getTela()
    {
        return tela;
    }

    public void setTela(String tela)
    {
        this.tela = tela;
    }

    public List<LocalidadeComercialBean> getLocalidadesComerciais()
    {
        if(localidadesComerciais == null)
            setLocalidadesComerciais(new ArrayList<LocalidadeComercialBean>()); 
        
        return localidadesComerciais;
    }

    public void setLocalidadesComerciais(List<LocalidadeComercialBean> localidadesComerciais)
    {
        this.localidadesComerciais = localidadesComerciais;
    }
    
}
