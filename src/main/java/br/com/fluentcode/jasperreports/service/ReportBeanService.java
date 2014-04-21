package br.com.fluentcode.jasperreports.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.fluentcode.jasperreports.dto.Contato;
import br.com.fluentcode.jasperreports.dto.Empresa;

public class ReportBeanService {

	public byte[] generateReport() throws JRException {
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream("/jasper/bean/report_companies.jasper");

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		JasperRunManager.runReportToPdfStream(inputStream, outputStream,
				getParameters(), new JRBeanCollectionDataSource(getEmpresas()));

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
		parameters.put("SUBREPORT_DIR", "/jasper/bean/");
		/*
		 * Using the same master report datasource in subreport cause the effect
		 * of loosing the first row in the subreport, because the subreport will
		 * move the record pointer in the data source.
		 * 
		 * A data source is a consumable object that is usable for feeding a
		 * report only once. So the master report should send to the report 
		 * new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource($P{EMPRESAS})
		 * and not $P{REPORT_DATA_SOURCE}
		 */
		parameters.put("EMPRESAS", getEmpresas());
		return parameters;
	}

	private Collection<Empresa> getEmpresas() {
		Collection<Empresa> empresas = new ArrayList<Empresa>();

		Empresa e = new Empresa();
		e.setNome("Mitsubishi");
		Collection<Contato> contatos = new ArrayList<Contato>();
		Contato c = new Contato();
		c.setNome("Vlair");
		c.setCargo("Gerente de vendas");
		c.setTelefone("(11) 3222-9991");
		contatos.add(c);
		c = new Contato();
		c.setNome("Vanessa Brien");
		c.setCargo("Gerente de qualidade");
		c.setTelefone("(11) 3222-9992");
		contatos.add(c);
		e.setContatos(contatos);
		empresas.add(e);

		e = new Empresa();
		e.setNome("General Motors");
		contatos = new ArrayList<Contato>();
		c = new Contato();
		c.setNome("Erika Sutien");
		c.setCargo("Gerente de Relacionamento");
		c.setTelefone("(11) 3333-9991");
		contatos.add(c);
		c = new Contato();
		c.setNome("Pamela Lucena");
		c.setCargo("Gerente de Projetos");
		c.setTelefone("(11) 3333-9992");
		contatos.add(c);
		e.setContatos(contatos);
		empresas.add(e);

		return empresas;
	}
}
