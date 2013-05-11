package com.financeiro.sonolucro.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;

public class DataUtil implements Serializable
{

    public static final String FORMATO_BR = "BR";
    public static final String FORMATO_US = "US";
    public static final String LOCALE_BRASIL = "pt_BR";
    public static final String LOCALE_EUA = "en_US";
    public static final String FORMATO_PADRAO = "dd/MM/yyyy";
    public static final String FORMATO_EUA = "yyyy/MM/dd";
    
    private static final long serialVersionUID = 3023731311699049204L;

    public static SimpleDateFormat formatoPadrao()
    {
        return new SimpleDateFormat(FORMATO_PADRAO);
    }

    public static SimpleDateFormat formatoUS()
    {
        return new SimpleDateFormat(FORMATO_EUA);
    }

    public SimpleDateFormat formatoUsuarioLogado(String locale)
    {

        if (locale.equals(LOCALE_BRASIL))
            return formatoPadrao();
        else if (locale.equals(LOCALE_EUA))
            return formatoUS();

        return null;

    }

    public String dataStringUsuarioLogado(Date data, String locale)
    {
        try
        {
            SimpleDateFormat dateFormat = formatoUsuarioLogado(locale);

            String dataStr = dateFormat.format(data);

            return dataStr;
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
            return null;
        }

    }
    
    public Date trocarFormatoData(Date data, String formatoDestino)
    {
        try
        {
            if (formatoDestino.equals(FORMATO_US))
            {
                String dataStr = formatoPadrao().format(data);
                data = formatoPadrao().parse(dataStr);
            }
            else if (formatoDestino.equals(FORMATO_BR))
            {
                String dataStr = formatoUS().format(data);
                data = formatoUS().parse(dataStr);
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return data;
    }

    public Integer getTotalDeDias(Date dataInicial, Date dataFinal)
    {
        try
        {
            DateTime tempoInicial = criarDateTime(dataInicial);

            DateTime tempoFinal = criarDateTime(dataFinal);

            Days dias = Days.daysBetween(tempoInicial, tempoFinal);

            Integer totalDeDias = dias.getDays();

            return totalDeDias;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public Integer getTotalDeMeses(Date dataInicial, Date dataFinal)
    {
        try
        {
            DateTime timeInicial = criarDateTime(dataInicial);

            DateTime timeFinal = criarDateTime(dataFinal);

            Months meses = Months.monthsBetween(timeInicial, timeFinal);

            Integer totalDeMeses = meses.getMonths();

            return totalDeMeses;
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    private DateTime criarDateTime(Date data)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        Integer ano = calendar.get(Calendar.YEAR);
        Integer mes = calendar.get(Calendar.MONTH) + 1;
        Integer dia = calendar.get(Calendar.DAY_OF_MONTH);
        Integer horas = calendar.get(Calendar.HOUR);
        Integer minutos = calendar.get(Calendar.MINUTE);

        DateTime dateTime = new DateTime(ano, mes, dia, (horas == null) ? 0 : horas,
                (minutos == null) ? 0 : minutos);

        return dateTime;
    }

    public Integer getUltimoDiaMes(Date data)
    {
        try
        {
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(data);

            calendar.setTime(formatoPadrao().parse((calendar.get(calendar.DAY_OF_MONTH) - calendar.get(calendar.DAY_OF_MONTH)) + "/"
                    + (calendar.get(calendar.MONTH) + 2) + "/" + calendar.get(calendar.YEAR)));

            Integer ultimoDia = calendar.get(Calendar.DAY_OF_MONTH);

            return ultimoDia;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public Integer getMes(Date data)
    {
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(data);
        Integer mes = calendar.get(Calendar.MONTH) + 1; 
        
        return mes; 
    }
    
    public Integer getAno(Date data)
    {
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(data);
        Integer ano = calendar.get(Calendar.YEAR); 
        
        return ano;
    }
    
    public Integer getDia(Date data)
    {
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(data);
        Integer dia = calendar.get(Calendar.DAY_OF_MONTH);
        
        return dia; 
    }
    
    public Date getDataApartir(Date data, Integer dias) 
    {
        DateTime dateTime = criarDateTime(data); 
        DateTime dataFinal = dateTime.plusDays(dias);
        
        GregorianCalendar calendar = new GregorianCalendar(); 
        calendar.setTimeInMillis(dataFinal.getMillis());
        
        return calendar.getTime(); 
    }
}
