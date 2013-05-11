package com.financeiro.sonolucro.web.navegacao.dica;

import com.financeiro.sonolucro.bean.api.DicaBean;
import com.financeiro.sonolucro.bean.impl.DicaBeanImpl;
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
 * @author Rodrigo Romero
 * @since 26/12/2012
 * @version 1.0
 *
 */

@Named(value = "dicaNavegacao")
@ViewAccessScoped  
public class DicaNavegacao implements Serializable
{
    protected static final String BEAN_NAME = "dicaNavegacao"; 
    
    @Inject
    private Conversation conversation;
    private DicaBean dica; 
    private Integer idiomaItemSelecinado; 
    private Integer categoriaItemSelecionado; 
    private List<SelectItem> idiomasItem; 
    private List<SelectItem> categoriasItem; 
    private List<DicaBeanNavegacao> dicas;
    
    public DicaNavegacao()
    {
        
    }
        
    public DicaBean getDica()
    {
        if(dica == null)
            setDica(new DicaBeanImpl()); 
        
        return dica;
    }

    public void setDica(DicaBean dica)
    {
        this.dica = dica;
    }

    public Integer getIdiomaItemSelecinado()
    {
        return idiomaItemSelecinado;
    }

    public void setIdiomaItemSelecinado(Integer idiomaItemSelecinado)
    {
        this.idiomaItemSelecinado = idiomaItemSelecinado;
    }

    public List<SelectItem> getIdiomasItem()
    {
        if(idiomasItem == null)
            setIdiomasItem(new ArrayList<SelectItem>()); 
        
        return idiomasItem;
    }

    public void setIdiomasItem(List<SelectItem> idiomasItem)
    {
        this.idiomasItem = idiomasItem;
    }

    public List<DicaBeanNavegacao> getDicas()
    {
        return dicas;
    }

    public void setDicas(List<DicaBeanNavegacao> dicas)
    {
        this.dicas = dicas;
    }

    public Integer getCategoriaItemSelecionado()
    {
        return categoriaItemSelecionado;
    }

    public void setCategoriaItemSelecionado(Integer categoriaItemSelecionado)
    {
        this.categoriaItemSelecionado = categoriaItemSelecionado;
    }

    public List<SelectItem> getCategoriasItem()
    {
        if(categoriasItem == null)
            setCategoriasItem(new ArrayList<SelectItem>()); 
        
        return categoriasItem;
    }

    public void setCategoriasItem(List<SelectItem> categoriasItem)
    {
        this.categoriasItem = categoriasItem;
    }
}
