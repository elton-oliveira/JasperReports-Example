package br.com.fluentcode.jasperreports.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRXmlDataSource;

public class ReportUtil {
	
	public byte[] generateReport() throws JRException {
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream("/relatorios/report_companies.jasper");

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		JasperRunManager.runReportToPdfStream(inputStream, outputStream,
				getParameters(), new JRXmlDataSource(getXmlDataSource()));

		byte[] bytes = outputStream.toByteArray();

		return bytes;
	}

	private Map<String, Object> getParameters() {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", "/relatorios/");
		return parameters;
	}

	private InputStream getXmlDataSource() {
		return getClass().getClassLoader().getResourceAsStream(
				"/report_datasource.xml");
	}

}
