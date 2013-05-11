package com.financeiro.sonolucro.web.navegacao.cartao;

import java.util.List;

import javax.inject.Named;

import com.financeiro.sonolucro.bean.api.CartaoBean;
import com.financeiro.sonolucro.bean.impl.CartaoBeanImpl;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

/**
 * Recebe e envia informações da view Conta.
 *
 * @author Rodrigo Romero
 * @since 10/08/2012
 * @version 1.0
 */

@Named(value = "cartaoNavegacao")
@ViewAccessScoped  
public class CartaoNavegacao implements Serializable
{

    private static final long serialVersionUID = 5528906425513699588L;
    protected static final String BEAN_NAME = "cartaoNavegacao"; 
    
    @Inject
    private Conversation conversation; 
    private CartaoBean cartao;
    private List<CartaoBeanNavegacao> cartoes;
    private Integer statusItemSelecionado;
 

    public CartaoNavegacao()
    {
        
    }
     
    public CartaoBean getCartao()
    {
        if (cartao == null)
            setCartao(new CartaoBeanImpl());

        return cartao;
    }

    public void setCartao(CartaoBean cartao)
    {
        this.cartao = cartao;
    }

    public List<CartaoBeanNavegacao> getCartoes()
    {
        if (cartoes == null)
            setCartoes(new ArrayList<CartaoBeanNavegacao>());

        return cartoes;
    }

    public void setCartoes(List<CartaoBeanNavegacao> cartoes)
    {
        this.cartoes = cartoes;
    }

    public Integer getStatusItemSelecionado()
    {
        return statusItemSelecionado;
    }

    public void setStatusItemSelecionado(Integer statusItemSelecionado)
    {
        this.statusItemSelecionado = statusItemSelecionado;
    }
}
