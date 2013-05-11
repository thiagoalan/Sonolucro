package com.financeiro.sonolucro.util;

import java.util.Locale;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.bean.impl.UsuarioBeanImpl;
import com.financeiro.sonolucro.controle.api.UsuarioControle;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "beanUtil")
@SessionScoped
public class BeanUtil implements Serializable
{
    private static final long serialVersionUID = 5220700056390898179L;

    @Inject
    private UsuarioControle usuarioControle;
    private UsuarioBean usuarioLogado;

    public Boolean getPermissao()
    {
        try
        {
            UsuarioBean usuario = getUsuarioLogado();
            Set<String> permissoes = usuario.getPermissao();

            Iterator iterator = permissoes.iterator();

            while (iterator.hasNext())
            {
                String permissao = (String) iterator.next();

                if (permissao.equals(UsuarioBeanImpl.PERMISSAO_ADMINISTRADOR))
                    return true;
            }

            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public String permissaoStr(UsuarioBean usuario)
    {
        try
        {
            Set<String> permissoes = usuario.getPermissao();

            Iterator iterator = permissoes.iterator();

            while (iterator.hasNext())
            {
                String permissao = (String) iterator.next();

                if (permissao.equals(UsuarioBeanImpl.PERMISSAO_ADMINISTRADOR))
                    return MensagemUtil.getMensagem("usuario_permissao_administrador");
            }

            return MensagemUtil.getMensagem("usuario_permissao_usuario");
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public UsuarioBean getUsuarioLogado()
    {
        try
        {
            if (usuarioLogado == null)
            {
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext external = context.getExternalContext();
                String email = external.getRemoteUser();

                if (email != null)
                    usuarioLogado = usuarioControle.buscarPorEmail(email);

            }

            return usuarioLogado;
        }
        catch (SonolucroControleException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void setUsuarioLogado(UsuarioBean usuarioLogado)
    {
        this.usuarioLogado = usuarioLogado; 
    }
    
    public void mudaIdiomaParaPtBr()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(new Locale("pt", "BR"));
    }

    public void mudaIdiomaParaEnUS()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(new Locale("en", "US"));
    }
    
    @PostConstruct
    public void alterarDataDeAcessoUsuario()
    {
        try
        {
            getUsuarioLogado().setDataAcesso(new Date());
            usuarioControle.alterar(usuarioLogado);
        }
        catch (SonolucroControleException e)
        {
            System.out.println("Erro ao atualizar data de acesso do Usuário. ");
            e.printStackTrace();
        }
    }
}
