package com.food.recifit.service;

import java.awt.Font;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;





@Service
public class PDFGeneratorService {
	public void export(HttpServletResponse response) throws IOException{
		Document document = new Document(PageSize.A4);
		//document : 넣는것. response.getOutputStream(): 어디에 쓸건지
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();

		//글꼴
		Font fontTitle = FontFactory.getFont(FontFactory.);
		fontTitle.setSize(18);
		//단락 Paragraph
		Paragraph paragraph = new Paragraph("This is a title.한글이 되니", fontTitle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
		fontParagraph.setSize(12);

		Paragraph paragraph2 = new Paragraph("This is a paragraph.", fontParagraph);
		paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
		
		document.add(paragraph);
		document.add(paragraph2);
		document.close();
		
	}
}
