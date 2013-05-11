package com.financeiro.sonolucro.web.navegacao.usuario;

import javax.inject.Named;

import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.bean.impl.UsuarioBeanImpl;
import com.financeiro.sonolucro.util.BeanUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

@Named(value = "usuarioNavegacao")
@ViewAccessScoped 
public class UsuarioNavegacao implements Serializable
{

    protected static final String BEAN_NAME = "usuarioNavegacao";
    
    private UsuarioBean usuarioLogado;
    private UsuarioBean usuarioCadastro;
    private UsuarioBean usuarioPesquisaEdicao; 
    private String senha;
    private String novaSenha;
    private String confirmaSenha;
    private Integer sexoItemSelecionado;
    private Integer idiomaItemSelecionado;
    private List<SelectItem> idiomasItem; 
    private List<SelectItem> sexosItem; 
    private List<UsuarioBeanNavegacao> usuarios;
    private Boolean adiministrador = false; 

    
    @Inject
    private BeanUtil beanUtil;
    @Inject
    private Conversation conversation;

    public String getNovaSenha()
    {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha)
    {
        this.novaSenha = novaSenha;
    }

    public String getConfirmaSenha()
    {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha)
    {
        this.confirmaSenha = confirmaSenha;
    }

    public Integer getSexoItemSelecionado()
    {
        return sexoItemSelecionado;
    }

    public void setSexoItemSelecionado(Integer sexoItemSelecionado)
    {
        this.sexoItemSelecionado = sexoItemSelecionado;
    }

    public Integer getIdiomaItemSelecionado()
    {
        return idiomaItemSelecionado;
    }

    public void setIdiomaItemSelecionado(Integer idiomaItemSelecionado)
    {
        this.idiomaItemSelecionado = idiomaItemSelecionado;
    }

    public UsuarioBean getUsuarioLogado()
    {
        if (usuarioLogado == null)
            setUsuarioLogado(beanUtil.getUsuarioLogado());

        return usuarioLogado;
    }

    public void setUsuarioLogado(UsuarioBean usuarioLogado)
    {
        this.usuarioLogado = usuarioLogado;
    }

    public UsuarioBean getUsuarioCadastro()
    {
        if (usuarioCadastro == null)
            setUsuarioCadastro(new UsuarioBeanImpl());

        return usuarioCadastro;
    }

    public void setUsuarioCadastro(UsuarioBean usuarioCadastro)
    {
        this.usuarioCadastro = usuarioCadastro;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public List<UsuarioBeanNavegacao> getUsuarios()
    {
        if (usuarios == null)
            setUsuarios(new ArrayList<UsuarioBeanNavegacao>());

        return usuarios;
    }

    public void setUsuarios(List<UsuarioBeanNavegacao> usuarios)
    {
        this.usuarios = usuarios;
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

    public List<SelectItem> getSexosItem()
    {
        if(sexosItem == null)
            setSexosItem(new ArrayList<SelectItem>());
        
        return sexosItem;
    }

    public void setSexosItem(List<SelectItem> sexosItem)
    {
        this.sexosItem = sexosItem;
    }

    public Boolean getAdiministrador()
    {
        return adiministrador;
    }

    public void setAdiministrador(Boolean adiministrador)
    {
        this.adiministrador = adiministrador;
    }

    public UsuarioBean getUsuarioPesquisaEdicao()
    {
        if(usuarioPesquisaEdicao == null)
            setUsuarioPesquisaEdicao(new UsuarioBeanImpl()); 
        
        return usuarioPesquisaEdicao;
    }

    public void setUsuarioPesquisaEdicao(UsuarioBean usuarioPesquisaEdicao)
    {
        this.usuarioPesquisaEdicao = usuarioPesquisaEdicao;
    }

    
}
