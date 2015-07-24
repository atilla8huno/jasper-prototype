package br.com.oobj.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("all")
public class GeradorRelatorio {

	public static final String RELATORIO_PREFIX = "resources" + File.separator + "jasper";
	public static final String CONTEXT_ROOT = "CONTEXT_ROOT";
	public static final String PAGE_INDEX = "PAGE_INDEX";

	public byte[] gerarRelatorio(String arquivoJasper, Map<String, Object> parameters) throws Exception {
		return gerarPDF(preencherRelatorio(arquivoJasper, null, parameters, null));
	}

	public byte[] gerarRelatorio(String arquivoJasper, Collection<?> collection) throws Exception {
		return gerarPDF(preencherRelatorio(arquivoJasper, collection, null, null));
	}

	public byte[] gerarRelatorio(String arquivoJasper, Collection<?> collection, Map<String, Object> parameters)
			throws Exception {
		return gerarPDF(preencherRelatorio(arquivoJasper, collection, parameters, null));
	}

	public byte[] gerarRelatorio(Relatorio relatorio) throws Exception {
		return gerarPDF(preencherRelatorio(relatorio.getArquivoJasper(), relatorio.getCollection(),
				relatorio.getParameters(), null));
	}

	public byte[] gerarRelatorios(List<Relatorio> relatorios) throws Exception {
		// gera a lista de JasperPrint a partir da lista de relatorios
		List<JasperPrint> listaJasperPrint = new ArrayList<JasperPrint>();
		Integer pageIndex = 0;
		for (Relatorio relatorio : relatorios) {
			if (listaJasperPrint.size() > 0) {
				pageIndex += listaJasperPrint.get(listaJasperPrint.size() - 1).getPages().size();
			}
			listaJasperPrint.add(preencherRelatorio(relatorio.getArquivoJasper(), relatorio.getCollection(),
					relatorio.getParameters(), pageIndex));
		}
		// gera o pdf
		return gerarPDF(listaJasperPrint);
	}

	private byte[] gerarPDF(JasperPrint jasperPrint) throws Exception {
		// transforma o JasperPrint em pdf e retorna seus bytes[]
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@SuppressWarnings("unused")
	private byte[] gerarDocx(JasperPrint jasperPrint) throws Exception {
		// transforma o JasperPrint em Docx e retorna seus bytes[]
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		JRDocxExporter ex = new JRDocxExporter();
		ex.setParameter(JRExporterParameter.OUTPUT_STREAM, arrayOutputStream);
		ex.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		ex.exportReport();

		arrayOutputStream.flush();
		arrayOutputStream.close();

		return arrayOutputStream.toByteArray();
	}

	private byte[] gerarPDF(List<JasperPrint> listaJasperPrint) throws Exception {
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		JRPdfExporter ex = new JRPdfExporter();

		// transforma a lista de JasperPrint em pdf e preenche o arrayOutputStream
		ex.setParameter(JRExporterParameter.OUTPUT_STREAM, arrayOutputStream);
		ex.setParameter(JRExporterParameter.JASPER_PRINT_LIST, listaJasperPrint);
		ex.exportReport();

		arrayOutputStream.flush();
		arrayOutputStream.close();

		return arrayOutputStream.toByteArray();
	}

	private JasperPrint preencherRelatorio(String arquivoJasper, Collection<?> collection,
			Map<String, Object> parameters, Integer pageIndex) throws Exception {

		// adiciona o parametro REPORT_DIR usado para imagens e sub_relatorios
		if (parameters == null) {
			parameters = new HashMap<String, Object>();
		}

		if (pageIndex != null && pageIndex > 0) {
			parameters.put(GeradorRelatorio.PAGE_INDEX, pageIndex);
		}

		JRDataSource dataSource = null;

		// cria o DataSource caso exista uma collection
		if (collection != null && collection.size() > 0) {
			dataSource = new JRBeanCollectionDataSource(collection);
		}

		// gera o jasperPrint de acordo com os dados recebidos
		JasperPrint jasperPrint;
		if (dataSource != null) {
			jasperPrint = JasperFillManager.fillReport(arquivoJasper, parameters, dataSource);
		} else {
			jasperPrint = JasperFillManager.fillReport(arquivoJasper, parameters);
		}

		return jasperPrint;
	}

	public static void gerarRelatorioDesktop(String arquivo, Collection<?> itens, Map<String, Object> parametros)
			throws Exception {
		JasperPrint print = JasperFillManager.fillReport(arquivo, parametros, new JRBeanCollectionDataSource(itens));
		JasperViewer.viewReport(print, false);
	}

	public static void gerarRelatorioDesktop(InputStream arquivo, Collection<?> itens, Map<String, Object> parametros)
			throws Exception {
		JasperPrint print = JasperFillManager.fillReport(arquivo, parametros, new JRBeanCollectionDataSource(itens));
		JasperViewer.viewReport(print, false);
	}

	public static void gerarRelatorioDesktop(JasperReport arquivo, Collection<?> itens, Map<String, Object> parametros)
			throws Exception {
		JasperPrint print = JasperFillManager.fillReport(arquivo, parametros, new JRBeanCollectionDataSource(itens));
		JasperViewer.viewReport(print, false);
	}
}
