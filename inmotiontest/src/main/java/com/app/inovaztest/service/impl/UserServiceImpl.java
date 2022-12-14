package com.app.inovaztest.service.impl;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.app.inovaztest.model.User;
import com.app.inovaztest.service.UserService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class UserServiceImpl implements UserService{
	
	public List<User> listUser;
	
	public UserServiceImpl(List<User> listUser) {
		this.listUser = listUser;
	}

	@Override
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		
		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setColor(Color.darkGray);
		fontTitle.setSize(18);
		
		Paragraph title = new Paragraph("INMOTION - Ryo Rangga S\nList Data Users");
		document.add(title);
		
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f});
		
		tableHeader(table);
		tableData(table);
		
		document.add(table);
		document.close();
		
		
		
	}
	
	@Override
	public void tableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.gray);
		cell.setPadding(4);
		
		Font font = FontFactory.getFont(FontFactory.TIMES_BOLD);
		font.setColor(Color.white);
		
		cell.setPhrase(new Phrase("Username", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("User Id", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Detil Url", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Account", font));
		table.addCell(cell);
		
	}

	@Override
	public void tableData(PdfPTable table) {
		for (User user : listUser) {
			table.addCell(user.getLogin());
			table.addCell(String.valueOf(user.getId()));
			table.addCell(user.getUrl());
			table.addCell(user.getHtml_url());
			
		}
		
	}
	
	
	

}
