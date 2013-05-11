package com.financeiro.sonolucro.controle.impl;

import java.util.List;

import javax.inject.Inject;

import com.financeiro.sonolucro.bean.api.CartaoBean;
import com.financeiro.sonolucro.bean.api.FaturaCartaoBean;
import com.financeiro.sonolucro.bean.api.MovimentacaoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.bean.impl.FaturaCartaoBeanImpl;
import com.financeiro.sonolucro.controle.api.CartaoControle;
import com.financeiro.sonolucro.dao.CartaoDAO;
import com.financeiro.sonolucro.dao.FaturaCartaoDAO;
import com.financeiro.sonolucro.dao.MovimentacaoDAO;
import com.financeiro.sonolucro.util.DataUtil;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import javax.ejb.Stateless;

/**
 * Implementacão padrão da Interface CartaoControle.
 *
 * @author Rodrigo Romero
 * @since 10/08/2012
 * @version 1.0
 */
@Stateless
public class CartaoControleImpl implements CartaoControle, Serializable
{
    private static final long serialVersionUID = -5319326107467303267L;

    @Inject
    private CartaoDAO cartaoDAO;
    @Inject
    private MovimentacaoDAO movDAO;
    @Inject
    private FaturaCartaoDAO faturaDAO;

    @Override
    public CartaoBean salvar(CartaoBean cartao) throws SonolucroControleException
    {

        try
        {
            return cartaoDAO.salvar(cartao);
        }
        catch (SonolucroDAOException e)
        {
            e.printStackTrace();
            throw new SonolucroControleException(e);
        }
    }

    @Override
    public CartaoBean alterar(CartaoBean cartao) throws SonolucroControleException
    {
        try
        {
            return cartaoDAO.alterar(cartao);
        }
        catch (SonolucroDAOException e)
        {
            e.printStackTrace();
            throw new SonolucroControleException(e);
        }
    }

    @Override
    public void apagar(CartaoBean cartao) throws SonolucroControleException
    {
        try
        {
            preparaCartaoParaApagar(cartao);
            cartaoDAO.apagar(cartao);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }
    }

    @Override
    public List<CartaoBean> listar(UsuarioBean usuario, Boolean processarSaldo) throws SonolucroControleException
    {
        try
        {
            List<CartaoBean> cartoes = cartaoDAO.listar(usuario.getId());

            if (processarSaldo)
            {
                for (CartaoBean cartao : cartoes)
                    processaSaldoCartao(cartao);
            }
            return cartoes;
        }
        catch (SonolucroDAOException e)
        {
            e.printStackTrace();
            throw new SonolucroControleException("Erro ao listar Cartão", e);
        }
    }

    @Override
    public CartaoBean buscarCartao(Long id) throws SonolucroControleException
    {
        try
        {
            return cartaoDAO.buscarCartao(id);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException("Erro ao buscar Cartão.", e);
        }
    }

    @Override
    public FaturaCartaoBean alterarFatura(FaturaCartaoBean fatura) throws SonolucroControleException
    {
        try
        {
            return faturaDAO.alterar(fatura);
        }
        catch (SonolucroDAOException e)
        {
            e.printStackTrace();
            throw new SonolucroControleException(e);
        }
    }

    protected void processaSaldoCartao(CartaoBean cartao) throws SonolucroControleException
    {

        try
        {
            List<MovimentacaoBean> movs = movDAO.listarMovimentacaoPorCartao(cartao);

            processaFaturas(cartao, movs);

            Float gastos = 0f;

            Date dataFinal = processaDataFinal(cartao.getDiaFechamentoFatura());

            for (FaturaCartaoBean fatura : cartao.getFaturas())
            {
                if (fatura.getDataInicial().before(dataFinal))
                    gastos = fatura.getValorTotal() - fatura.getValorPago();
            }

            Float saldo = cartao.getLimite() - gastos;

            cartao.setSaldo(saldo);
        }
        catch (SonolucroDAOException e)
        {
            e.printStackTrace();
            throw new SonolucroControleException(e);
        }

    }

