package com.financeiro.sonolucro.web.navegacao.empresa;

import com.financeiro.sonolucro.bean.api.CidadeBean;
import com.financeiro.sonolucro.bean.api.EmpresaBean;
import com.financeiro.sonolucro.bean.api.EstadoBean;
import com.financeiro.sonolucro.bean.api.PaisBean;
import com.financeiro.sonolucro.bean.api.PessoaBean;
import com.financeiro.sonolucro.bean.impl.CidadeBeanImpl;
import com.financeiro.sonolucro.bean.impl.EmpresaBeanImpl;
import com.financeiro.sonolucro.controle.api.EmpresaControle;
import com.financeiro.sonolucro.controle.api.SonolucroControle;
import com.financeiro.sonolucro.util.MensagemUtil;
import com.financeiro.sonolucro.util.RequestUtils;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroViewException;
import com.financeiro.sonolucro.web.NavegacaoControle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

/**
 * @author rodrigo
 * @since 28/12/2012
 * @version 1.0.0
 */
@Named(value = "empresaNavegacaoControle")
@ViewAccessScoped  
public class EmpresaNavegacaoControle extends NavegacaoControle<EmpresaBean> implements Serializable
{

    protected static final String BEAN_NAME = "empresaNavegacaoControle";
    protected static final String TELA_EMPRESA = "empresa";
    protected static final String TELA_EDICAO = "empresaEdicao";
    protected static final String ID_COMPONENTE_MENSAGEM = "messageCadastroEmpresa";
    protected static final String ID_COMPONENTE_MENSAGEM_EDICAO = "messageEdicaoEmpresa";  
    
    @Inject
    private Conversation conversation;
    @Inject
    private EmpresaNavegacao navegacao;
    @Inject
    private SonolucroControle controle;
    @Inject
    private EmpresaControle empresaControle;
    
