
package com.financeiro.sonolucro.web.navegacao.empresa;

import com.financeiro.sonolucro.bean.api.PessoaBean;
import com.financeiro.sonolucro.bean.impl.ResponsavelEmpresaBeanImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

/**
 *
 * @author rodrigo
 * @since 30/12/2012
 * @version 1.0.0
 * 
 */

@Named(value = "responsavelEmpresaNavegacao")
@ViewAccessScoped  
public class ResponsavelEmpresaNavegacao implements Serializable
{
    protected static final String BEAN_NAME = "responsavelEmpresaNavegacao";
    @Inject
    private Conversation conversation; 
    private PessoaBean responsavelEmpresa; 
    private List<SelectItem> paisesItem; 
    private List<SelectItem> estadosItem; 
    private List<SelectItem> cidadesItem; 
    private Long paisItemSelecionado; 
    private Long estadoItemSelecionado; 
    private Long cidadeItemSelecionado;
    private Integer sexoItemSelecionado;
    private List<PessoaBean> responsavelEmpresas; 
    
    
    public PessoaBean getResponsavelEmpresa()
    {
        if(responsavelEmpresa ==  null)
            setResponsavelEmpresa(new ResponsavelEmpresaBeanImpl());
        
        return responsavelEmpresa;
    }

    public void setResponsavelEmpresa(PessoaBean responsavelEmpresa)
    {
        this.responsavelEmpresa = responsavelEmpresa;
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
        if(estadosItem == null)
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

    public Integer getSexoItemSelecionado()
    {
        return sexoItemSelecionado;
    }

    public void setSexoItemSelecionado(Integer sexoItemSelecionado)
    {
        this.sexoItemSelecionado = sexoItemSelecionado;
    }

    public List<PessoaBean> getResponsavelEmpresas()
    {
        if(responsavelEmpresas == null)
            setResponsavelEmpresas(new ArrayList<PessoaBean>());
        
        return responsavelEmpresas;
    }

    public void setResponsavelEmpresas(List<PessoaBean> responsavelEmpresas)
    {
        this.responsavelEmpresas = responsavelEmpresas;
    }

}
