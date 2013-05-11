package com.financeiro.sonolucro.web.navegacao.dica;

import com.financeiro.sonolucro.bean.api.DicaBean;
import com.financeiro.sonolucro.bean.api.IdiomaBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.bean.impl.DicaBeanImpl;
import com.financeiro.sonolucro.controle.api.SonolucroControle;
import com.financeiro.sonolucro.util.BeanUtil;
import com.financeiro.sonolucro.util.MensagemUtil;
import com.financeiro.sonolucro.util.RequestUtils;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroViewException;
import com.financeiro.sonolucro.web.NavegacaoControle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Rodrigo Romero
 * @since 26/12/2012
 * @version 1.0
 */
@Named(value = "dicaNavegacaoControle")
@SessionScoped
public class DicaNavegacaoControle extends NavegacaoControle<DicaBean> implements Serializable
{

    protected static final String TELA_DICA = "dica";
    protected static final String BEAN_NAME = "dicaNavegacaoControle";
    protected static final String ID_COMPONENTE_MENSAGEM = "messageCadastroDica"; 
    
    @Inject
    private Conversation conversation;
    @Inject
    private DicaNavegacao navegacao;
    @Inject
    private SonolucroControle controle;
    @Inject
    private BeanUtil beanUtil;
    
    private List<IdiomaBean> idiomas;
    private List<DicaBean> dicas;
    

