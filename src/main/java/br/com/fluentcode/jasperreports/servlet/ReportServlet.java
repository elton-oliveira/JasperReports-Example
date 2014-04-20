package br.com.fluentcode.jasperreports.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			final URL urlArquivoJasper = getClass().getClassLoader()
					.getResource("/relatorios/report_companies.jasper");

			final JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(urlArquivoJasper);

			final JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, getPameters(), new JRXmlDataSource(
							getXmlDataSource()));

			//Sends the report to the browser
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

	private Map<String, Object> getPameters() {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", "/relatorios/");
		return parameters;
	}

	private InputStream getXmlDataSource() {
		return getClass().getClassLoader().getResourceAsStream(
				"report_datasource.xml");
	}

}
