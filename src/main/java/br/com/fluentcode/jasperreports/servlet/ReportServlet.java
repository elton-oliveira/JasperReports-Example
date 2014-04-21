package br.com.fluentcode.jasperreports.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import br.com.fluentcode.jasperreports.service.ReportBeanService;
import br.com.fluentcode.jasperreports.service.ReportXmlService;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			
			byte[] bytes = null;
			
			if(Boolean.valueOf(request.getParameter("fromXml"))){
				 bytes = new ReportXmlService().generateReport();
			}else{
				 bytes = new ReportBeanService().generateReport();
			}
			
			//Tells the browser what type of response
			response.setContentType("application/pdf");
			//Option to save the file on disk 
			response.setHeader("Content-disposition", "attachment; filename=\"business partners.pdf\"");
			
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(bytes);
			outputStream.flush();
			outputStream.close();
			
		} catch (JRException e) {
			throw new ServletException(e);
		}

	}

}