    private void processaFaturas(CartaoBean cartao, List<MovimentacaoBean> movs) throws SonolucroControleException
    {

        List<FaturaCartaoBean> faturas = (List) cartao.getFaturas();

        for (MovimentacaoBean mov : movs)
        {
            Integer count = 0;

            for (FaturaCartaoBean fatura : faturas)
            {
                Date dataMovimentacao = mov.getDataVencimento();

                if (dataMovimentacao.after(fatura.getDataInicial())
                        && (dataMovimentacao.before(fatura.getDataFinal())
                        || dataMovimentacao.equals(fatura.getDataFinal())))
                {
                    count++;
                    Float valorFatura = fatura.getValorTotal();
                    Float valorMov = mov.getValor();
                    valorFatura += valorMov;
                    fatura.setValorTotal(valorFatura);
                    fatura.getMovimentacoes().add(mov);
                }
            }

            if (count == 0)
                criarNovaFatura(cartao, mov, faturas);

            count = 0;
        }

        for (Integer i = 0; i < faturas.size(); i++)
        {
            if (faturas.get(i).getMovimentacoes().size() == 0)
            {
                faturas.remove(i);
                continue;
            }

            if (faturas.get(i).getValorPago() < faturas.get(i).getValorTotal())
            {
                if (i != faturas.size() - 1)
                {
                    Float saldo = faturas.get(i).getValorTotal() - faturas.get(i).getValorPago();
                    faturas.get(i + 1).setValorTotal(saldo + faturas.get(i + 1).getValorTotal());
                }

            }
        }

        alterar(cartao);
    }

    private void criarNovaFatura(CartaoBean cartao, MovimentacaoBean mov,
                                 List<FaturaCartaoBean> faturas) throws SonolucroControleException
    {
        try
        {
            Integer indiceData = cartao.getDiaFechamentoFatura();

            SimpleDateFormat dateFormat = DataUtil.formatoPadrao();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(mov.getDataVencimento());

            Integer diaMov = calendar.get(calendar.DAY_OF_MONTH);

            Date dataInicial = null;
            Date dataFinal = null;

            if (diaMov <= indiceData)
            {
                dataInicial = dateFormat.parse((indiceData) + "/"
                        + (calendar.get(calendar.MONTH)) + "/"
                        + calendar.get(calendar.YEAR));
                dataFinal = dateFormat.parse((indiceData) + "/"
                        + (calendar.get(calendar.MONTH) + 1) + "/"
                        + calendar.get(calendar.YEAR));
            }
            else
            {
                dataInicial = dateFormat.parse((indiceData) + "/"
                        + (calendar.get(calendar.MONTH) + 1) + "/"
                        + calendar.get(calendar.YEAR));
                dataFinal = dateFormat.parse((indiceData) + "/"
                        + (calendar.get(calendar.MONTH) + 2) + "/"
                        + calendar.get(calendar.YEAR));
            }

            FaturaCartaoBean novaFatura = new FaturaCartaoBeanImpl(
                    dataInicial, dataFinal, 0f, mov.getValor());

            novaFatura.getMovimentacoes().add(mov);

            faturas.add(novaFatura);

            Collections.sort((List) faturas);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            throw new SonolucroControleException("Erro ao criar nova fatura.", e);
        }
    }

    private Date processaDataFinal(Integer indice)
    {
        try
        {
            SimpleDateFormat dateFormat = DataUtil.formatoPadrao();
            Calendar calendar = Calendar.getInstance();
            Date dataFinal = new Date();
            calendar.setTime(dataFinal);

            Integer diaAtual = calendar.get(calendar.DAY_OF_MONTH);

            if (diaAtual <= indice)
            {
                String dataFinalStr = (indice + 1) + "/" + calendar.get(calendar.MONTH) + "/" + calendar.get(calendar.YEAR);
                dataFinal = dateFormat.parse(dataFinalStr);
            }
            else
            {
                String dataFinalStr = (indice + 1) + "/" + (calendar.get(calendar.MONTH) + 1) + "/" + calendar.get(calendar.YEAR);
                dataFinal = dateFormat.parse(dataFinalStr);
            }

            return dataFinal;
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    protected void preparaCartaoParaApagar(CartaoBean cartao) throws SonolucroControleException
    {
        try
        {
            List<MovimentacaoBean> movs = movDAO.listarMovimentacaoPorCartao(cartao);

            if (movs != null)
            {
                for (MovimentacaoBean mov : movs)
                {
                    mov.setCartao(null);
                    movDAO.alterar(mov);
                }
            }
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException("Erro ao apagar Cartão.", e);
        }

    }
}
