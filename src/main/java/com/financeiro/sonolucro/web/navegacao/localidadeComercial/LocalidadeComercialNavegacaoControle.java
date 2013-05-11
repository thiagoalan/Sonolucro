package com.financeiro.sonolucro.web.navegacao.localidadeComercial;

import com.financeiro.sonolucro.bean.api.CidadeBean;
import com.financeiro.sonolucro.bean.api.EmpresaBean;
import com.financeiro.sonolucro.bean.api.EstadoBean;
import com.financeiro.sonolucro.bean.api.LocalidadeComercialBean;
import com.financeiro.sonolucro.bean.api.PaisBean;
import com.financeiro.sonolucro.bean.api.PessoaBean;
import com.financeiro.sonolucro.controle.api.EmpresaControle;
import com.financeiro.sonolucro.controle.api.LocalidadeComercialControle;
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

/**
 * @author Rodrigo
 * @since 30/12/2012
 * @version 1.0
 */
@Named(value = "localidadeComercialNavegacaoControle")
//@ConversationScoped
public class LocalidadeComercialNavegacaoControle extends NavegacaoControle<LocalidadeComercialBean>
        implements Serializable
{

    protected static final String BEAN_NAME = "localidadeComercialNavagacaoControle";
    protected static final String TELA_LOCALIDADE_COMERCIAL = "localidadeComercial";
    protected static final String TELA_LOCALIDADE_COMERCIAL_EDICAO = "localidadeComercialEdicao";
    protected static final String ID_COMPONENTE_MENSAGEM = "messageLocalidadeComercialCadastro";
    protected static final String ID_COMPONENTE_MENSAGEM_EDICAO = "messageLocalidadeComercialEdicao";
    @Inject
    private Conversation conversation;
    @Inject
    private LocalidadeComercialNavegacao navegacao;
    @Inject
    private LocalidadeComercialControle controle;
    @Inject
    private SonolucroControle sonolucroControle;
    @Inject
    private EmpresaControle empresaControle;
    private List<PaisBean> paises;

    @Override
    public void salvar(ActionEvent event)
    {
        try
        {
            LocalidadeComercialBean localidadeComercial = navegacao.getLocalidadeComercial();
            preparaBeanParaSalvar(localidadeComercial);
            controle.salvar(localidadeComercial);

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("localidade_comercial_msg_sucesso_salvar"));

            preparaTela();

        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("localidade_comercial_msg_erro_salvar"));
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("localidade_comercial_msg_erro_salvar"));
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(ActionEvent event)
    {
        try
        {
            LocalidadeComercialBean localidadeComercial = navegacao.getLocalidadeComercial();
            preparaBeanParaAlterar(localidadeComercial);
            controle.alterar(localidadeComercial);

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM_EDICAO,
                    MensagemUtil.getMensagem("localidade_comercial_msg_sucesso_alterar"));
        }
        catch (SonolucroViewException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM_EDICAO,
                    MensagemUtil.getMensagem("localidade_comercial_msg_erro_alterar"));
            e.printStackTrace();
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM_EDICAO,
                    MensagemUtil.getMensagem("localidade_comercial_msg_erro_alterar"));
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(ActionEvent event)
    {
        try
        {
            LocalidadeComercialBean localidadeComercial = navegacao.getLocalidadeComercial();
            controle.apagar(localidadeComercial);

            MensagemUtil.mensagemOperacaoSucesso(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("localidade_comercial_msg_sucesso_apagar"));

            preparaTela();
        }
        catch (SonolucroControleException e)
        {
            MensagemUtil.mensagemOperacaoErro(ID_COMPONENTE_MENSAGEM,
                    MensagemUtil.getMensagem("localidade_comercial_msg_erro_apagar"));
            e.printStackTrace();
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void preparaAlteracao(ActionEvent event)
    {
        try
        {
            LocalidadeComercialBean localidadeComercial = (LocalidadeComercialBean) RequestUtils.getRequest().getAttribute("localidadeComercial");
            navegacao.setLocalidadeComercial(localidadeComercial);

            if (event == null)
                preparaTelaEdicao(localidadeComercial);

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

    @Override
    protected void limpaTela()
    {
        navegacao.setLocalidadeComercial(null);
        navegacao.setCidadeItemSelecionado(null);
        navegacao.setCidadesItem(null);
        navegacao.setEmpresaSelecionada(Boolean.FALSE);
        navegacao.setEstadoItemSelecionado(null);
        navegacao.setEstadosItem(null);
        navegacao.setResponsaveisSelecionados(null);
        navegacao.setPaisItemSelecionado(null);
        navegacao.setLocalidadesComerciais(null);
    }

    @Override
    protected void preparaBeanParaSalvar(LocalidadeComercialBean localidadeComercial) throws SonolucroViewException
    {
        try
        {
            Long idCidade = navegacao.getCidadeItemSelecionado();

            CidadeBean cidade = sonolucroControle.buscarCidade(idCidade);

            if (cidade == null)
                throw new SonolucroViewException("Cidade nula ao salvar Localidade Comercial");

            localidadeComercial.setCidade(cidade);

            validaResponsaveisSelecionados(localidadeComercial);

        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    @Override
    protected void preparaBeanParaAlterar(LocalidadeComercialBean localidadeComercial) throws SonolucroViewException
    {
        preparaBeanParaSalvar(localidadeComercial);
    }

    @Override
    protected void preparaTela() throws SonolucroViewException
    {
        limpaTela();
        iniciarPaises();
        iniciarEmpresas();
        iniciarResponsaveis();
        listarLocalidadesComerciais();
        String empresaStr = MensagemUtil.getMensagem("localidade_comercial_empresa");
        navegacao.setEmpresaStr(empresaStr);
    }

    public String irParaTelaLocalidadeComercial()
    {
        try
        {
            preparaTela();
            navegacao.setTela("");
            return TELA_LOCALIDADE_COMERCIAL;
        }
        catch (SonolucroViewException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public String irParaTelaEdicao()
    {
        try
        {
            preparaAlteracao(null);
            navegacao.setTela("Altera");
            return TELA_LOCALIDADE_COMERCIAL_EDICAO;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private void iniciarPaises()
    {
        try
        {
            paises = sonolucroControle.listarPaises();
            listarPaises(paises);
        }
        catch (SonolucroControleException e)
        {
            e.printStackTrace();
        }
    }

    public void iniciarEstados()
    {
        try
        {
            Long idPais = navegacao.getPaisItemSelecionado();

            if (idPais != null && idPais != 0)
            {
                PaisBean pais = sonolucroControle.buscarPais(idPais);
                List<EstadoBean> estados = sonolucroControle.listarEstados(pais);

                listarEstados(estados);
            }

        }
        catch (SonolucroControleException e)
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
                EstadoBean estado = sonolucroControle.buscarEstado(idEstado);
                List<CidadeBean> cidades = sonolucroControle.listarCidades(estado);
                listarCidades(cidades);
            }
        }
        catch (SonolucroControleException e)
        {
            e.printStackTrace();
        }
    }

    public void iniciarEmpresas()
    {
        try
        {
            List<EmpresaBean> empresas = empresaControle.listar();
            navegacao.setEmpresas(empresas);
        }
        catch (SonolucroControleException e)
        {
            e.printStackTrace();
        }
    }

    public void iniciarResponsaveis()
    {
        try
        {
            List<PessoaBean> responsaveis = sonolucroControle.listarResponsavelEmpresa();
            navegacao.setResponsaveis(responsaveis);
        }
        catch (SonolucroControleException e)
        {
            e.printStackTrace();
        }
    }

    public void adicionarEmpresa()
    {
        try
        {
            String empresaStr = navegacao.getEmpresaBeanSelecionado().getNomeFantasia();
            EmpresaBean empresaSelecionada = navegacao.getEmpresaBeanSelecionado();
            navegacao.getLocalidadeComercial().setEmpresa(empresaSelecionada);
            navegacao.setEmpresaStr(empresaStr);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    public void removerResponsavelSelecionado(ActionEvent event)
    {
        PessoaBean responsavelRemover = (PessoaBean) RequestUtils.getRequest().getAttribute("responsavel");
        PessoaBean[] responsaveisLocalidadeComercialSelecionados = navegacao.getResponsaveisSelecionados();

        if (responsavelRemover != null && responsaveisLocalidadeComercialSelecionados != null)
        {
            Integer size = (responsaveisLocalidadeComercialSelecionados.length > 0) ? responsaveisLocalidadeComercialSelecionados.length - 1 : 0;

            PessoaBean[] responsaveisLocalidadeComercialSelecionadosAtualizado = new PessoaBean[size];
            List<PessoaBean> listaResponsaveis = new ArrayList<PessoaBean>();

            for (Integer i = 0; i < responsaveisLocalidadeComercialSelecionados.length; i++)
            {
                if (!(responsaveisLocalidadeComercialSelecionados[i].getId().longValue() == responsavelRemover.getId().longValue()))
                    listaResponsaveis.add(responsaveisLocalidadeComercialSelecionados[i]);
            }

            for (Integer i = 0; i < responsaveisLocalidadeComercialSelecionadosAtualizado.length; i++)
                responsaveisLocalidadeComercialSelecionadosAtualizado[i] = listaResponsaveis.get(i);

            navegacao.setResponsaveisSelecionados(responsaveisLocalidadeComercialSelecionadosAtualizado);
        }
    }

    private void listarPaises(List<PaisBean> paises)
    {
        if (paises != null)
        {
            List<SelectItem> paisesItem = new ArrayList<SelectItem>();

            for (PaisBean pais : paises)
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

    private void validaResponsaveisSelecionados(LocalidadeComercialBean localidadeComercial) throws SonolucroViewException
    {
        PessoaBean[] responsaveis = navegacao.getResponsaveisSelecionados();

        if (responsaveis.length > 5)
            throw new SonolucroViewException("Erro ao cadastrar, mais de 5 responsaveis pela empresa ",
                    MensagemUtil.getMensagem("empresa_msg_erro_size_responsaveis_cadastro"));

        if (responsaveis != null && responsaveis.length > 0)
        {
            List<PessoaBean> listaResponsaveis = new ArrayList<PessoaBean>();

            for (PessoaBean responsavel : responsaveis)
                listaResponsaveis.add(responsavel);

            localidadeComercial.setResponsavelLocalidadeComercial(listaResponsaveis);

        }
        else
            localidadeComercial.setResponsavelLocalidadeComercial(null);
    }

    private void listarLocalidadesComerciais() throws SonolucroViewException
    {
        try
        {
            List<LocalidadeComercialBean> localidadesComerciais = controle.listar();
            carregarListaResponsaveisParaLazy(localidadesComerciais);
            navegacao.setLocalidadesComerciais(localidadesComerciais);
        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }

    }

    private void preparaTelaEdicao(LocalidadeComercialBean localidadeComercial) throws SonolucroViewException
    {
        try
        {
            PaisBean pais = localidadeComercial.getCidade().getEstado().getPais();
            navegacao.setPaisItemSelecionado(pais.getId());

            List<EstadoBean> estados = sonolucroControle.listarEstados(pais);
            listarEstados(estados);
            navegacao.setEstadoItemSelecionado(localidadeComercial.getCidade().getEstado().getId());

            List<CidadeBean> cidades = sonolucroControle.listarCidades(localidadeComercial.getCidade().getEstado());
            listarCidades(cidades);
            navegacao.setCidadeItemSelecionado(localidadeComercial.getCidade().getId());

            List<PessoaBean> responsaveis = (List) controle.listarResponsaveisPorLocalidadeComercial(localidadeComercial);

            navegacao.setEmpresaStr(localidadeComercial.getEmpresa().getNomeFantasia());

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

    /*
     * Evita erro de lazy
     */
    private void carregarListaResponsaveisParaLazy(List<LocalidadeComercialBean> localidadesComerciais) throws SonolucroViewException
    {
        try
        {
            for (LocalidadeComercialBean localidadeComercial : localidadesComerciais)
            {
                List<PessoaBean> responsaveis = controle.listarResponsaveisPorLocalidadeComercial(localidadeComercial);
                localidadeComercial.setResponsavelLocalidadeComercial(responsaveis);
            }
        }
        catch (SonolucroControleException e)
        {
            throw new SonolucroViewException(e);
        }

    }
}
