package com.food.recifit.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import com.food.recifit.domain.Zzim;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

import lombok.extern.slf4j.Slf4j;


public class ZzimPDFExporter {
   private Zzim zzim;

   public ZzimPDFExporter(Zzim zzim) {
      this.zzim = zzim;
   }

   public void export(HttpServletResponse response, int num) throws DocumentException, IOException {
      
      //Pdf형식의 document를 생성한다.
      Rectangle pageSize = PageSize.A4;
       Document document = new Document(pageSize);
       document.setMargins(50, 50, 80, 100);
      
      //PdfWriter를 취득한다.
      PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
      
      //document Open한다.
      document.open();
      XMLWorkerHelper helper = XMLWorkerHelper.getInstance();
      
      //이미지 생성
      String filename = "C:/upload/"+zzim.getZzim_savedfile();
      Image image = Image.getInstance(filename);
      image.scaleAbsolute(350, 250);
      image.setAbsolutePosition(120, 400);
      image.setSpacingBefore(10f);
      image.setSpacingAfter(10f);
      document.add(image);
      
      
      
      
      
      
      
      
///여기 밑에 경로 자신의 img 경로로 수중해줘야해요      
      
      
      
      
      //pdf 배경
      // Create an Image object
      Image background = Image.getInstance("D:/workspace/Test_Spring/recifit/src/main/resources/static/image/pdfbackground1.jpg");
//		수정필요							      C:/Java/workspace/recifit/src/main/resources/static/image/pdfbackground1.jpg
//										  D:/workspace/Test_Spring/recifit/src/main/resources/static/image
      // Set the position of the image to the bottom left corner of the page
      background.scaleAbsolute(pageSize.getWidth(), pageSize.getHeight());
      background.setAbsolutePosition(0, 0);
      // Get the PdfContentByte object for the page
      PdfContentByte canvas = writer.getDirectContentUnder();
      // Add the image to the background
      canvas.addImage(background);      
      
      // CSS
      CSSResolver cssResolver = new StyleAttrCSSResolver();
      CssFile cssFile = helper.getCSS(new FileInputStream("D:/workspace/Test_Spring/recifit/src/main/resources/static/css/pdf.css"));
      //수정필요
      cssResolver.addCss(cssFile);
      
      // HTML, 폰트 설정
      XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);

      fontProvider.register("D:/workspace/Test_Spring/recifit/src/main/resources/static/font/malgun.ttf", "MalgunGothic"); // MalgunGothic은 alias,
      //수정필요
      CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);

      HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
      htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
      
      
      
      
 
    ///여기 밑에 경로 자신의 여기까지 css tml 폰트 설정     
      
      
      
      // Pipelines
      PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
      HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
      CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
       
      XMLWorker worker = new XMLWorker(css, true);
      XMLParser xmlParser = new XMLParser(worker, Charset.forName("UTF-8"));
      
      String htmlStr = "<html><head><body style='font-family: MalgunGothic;'>"
              + "<h2>"+zzim.getZzim_name()+"</h2><p>    </p>"
              + "</body></head></html>";
