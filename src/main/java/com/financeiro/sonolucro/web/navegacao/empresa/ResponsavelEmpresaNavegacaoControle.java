package com.financeiro.sonolucro.web.navegacao.empresa;

import com.financeiro.sonolucro.bean.api.CidadeBean;
import com.financeiro.sonolucro.bean.api.EstadoBean;
import com.financeiro.sonolucro.bean.api.PaisBean;
import com.financeiro.sonolucro.bean.api.PessoaBean;
import com.financeiro.sonolucro.controle.api.SonolucroControle;
import com.financeiro.sonolucro.util.MensagemUtil;
import com.financeiro.sonolucro.util.RequestUtils;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroViewException;
import com.financeiro.sonolucro.web.NavegacaoControle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

/**
 * @author rodrigo
 * @since 30/12/2012
 * @version 1.0
 */
@Named(value = "responsavelEmpresaNavegacaoControle")
@ViewAccessScoped  
public class ResponsavelEmpresaNavegacaoControle
        extends NavegacaoControle<PessoaBean> implements Serializable
{

    protected static final String BEAN_NAME = "responsavelEmpresaNavegacaoControle";
    protected static final String TELA_RESOPNSAVEL_LOCALIDADE_COMERCIAL = "responsavelEmpresa";
    protected static final String ID_COMPONENTE_MENSAGEM = "messageCadastroResponsavelEmpresa"; 
    
    @Inject
    Conversation conversation;
    @Inject
    ResponsavelEmpresaNavegacao navegacao;
    @Inject
    SonolucroControle controle;

    @Override
    public void salvar(ActionEvent event)
    {
        try
        {
            PessoaBean pessoa = navegacao.getResponsavelEmpresa();
            preparaBeanParaSalvar(pessoa);
            navegacao.setResponsavelEmpresa(controle.salvarResponsavelEmpresa(pessoa));
            preparaTela();
            
            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("responsavel_empresa_msg_sucesso_salvar_responsavel"));

        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("responsavel_empresa_msg_erro_salvar"));
            e.printStackTrace();
            navegacao.setResponsavelEmpresa(null);
        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("responsavel_empresa_msg_erro_salvar"));
            e.printStackTrace();
            navegacao.setResponsavelEmpresa(null);
        }
    }

    @Override
    public void alterar(ActionEvent event)
    {
        try
        {
            PessoaBean pessoa  = navegacao.getResponsavelEmpresa(); 
            preparaBeanParaAlterar(pessoa); 
            controle.alterarResponsavelEmpresa(pessoa); 
            preparaTela(); 
            
            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("responsavel_empresa_msg_sucesso_alterar_responsavel"));
        }
        catch(SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("responsavel_empresa_msg_erro_alterar"));
            e.printStackTrace();
        }
        catch(SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("responsavel_empresa_msg_erro_alterar"));
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(ActionEvent event)
    {
        try
        {
            PessoaBean pessoa = navegacao.getResponsavelEmpresa(); 
            controle.apagarResponsavelEmpresa(pessoa);
            preparaTela(); 
            
            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("responsavel_empresa_msg_sucesso_apagar_responsavel"));
        }
        catch(SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("responsavel_empresa_msg_erro_apagar"));
            e.printStackTrace();
        }
        catch(SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("responsavel_empresa_msg_erro_apagar"));
            e.printStackTrace();
        }
    }

    @Override
    public void preparaAlteracao(ActionEvent event)
    {
        try
        {
            PessoaBean responsavelEmpresa = (PessoaBean) RequestUtils.getRequest().getAttribute("responsavel");

            navegacao.setResponsavelEmpresa(responsavelEmpresa);
            navegacao.setSexoItemSelecionado(responsavelEmpresa.getSexo());

            CidadeBean cidade = responsavelEmpresa.getCidadeOndeReside();
            EstadoBean estado = cidade.getEstado();
            PaisBean pais = estado.getPais();

            navegacao.setCidadeItemSelecionado(cidade.getId());
            navegacao.setEstadoItemSelecionado(estado.getId());
            navegacao.setPaisItemSelecionado(pais.getId());

            List<EstadoBean> estados = controle.listarEstados(pais);
            listarEstados(estados);

            List<CidadeBean> cidades = controle.listarCidades(estado);
            listarCidades(cidades);
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            e.printStackTrace(); 
        }

    }

    @Override
    protected void limpaTela()
    {
        navegacao.setCidadeItemSelecionado(null);
        navegacao.setCidadesItem(null);
        navegacao.setEstadoItemSelecionado(null);
        navegacao.setEstadosItem(null);
        navegacao.setPaisItemSelecionado(null);
        navegacao.setPaisesItem(null);
        navegacao.setResponsavelEmpresa(null);
    }

    @Override
    protected void preparaTela() throws SonolucroViewException
    {
        limpaTela();
        iniciarPaises();
        iniciarResponsaveis();
    }

    public String irParaTelaResponsavelEmpresa()
    {
        try
        {
            preparaTela();
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
            return null;
        }

        return TELA_RESOPNSAVEL_LOCALIDADE_COMERCIAL;
    }

    private void iniciarPaises() throws SonolucroViewException
    {
        try
        {
            List<PaisBean> paises = controle.listarPaises();

            listarPaises(paises);
        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    public void iniciarEstados() throws SonolucroViewException
    {
        try
        {
            Long idPais = navegacao.getPaisItemSelecionado();
            PaisBean pais = controle.buscarPais(idPais);
            List<EstadoBean> estados = controle.listarEstados(pais);
            
            listarEstados(estados); 
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

    public void iniciarCidades() throws SonolucroViewException
    {
        try
        {
            Long idEstado = navegacao.getEstadoItemSelecionado();
            EstadoBean estado = controle.buscarEstado(idEstado);
            List<CidadeBean> cidades = controle.listarCidades(estado);

            listarCidades(cidades); 
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

    private void iniciarResponsaveis() throws SonolucroViewException
    {
        try
        {
            List<PessoaBean> responsaveis = controle.listarResponsavelEmpresa();
            navegacao.setResponsavelEmpresas(responsaveis);
        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    @Override
    protected void preparaBeanParaSalvar(PessoaBean pessoa) throws SonolucroViewException
    {
        try
        {
            Long idCidade = navegacao.getCidadeItemSelecionado();
            CidadeBean cidade = controle.buscarCidade(idCidade);

            if (cidade == null)
                throw new SonolucroViewException("Cidade não localizada.");

            pessoa.setCidadeOndeReside(cidade);
            pessoa.setSexo(navegacao.getSexoItemSelecionado());

        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    @Override
    protected void preparaBeanParaAlterar(PessoaBean pessoa) throws SonolucroViewException
    {
        preparaBeanParaSalvar(pessoa); 
    }

    private void listarPaises(List<PaisBean> paises)
    {
        if(paises != null)
        {
            List<SelectItem> paisesItem = new ArrayList<SelectItem>(); 
            
            for(PaisBean pais : paises)
                paisesItem.add(new SelectItem(pais.getId(), pais.getNome())); 
            
            navegacao.setPaisesItem(paisesItem);
        }
    }

    private void listarEstados(List<EstadoBean> estados)
    {
        if (estados != null)
        {
            List<SelectItem> estadosItem = new ArrayList<SelectItem>();

            for (EstadoBean estado : estados)
                estadosItem.add(new SelectItem(estado.getId(), estado.getNome()));

            navegacao.setEstadosItem(estadosItem);
        }
    }

    private void listarCidades(List<CidadeBean> cidades)
    {
        if (cidades != null)
        {
            List<SelectItem> cidadesItem = new ArrayList<SelectItem>();

            for (CidadeBean cidade : cidades)
                cidadesItem.add(new SelectItem(cidade.getId(), cidade.getNome()));
            navegacao.setCidadesItem(cidadesItem);
        }
    }
    
    public void responsavelSelecionado(ActionEvent event)
    {
        PessoaBean responsavel = (PessoaBean) RequestUtils.getRequest().getAttribute("responsavel");
        navegacao.setResponsavelEmpresa(responsavel);
    }

}
