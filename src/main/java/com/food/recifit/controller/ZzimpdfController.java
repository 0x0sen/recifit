package com.food.recifit.controller;

import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.food.recifit.service.ZzimServiceImpl;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("recipe")
@Controller
public class ZzimpdfController {
	
	@Autowired
	ZzimServiceImpl service;
	//찜 pdf
	@GetMapping("")
	public void pdfzzim(String file){		
		try {			
			//1.Create File Name
			String file_name="D:\\workspace\\Test_Java\\Generate_PDF\\recipe_test.pdf";
			//2.Create Document Object
			Document document= new Document();
			//3.Get pdfwriter instance		
			PdfWriter.getInstance(document, new FileOutputStream(file_name));
			//4.open document
			document.open();
			//5.Add content
			
			Paragraph para=new Paragraph("This is testing from Chilyfacts.com");
			document.add(para);
			
			document.add(new Paragraph("  "));
			document.add(new Paragraph("  "));
			document.add(new Paragraph("  "));
			document.add(new Paragraph("  "));
			document.add(new Paragraph("  "));
			
			
			//add table
			PdfPTable table = new PdfPTable(3);
			PdfPCell c1 = new PdfPCell(new Phrase("Heading 1"));
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Heading 2"));
			table.addCell(c1);
			
			c1 = new PdfPCell(new Phrase("Heading 3"));
			table.addCell(c1);
			//첫번째 행을 헤더로 함
			table.setHeaderRows(1);
			
			table.addCell("1.0");
			table.addCell("1.1");
			table.addCell("1.2");
			table.addCell("2.1");
			table.addCell("2.2");
			table.addCell("2.3");

			document.add(table);
			
			//add image in pdf
			document.add(Image.getInstance("C:\\Users\\user\\Desktop\\image\\dog.jpg"));
								
			
			//6.close document
			document.close();
			
			System.out.println("finished");
			} catch (Exception e) {
				System.err.println(e);
			}
		}

}