//      String htmlStr = "<p style='font-family: MalgunGothic;'><span> <h3>" + zzim.getZzim_name() + "</h3></span></p>";
//      htmlStr += "<p style='font-family: MalgunGothic;'> <span> <h6>재료: " + zzim.getZzim_need() + "</h6></span></p>";
//      htmlStr += "<p style='font-family: MalgunGothic;'> <span> 만드는 법: " + zzim.getZzim_howto().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "") + "</span></p>";
//      
      StringReader strReader = new StringReader(htmlStr);
      xmlParser.parse(strReader);
      
      //표 생성
      PdfPTable table = new PdfPTable(1);      
      table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
      PdfPCell cell1 = new PdfPCell(new Phrase("  "));
      cell1.setBorder(Rectangle.NO_BORDER);
      table.addCell(cell1);

      PdfPCell cell2 = new PdfPCell(new Phrase("  "));
      cell2.setBorder(Rectangle.NO_BORDER);
      table.addCell(cell2);
      
      PdfPCell cell3 = new PdfPCell(new Phrase("  "));
      cell3.setBorder(Rectangle.NO_BORDER);
      table.addCell(cell3);
      
      PdfPCell cell4 = new PdfPCell(new Phrase("  "));
      cell4.setBorder(Rectangle.NO_BORDER);
      table.addCell(cell4);
      
      PdfPCell cell5 = new PdfPCell(new Phrase("  "));
      cell5.setBorder(Rectangle.NO_BORDER);
      table.addCell(cell5);
      
      PdfPCell cell6 = new PdfPCell(new Phrase("  "));
      cell6.setBorder(Rectangle.NO_BORDER);
      table.addCell(cell6);
      
      PdfPCell cell7 = new PdfPCell(new Phrase("  "));
      cell7.setBorder(Rectangle.NO_BORDER);
      table.addCell(cell7);
      
      PdfPCell cell8 = new PdfPCell(new Phrase("  "));
      cell8.setBorder(Rectangle.NO_BORDER);
      table.addCell(cell8);
      
      PdfPCell cell9 = new PdfPCell(new Phrase("  "));
      cell9.setBorder(Rectangle.NO_BORDER);
      table.addCell(cell9);
      
      PdfPCell cel1 = new PdfPCell(new Phrase("  "));
      cel1.setBorder(Rectangle.NO_BORDER);
      table.addCell(cel1);
      
      PdfPCell cel2 = new PdfPCell(new Phrase("  "));
      cel2.setBorder(Rectangle.NO_BORDER);
      table.addCell(cel2);
      
      PdfPCell cel3 = new PdfPCell(new Phrase("  "));
      cel3.setBorder(Rectangle.NO_BORDER);
      table.addCell(cel3);
      
      PdfPCell cel4 = new PdfPCell(new Phrase("  "));
      cel4.setBorder(Rectangle.NO_BORDER);
      table.addCell(cel4);
      
      PdfPCell cel5 = new PdfPCell(new Phrase("  "));
      cel5.setBorder(Rectangle.NO_BORDER);
      table.addCell(cel5);
      
      PdfPCell cel6 = new PdfPCell(new Phrase("  "));
      cel6.setBorder(Rectangle.NO_BORDER);
      table.addCell(cel6);
      
      PdfPCell cel7 = new PdfPCell(new Phrase("  "));
      cel7.setBorder(Rectangle.NO_BORDER);
      table.addCell(cel7);
      
      PdfPCell cel8 = new PdfPCell(new Phrase("  "));
      cel8.setBorder(Rectangle.NO_BORDER);
      table.addCell(cel8);
      
      PdfPCell cel9 = new PdfPCell(new Phrase("  "));
      cel9.setBorder(Rectangle.NO_BORDER);
      table.addCell(cel9);
      
      PdfPCell ce1 = new PdfPCell(new Phrase("  "));
      ce1.setBorder(Rectangle.NO_BORDER);
      table.addCell(ce1);
      
      PdfPCell ce2 = new PdfPCell(new Phrase("  "));
      ce2.setBorder(Rectangle.NO_BORDER);
      table.addCell(ce2);
      
      PdfPCell ce3 = new PdfPCell(new Phrase("  "));
      ce3.setBorder(Rectangle.NO_BORDER);
      table.addCell(ce3);
      

      table.setTotalWidth(200);
      table.setLockedWidth(true);
      
      // 빈 셀을 추가
     document.add(table);

   
      String tmlStr = "<html><head><body style='font-family: MalgunGothic;'>"
              + "<h3>재료 : "+ zzim.getZzim_need()+"</h3>"
              + "<p>" +zzim.getZzim_howto()+"</p>"
              + "</body></head></html>";
      StringReader trReader = new StringReader(tmlStr);
      xmlParser.parse(trReader);      
      
      
      document.close();
      
   }
}