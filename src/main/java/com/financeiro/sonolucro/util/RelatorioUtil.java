package com.financeiro.sonolucro.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/*
 * @author Rodrigo Romero
 * @version 1.0
 */

public class RelatorioUtil implements Serializable
{
    private static final long serialVersionUID = -7967049603922026738L;
    
    public static final int PDF = 1;
    public static final int EXCEL = 2;
    public static final int HTML = 3;
    public static final int PLANILHA_OPEN_OFFICE = 4;

    public static StreamedContent criaRelatorio(HashMap parametrosRelatorio, String nomeRelatorio,
                                                String relatorioSaida, Integer tipoRelatorio)
    {
        StreamedContent relatorio = null;

        try
        {
            JasperReport relatorioJasper = buscaRelatorioJasper(nomeRelatorio);
            JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper,
                    parametrosRelatorio, getConnection());

            relatorio = processaTipoDeArquivo(impressoraJasper, relatorioSaida, tipoRelatorio);
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }

        return relatorio;
    }

    private static JasperReport buscaRelatorioJasper(String nomeRelatorio) throws JRException
    {
        String caminhoArquivo = pastaRelatorios() + File.separator + nomeRelatorio + ".jasper";
        JasperReport relatorioJasper = (JasperReport) JRLoader.loadObject(caminhoArquivo);

        return relatorioJasper;
    }

    private static String pastaRelatorios()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        String caminho = context.getExternalContext().getRealPath("relatorios");

        return caminho;
    }

    private static StreamedContent processaTipoDeArquivo(JasperPrint impressoraJasper, String relatorioSaida,
                                                         Integer tipo) throws FileNotFoundException, JRException
    {
        String caminhoArquivoRelatorio = null;
        JRExporter tipoArquivoExportado = null;
        String extensaoArquivoExportado = "";
        File arquivoGerado = null;

        switch (tipo)
        {
            case PDF:
                tipoArquivoExportado = new JRPdfExporter();
                extensaoArquivoExportado = "pdf";
                break;

            case HTML:
                tipoArquivoExportado = new JRHtmlExporter();
                extensaoArquivoExportado = "html";
                break;

            case EXCEL:
                tipoArquivoExportado = new JRXlsExporter();
                extensaoArquivoExportado = "xls";
                break;

            case PLANILHA_OPEN_OFFICE:
                tipoArquivoExportado = new JROdtExporter();
                extensaoArquivoExportado = "ods";
                break;

            default:
                tipoArquivoExportado = new JRPdfExporter();
                extensaoArquivoExportado = "pdf";

        }

        caminhoArquivoRelatorio = pastaRelatorios() + File.separator + relatorioSaida + "." + extensaoArquivoExportado;
        arquivoGerado = new File(caminhoArquivoRelatorio);

        tipoArquivoExportado.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
        tipoArquivoExportado.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
        tipoArquivoExportado.exportReport();
        arquivoGerado.deleteOnExit();

        InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);

        return new DefaultStreamedContent(conteudoRelatorio, "application/" + extensaoArquivoExportado,
                relatorioSaida + "." + extensaoArquivoExportado);

    }

    private static Connection getConnection()
    {
        Connection connection = null;

        try
        {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env/");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/SonolucroDB");
            connection = (Connection) dataSource.getConnection();
        }
        catch (NamingException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return connection;
    }
}
