package br.com.fluentcode.jasperreports.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRXmlDataSource;

public class ReportXmlService {
	
	public byte[] generateReport() throws JRException {
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream("/jasper/xml/report_companies.jasper");

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		JasperRunManager.runReportToPdfStream(inputStream, outputStream,
				getParameters(), new JRXmlDataSource(getXmlDataSource()));

		byte[] bytes = outputStream.toByteArray();

		return bytes;
	}

	private Map<String, Object> getParameters() {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		/*
		 * A relative path cannot be used to locate the subreport file; in other
		 * words, why, if you have a report in c:\myreport\main_report.jasper,
		 * you cannot refer to the subreport just by using an expression like
		 * ..\\mysubreports\\mysubreport.jasper. Well, you cannot do this
		 * because JasperReports does not keep in memory the original location
		 * of the Jasper file that it’s working with.
		 */
		parameters.put("SUBREPORT_DIR", "/jasper/xml/");
		return parameters;
	}

	private InputStream getXmlDataSource() {
		return getClass().getClassLoader().getResourceAsStream(
				"/report_datasource.xml");
	}

}
