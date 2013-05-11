package com.financeiro.sonolucro.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigo Romero
 * @since 10/03/2013
 * @version 1.0
 */
public class ValorUtil implements Serializable
{
    private static final long serialVersionUID = -306305177643641869L;

    public Float getValorReal(String valorStr)
    {
        try
        {
            if (valorStr == null || valorStr.equals(""))
                return 0.0f;

            String[] valoresStr = valorStr.split("");
            StringBuilder valorFloatStr = new StringBuilder();

            for (String valor : valoresStr)
            {
                if (valor.equals("."))
                    continue;
                if (valor.equals(","))
                {
                    valorFloatStr.append(".");
                    continue;
                }

                valorFloatStr.append(valor);
            }

            return Float.parseFloat(valorFloatStr.toString());
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public String getValoStr(Float valor)
    {
        try
        {
            String valorString = String.valueOf(valor);
            StringBuilder valorStr = new StringBuilder();

            List<String> listaReais = getReal(valorString);
            String centavosStr = getCentavos(valorString);

            if (listaReais.size() < 4)
            {
                for (String string : listaReais)
                    valorStr.append(string);

                valorStr.append(",");
                valorStr.append(centavosStr);

                return valorStr.toString();
            }
            else
            {
                int count = 0;

                List<String> listaTemp = new ArrayList<String>();
                for (int i = listaReais.size() - 1; i >= 0; i--)
                {
                    count++;

                    listaTemp.add(0, listaReais.get(i));

                    if (count != 1 && count % 3 == 0 && count < listaReais.size())
                        listaTemp.add(0, ".");
                }

                for (String string : listaTemp)
                    valorStr.append(string);

                valorStr.append(",");
                valorStr.append(centavosStr);

                return valorStr.toString();
            }
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public Float processaPorcentagem(Float x, Float y)
    {
        try
        {
            return (y * 100) / x;
        }
        catch (RuntimeException e)
        {
            return null;
        }
    }

    private List<String> getReal(String valorStr)
    {
        String[] valores = valorStr.split("");

        List<String> listaValores = new ArrayList<String>();

        for (String string : valores)
        {
            if (string.equals("."))
                break;

            if (!string.equals(""))
                listaValores.add(string);
        }

        return listaValores;
    }

    private String getCentavos(String valorStr)
    {
        if (valorStr.contains("."))
        {
            StringBuilder centavosStr = new StringBuilder();

            String[] valoresStr = valorStr.split("");

            Integer count = 0;

            for (String valor : valoresStr)
            {
                if (valor.equals(".") || count.intValue() > 0)
                {
                    count++;

                    if (!valor.equals("."))
                        centavosStr.append(valor);
                    if (count.intValue() == 3)
                        break;
                }
            }

            return centavosStr.toString();
        }
        else
            return "0";
    }
}