package com.food.recifit.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.food.recifit.service.PDFGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PDFExportController {

	private final PDFGeneratorService pdfGeneratorService;
	//종속성 주입완료
	public PDFExportController(PDFGeneratorService pdfGeneratorService, PDFGeneratorService pdfGeneratorService2) {
		this.pdfGeneratorService = pdfGeneratorService;		
	}
	
	@GetMapping("/recipe/pdfzzim")
	public void generatePDF(HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		log.info("통과1");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		log.info(headerValue+"");
		this.pdfGeneratorService.export(response);
	}
}
