package com.financeiro.sonolucro.controle.impl;

import com.financeiro.sonolucro.bean.api.ObjetivoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.controle.api.ObjetivoControle;
import com.financeiro.sonolucro.dao.ObjetivoDAO;
import com.financeiro.sonolucro.util.DataUtil;
import com.financeiro.sonolucro.util.SonolucroControleException;
import com.financeiro.sonolucro.util.SonolucroDAOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Rodrigo Romero
 * @since 04/03/2013
 * @version 1.0
 */
@Stateless
public class ObjetivoControleImpl implements ObjetivoControle<ObjetivoBean>, Serializable
{

    @Inject
    private ObjetivoDAO objetivoDAO;

    @Override
    public ObjetivoBean salvar(ObjetivoBean objetivo) throws SonolucroControleException
    {
        try
        {
            return objetivoDAO.salvar(objetivo);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }
    }

    @Override
    public ObjetivoBean alterar(ObjetivoBean objetivo) throws SonolucroControleException
    {
        try
        {
            return objetivoDAO.alterar(objetivo);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }
    }

    @Override
    public void apagar(ObjetivoBean objetivo) throws SonolucroControleException
    {
        try
        {
            objetivoDAO.apagar(objetivo);
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }
    }

    @Override
    public List<ObjetivoBean> listar(UsuarioBean usuario) throws SonolucroControleException
    {
        try
        {
            List<ObjetivoBean> objetivos =  objetivoDAO.listar(usuario.getId());
            
            return objetivos; 
        }
        catch (SonolucroDAOException e)
        {
            throw new SonolucroControleException(e);
        }
    }

    private Date getProximaData(Date dataInicial)
    {
        try
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataInicial);
            
            StringBuilder proximaDataStr = new StringBuilder();

            proximaDataStr.append(calendar.get(Calendar.DAY_OF_MONTH) + "/");
            proximaDataStr.append((calendar.get(Calendar.MONTH) + 2) + "/");
            proximaDataStr.append(calendar.get(Calendar.YEAR));

            SimpleDateFormat df = DataUtil.formatoPadrao();
            Date proximaData = df.parse(proximaDataStr.toString());

            return proximaData;
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
