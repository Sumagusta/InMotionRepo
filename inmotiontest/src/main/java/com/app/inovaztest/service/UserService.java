package com.app.inovaztest.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;

public interface UserService {
	public void export(HttpServletResponse response) throws DocumentException, IOException;
	
	public void tableHeader(PdfPTable table);
	
	public void tableData(PdfPTable table);
}
