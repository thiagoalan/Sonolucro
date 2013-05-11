
package com.financeiro.sonolucro.web.navegacao.empresa;

import com.financeiro.sonolucro.bean.api.EmpresaBean;
import com.financeiro.sonolucro.bean.api.PessoaBean;
import com.financeiro.sonolucro.bean.impl.EmpresaBeanImpl;
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
 * @since 28/12/2012
 * @version 1.0.0
 */
@Named(value = "empresaNavegacao")
@ViewAccessScoped  
public class EmpresaNavegacao implements Serializable
{
    protected static final String BEAN_NAME = "empresaNavegacao"; 
    
    @Inject
    private Conversation conversation; 
    private EmpresaBean empresa;
    private Integer nossoClienteItemSelecionado; 
    private List<SelectItem> paisesItem; 
    private Long paisItemSelecionado; 
    private List<SelectItem> estadosItem; 
    private Long estadoItemSelecionado; 
    private List<SelectItem> cidadesItem; 
    private Long cidadeItemSelecionado;
    private Integer statusItemSelecionado;
    private List<SelectItem> tiposItem; 
    private Integer tipoItemSelecionado;
    private List<EmpresaBean> empresas; 
    private List<PessoaBean> responsaveis; 
    private PessoaBean[] responsaveisSelecionados;
    private String tela; 
    
   
    public EmpresaBean getEmpresa()
    {
        if(empresa == null)
            setEmpresa(new EmpresaBeanImpl()); 
        
        return empresa;
    }

    public void setEmpresa(EmpresaBean empresa)
    {
        this.empresa = empresa;
    }

    public Integer getNossoClienteItemSelecionado()
    {
        return nossoClienteItemSelecionado;
    }

    public void setNossoClienteItemSelecionado(Integer nossoClienteItemSelecionado)
    {
        this.nossoClienteItemSelecionado = nossoClienteItemSelecionado;
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

    public Long getPaisItemSelecionado()
    {
        return paisItemSelecionado;
    }

    public void setPaisItemSelecionado(Long paisItemSelecionado)
    {
        this.paisItemSelecionado = paisItemSelecionado;
    }

    public List<SelectItem> getEstadosItem()
    {
        if(estadosItem == null)
            setEstadosItem(new ArrayList<SelectItem>()); 
        
        return estadosItem;
    }

    public void setEstadosItem(List<SelectItem> estadosItem)
    {
        this.estadosItem = estadosItem;
    }

    public Long getEstadoItemSelecionado()
    {
        return estadoItemSelecionado;
    }

    public void setEstadoItemSelecionado(Long estadoItemSelecionado)
    {
        this.estadoItemSelecionado = estadoItemSelecionado;
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

    public Long getCidadeItemSelecionado()
    {
        return cidadeItemSelecionado;
    }

    public void setCidadeItemSelecionado(Long cidadeItemSelecionado)
    {
        this.cidadeItemSelecionado = cidadeItemSelecionado;
    }

    public Integer getStatusItemSelecionado()
    {
        return statusItemSelecionado;
    }

    public void setStatusItemSelecionado(Integer statusItemSelecionado)
    {
        this.statusItemSelecionado = statusItemSelecionado;
    }

    public List<SelectItem> getTiposItem()
    {
        return tiposItem;
    }

    public void setTiposItem(List<SelectItem> tiposItem)
    {
        this.tiposItem = tiposItem;
    }

    public Integer getTipoItemSelecionado()
    {
        return tipoItemSelecionado;
    }

    public void setTipoItemSelecionado(Integer tipoItemSelecionado)
    {
        this.tipoItemSelecionado = tipoItemSelecionado;
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

    public PessoaBean[] getResponsaveisSelecionados()
    {
      //  if(responsaveisSelecionados == null)
        //    setResponsaveisSelecionados(new ArrayList<PessoaBean>()); 
        
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
}