    @Override
    public void salvar(ActionEvent event)
    {
        try
        {
            DicaBean dica = navegacao.getDica();
            preparaBeanParaSalvar(dica);
            controle.salvarDica(dica);
            preparaTela();
            
            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("dica_msg_sucesso_salvar_dica"));
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("dica_msg_erro_salvar_dica"));
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("dica_msg_erro_salvar_dica"));
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(ActionEvent envet)
    {
        try
        {
            DicaBean dica = navegacao.getDica();
            preparaBeanParaAlterar(dica);
            controle.alterarDica(dica);
            preparaTela();
            
            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("dica_msg_sucesso_alterar_dica"));
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("dica_msg_erro_alterar_dica"));
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
             MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("dica_msg_erro_alterar_dica"));
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(ActionEvent event)
    {
        try
        {
            DicaBean dica = navegacao.getDica();
            controle.apagarDica(dica);
            preparaTela();
            
            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("dica_msg_sucesso_apagar_dica"));
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("dica_msg_erro_apagar_dica"));
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("dica_msg_erro_apagar_dica"));
            e.printStackTrace();
        }
    }

    public String irParaTelaDica()
    {
        try
        {
            preparaTela();
            return TELA_DICA;
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public void preparaAlteracao(ActionEvent event)
    {
        try
        {
            DicaBean dica = (DicaBean) RequestUtils.getRequest().getAttribute("dica");


            if (dica != null)
            {
                DicaBean dicaBean = new DicaBeanImpl(dica);
                navegacao.setDica(dicaBean);
                navegacao.setIdiomaItemSelecinado(dica.getIdioma().getNumeroIdioma());
                navegacao.setCategoriaItemSelecionado(dica.getCategoria());
            }


        }
        catch (RuntimeException e)
        {
            e.printStackTrace(); 
        }
    }

    @Override
    protected void preparaTela() throws SonolucroViewException
    {
        try
        {
            limpaTela();
            iniciaIdiomas();
            iniciaDicas();
            iniciaCategorias();
        }
        catch (RuntimeException e)
        {
            throw new SonolucroViewException(e);
        }

    }

    private void iniciaIdiomas() throws SonolucroViewException
    {
        try
        {
            this.idiomas = controle.listarIdiomas();

            for (IdiomaBean idioma : idiomas)
                navegacao.getIdiomasItem().add(new SelectItem(idioma.getNumeroIdioma(), idioma.getIdioma()));
        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    private void iniciaDicas() throws SonolucroViewException
    {
        try
        {
            List<DicaBean> dicas = controle.listarDicas();

            if (dicas != null)
            {
                List<DicaBeanNavegacao> dicasBeanNavegacao = new ArrayList<DicaBeanNavegacao>();

                for (DicaBean dica : dicas)
                {
                    DicaBeanNavegacao dicaBeanNavegacao = new DicaBeanNavegacao(dica);
                    dicaBeanNavegacao.setResumoTexto(montarResumoTextoDica(dicaBeanNavegacao.getTexto()));
                    dicaBeanNavegacao.setCategoriaStr(montarCategoriaStr(dicaBeanNavegacao.getCategoria()));
                    dicasBeanNavegacao.add(dicaBeanNavegacao);
                }

                navegacao.setDicas(dicasBeanNavegacao);
            }

        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    private String montarResumoTextoDica(String texto)
    {
        String[] resumo = texto.split("");
        Integer size = resumo.length;
        String resumoTexto = "";

        if (size > 0)
        {
            for (Integer i = 0; i < size; i++)
            {

                resumoTexto += resumo[i];

                if (i >= 30)
                    break;
            }
        }

        return resumoTexto;
    }

    private String montarCategoriaStr(Integer categoria)
    {
        if (categoria.equals(DicaBeanImpl.CATEGORIA_SISTEMA))
            return MensagemUtil.getMensagem("dica_categoria_sistema");
        if (categoria.equals(DicaBeanImpl.CATEGORIA_ECONOMIA))
            return MensagemUtil.getMensagem("dica_categoria_economia");
        if (categoria.equals(DicaBeanImpl.CATEGORIA_INVESTIMENTO))
            return MensagemUtil.getMensagem("dica_categoria_investimento");

        return null;
    }

    private void iniciaCategorias() throws SonolucroViewException
    {
        try
        {
            SelectItem categoria = new SelectItem(DicaBeanImpl.CATEGORIA_SISTEMA,
                    MensagemUtil.getMensagem("dica_categoria_sistema"));
            navegacao.getCategoriasItem().add(categoria);

            categoria = new SelectItem(DicaBeanImpl.CATEGORIA_ECONOMIA,
                    MensagemUtil.getMensagem("dica_categoria_economia"));
            navegacao.getCategoriasItem().add(categoria);

            categoria = new SelectItem(DicaBeanImpl.CATEGORIA_INVESTIMENTO,
                    MensagemUtil.getMensagem("dica_categoria_investimento"));
            navegacao.getCategoriasItem().add(categoria);
        }
        catch (RuntimeException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    @Override
    protected void preparaBeanParaSalvar(DicaBean dica) throws SonolucroViewException
    {
        try
        {
            Integer numeroIdioma = navegacao.getIdiomaItemSelecinado();
            Integer categoria = navegacao.getCategoriaItemSelecionado();

            dica.setCategoria(categoria);

            if (this.idiomas == null)
                this.idiomas = controle.listarIdiomas();

            for (IdiomaBean idioma : idiomas)
            {
                if (idioma.getNumeroIdioma().equals(numeroIdioma))
                {
                    dica.setIdioma(idioma);
                    break;
                }
            }
        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
        catch (RuntimeException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    @Override
    protected void preparaBeanParaAlterar(DicaBean dica) throws SonolucroViewException
    {
        preparaBeanParaSalvar(dica);
    }

    @Override
    protected void limpaTela()
    {
        navegacao.setDica(null);
        navegacao.setDicas(null);
        navegacao.setIdiomaItemSelecinado(0);
        navegacao.setIdiomasItem(null);
        navegacao.setCategoriasItem(null);
    }

    public String getGerarDicas()
    {
        try
        {

            UsuarioBean usuarioLogado = beanUtil.getUsuarioLogado();
            dicas = controle.listarDicaPorIdioma(usuarioLogado.getIdioma());

            if (dicas != null && dicas.size() > 0)
            {
                Integer size = dicas.size();

                Random random = new Random();
                DicaBean dica = dicas.get(random.nextInt(size));

                return dica.getTexto();
            }
            else
                return null;
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (SonolucroControleException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