    @Override
    public void salvar(ActionEvent event)
    {
        try
        {
            EmpresaBean empresa = navegacao.getEmpresa();
            preparaBeanParaSalvar(empresa);
            empresaControle.salvar(empresa);
            preparaTela();
            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("empresa_msg_sucesso_salvar_empresa"));
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("empresa_msg_erro_salvar_empresa")); 
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            if (e.getMessageUsuario() != null && !e.getMessageUsuario().equals(""))
                MensagemUtil.mensagemObservacaoOperacao(ID_COMPONENTE_MENSAGEM, e.getMessageUsuario());
            else
                MensagemUtil.mensagemObservacaoOperacao(ID_COMPONENTE_MENSAGEM, MensagemUtil.getMensagem("responsavel_empresa_msg_erro_salvar"));
        }
    }

    @Override
    public void alterar(ActionEvent event)
    {   
        try
        {
            EmpresaBean empresa = navegacao.getEmpresa();
            preparaBeanParaAlterar(empresa); 
            empresa = empresaControle.alterar(empresa);
            preparaTelaEdicao(empresa);
            
            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM_EDICAO, 
                    MensagemUtil.getMensagem("empresa_msg_sucesso_alterar_empresa"));
            
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM_EDICAO, 
                    MensagemUtil.getMensagem("responsavel_empresa_msg_erro_alterar")); 
            e.printStackTrace(); 
        }
        catch (SonolucroViewException e)
        {
            if (e.getMessageUsuario() != null && !e.getMessageUsuario().equals(""))
                MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM_EDICAO, 
                    e.getMessageUsuario());
            else
                 MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM_EDICAO, 
                    MensagemUtil.getMensagem("responsavel_empresa_msg_erro_alterar"));
               
            e.printStackTrace();
        }
        catch (RuntimeException e)
        {
             MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM_EDICAO, 
                    MensagemUtil.getMensagem("responsavel_empresa_msg_erro_alterar"));
            
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(ActionEvent event)
    {
        FacesContext context = FacesContext.getCurrentInstance(); 
        
        try
        {
            EmpresaBean empresa = navegacao.getEmpresa(); 
            empresaControle.apagar(empresa);
            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("empresa_msg_sucesso_apagar_empresa"));
            preparaTela();
        }
        catch(SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("empresa_msg_erro_apagar_empresa"));
            e.printStackTrace();
        }
        catch(SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM, 
                    MensagemUtil.getMensagem("empresa_msg_erro_apagar_empresa"));
            e.printStackTrace();
        }
    }

    @Override
    public void preparaAlteracao(ActionEvent event)
    {
        try
        {
            EmpresaBean empresa = (EmpresaBean) RequestUtils.getRequest().getAttribute("empresa");

            navegacao.setEmpresa(empresa);
            
            if(event == null)
                preparaTelaEdicao(empresa); 
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
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

    @Override
    protected void limpaTela()
    {
        navegacao.setEmpresa(null);
        navegacao.setCidadeItemSelecionado(null);
        navegacao.setCidadesItem(null);
        navegacao.setEstadoItemSelecionado(null);
        navegacao.setEstadosItem(null);
        navegacao.setNossoClienteItemSelecionado(null);
        navegacao.setPaisItemSelecionado(null);
        navegacao.setPaisesItem(null);
        navegacao.setTipoItemSelecionado(null);
        navegacao.setTiposItem(null);
        navegacao.setResponsaveisSelecionados(null);
    }

    @Override
    protected void preparaBeanParaSalvar(EmpresaBean empresa) throws SonolucroViewException
    {
        try
        {

            Long idCidade = navegacao.getCidadeItemSelecionado();
            CidadeBeanImpl cidade = (CidadeBeanImpl) controle.buscarCidade(idCidade);

            if (cidade == null)
                throw new SonolucroViewException("Cidade nula.");

            empresa.setCidade(cidade);

            validaResponsaveisSelecionados(empresa);

            if(empresa.getDataCadastro() == null)
                empresa.setDataCadastro(new Date());

            if (navegacao.getStatusItemSelecionado().intValue() == 1)
                empresa.setStatus(true);
            else if (navegacao.getStatusItemSelecionado().intValue() == 2)
                empresa.setStatus(false);

            if (navegacao.getNossoClienteItemSelecionado().intValue() == 1)
                empresa.setECliente(true);
            else if (navegacao.getNossoClienteItemSelecionado().intValue() == 2)
                empresa.setECliente(false);

            if (navegacao.getTipoItemSelecionado().intValue() != 0)
                empresa.setTipo(navegacao.getTipoItemSelecionado());

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

    private void validaResponsaveisSelecionados(EmpresaBean empresa) throws SonolucroViewException
    {
        PessoaBean[] responsaveis = navegacao.getResponsaveisSelecionados();

        if (responsaveis.length > 5)
            throw new SonolucroViewException("Erro ao cadastrar, mais de 5 responsaveis pela empresa ",
                    MensagemUtil.getMensagem("empresa_msg_erro_size_responsaveis_cadastro"));

        if (responsaveis != null && responsaveis.length > 0)
        {
            List<PessoaBean> listaResponsaveis = new ArrayList<PessoaBean>(Arrays.asList(responsaveis));
            empresa.setResponsaveisPelaEmpresa(listaResponsaveis);
        }
        else
            empresa.setResponsaveisPelaEmpresa(null);
    }

    @Override
    protected void preparaBeanParaAlterar(EmpresaBean empresa) throws SonolucroViewException
    {
        preparaBeanParaSalvar(empresa); 
    }

    @Override
    protected void preparaTela() throws SonolucroViewException
    {
        limpaTela();
        iniciarPaises();
        iniciarTipos();
        iniciarEmpresas();
        iniciarResponsaveisEmpresa();
    }

    public String irParaTelaEmpresa()
    {
        try
        {
            preparaTela();
            navegacao.setTela("");
            return TELA_EMPRESA;
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    private void iniciarPaises() throws SonolucroViewException
    {
        try
        {
            List<PaisBean> paises = controle.listarPaises();

            if (paises != null)
            {
                List<SelectItem> paisesItem = new ArrayList<SelectItem>();

                for (PaisBean pais : paises)
                    paisesItem.add(new SelectItem(pais.getId(), pais.getNome()));

                navegacao.setPaisesItem(paisesItem);
            }
        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    public void iniciarEstados()
    {
        try
        {
            Long idPais = navegacao.getPaisItemSelecionado();

            if (idPais != null && idPais != 0)
            {
                PaisBean pais = controle.buscarPais(idPais);
                List<EstadoBean> estados = controle.listarEstados(pais);
                listarEstados(estados);
            }

        }
        catch (SonolucroControleException e)
        {
            e.printStackTrace();
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
    }

    public void iniciarCidades()
    {
        try
        {
            Long idEstado = navegacao.getEstadoItemSelecionado();

            if (idEstado != null && idEstado != 0)
            {
                EstadoBean estado = controle.buscarEstado(idEstado);
                List<CidadeBean> cidades = controle.listarCidades(estado);
                listarCidades(cidades);
            }
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

    private void iniciarTipos()
    {
        List<SelectItem> tiposItem = new ArrayList<SelectItem>();

        tiposItem.add(new SelectItem(EmpresaBeanImpl.TIPO_VAREJISTA,
                MensagemUtil.getMensagem("empresa_tipo_varejista")));
        tiposItem.add(new SelectItem(EmpresaBeanImpl.TIPO_BANCARIA,
                MensagemUtil.getMensagem("empresa_tipo_bancaria")));

        navegacao.setTiposItem(tiposItem);
    }

    private void iniciarEmpresas() throws SonolucroViewException
    {
        try
        {
            List<EmpresaBean> empresas = this.empresaControle.listar();
            navegacao.setEmpresas(empresas);
        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    private void iniciarResponsaveisEmpresa()
    {
        try
        {
            List<PessoaBean> responsaveisEmpresa = controle.listarResponsavelEmpresa();

            if (responsaveisEmpresa != null)
                navegacao.setResponsaveis(responsaveisEmpresa);
        }
        catch (SonolucroControleException e)
        {
            e.printStackTrace();
        }
    }

    public void removerResponsavelEmpresa(ActionEvent event)
    {
        try
        {
            PessoaBean responsavelRemover = (PessoaBean) RequestUtils.getRequest().getAttribute("responsavel");
            PessoaBean[] responsaveisEmpresaSelecionados = navegacao.getResponsaveisSelecionados();

            if (responsavelRemover != null && responsaveisEmpresaSelecionados != null)
            {
                Integer size = (responsaveisEmpresaSelecionados.length > 0) ? responsaveisEmpresaSelecionados.length - 1 : 0;

                PessoaBean[] responsaveisEmpresaSelecionadosAtualizado = new PessoaBean[size];
                List<PessoaBean> listaResponsaveis = new ArrayList<PessoaBean>();

                for (Integer i = 0; i < responsaveisEmpresaSelecionados.length; i++)
                {
                    if (!(responsaveisEmpresaSelecionados[i].getId().longValue() == responsavelRemover.getId().longValue()))
                        listaResponsaveis.add(responsaveisEmpresaSelecionados[i]);
                }

                for (Integer i = 0; i < responsaveisEmpresaSelecionadosAtualizado.length; i++)
                    responsaveisEmpresaSelecionadosAtualizado[i] = listaResponsaveis.get(i);

                navegacao.setResponsaveisSelecionados(responsaveisEmpresaSelecionadosAtualizado);
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        catch (IndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }

    }

    public String irParaTelaEdicao()
    {
        try
        {
            preparaAlteracao(null);
            navegacao.setTela("Altera");
            return TELA_EDICAO;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private void preparaTelaEdicao(EmpresaBean empresa) throws SonolucroViewException
    {
        try
        {
            PaisBean pais = empresa.getCidade().getEstado().getPais();
            navegacao.setPaisItemSelecionado(pais.getId());

            List<EstadoBean> estados = controle.listarEstados(pais);
            listarEstados(estados);
            navegacao.setEstadoItemSelecionado(empresa.getCidade().getEstado().getId());

            List<CidadeBean> cidades = controle.listarCidades(empresa.getCidade().getEstado());
            listarCidades(cidades);
            navegacao.setCidadeItemSelecionado(empresa.getCidade().getId());

            navegacao.setTipoItemSelecionado(empresa.getTipo());

            navegacao.setNossoClienteItemSelecionado(empresa.getECliente() ? 1 : 2);
            navegacao.setStatusItemSelecionado(empresa.getStatus() ? 1 : 2);

            List<PessoaBean> responsaveis = (List) empresa.getResponsaveisPelaEmpresa();

            if (responsaveis != null && responsaveis.size() > 0)
            {
                Integer size = responsaveis.size();
                PessoaBean[] responsaveisSelecionados = new PessoaBean[size];

                for (Integer i = 0; i < size; i++)
                    responsaveisSelecionados[i] = responsaveis.get(i);

                navegacao.setResponsaveisSelecionados(responsaveisSelecionados);
            }
        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }

    }
}
