package com.food.recifit.controller;

import java.io.FileInputStream;
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

@Slf4j
public class ZzimPDFExporter {
   private Zzim zzim;

   public ZzimPDFExporter(Zzim zzim) {
      this.zzim = zzim;
   }

   public void export(HttpServletResponse response, int num) throws DocumentException, IOException {
      
      //Pdf형식의 document를 생성한다.
      Document document = new Document(new Rectangle(612, 792));
      
      //PdfWriter를 취득한다.
      PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
      
      //document Open한다.
      document.open();
      XMLWorkerHelper helper = XMLWorkerHelper.getInstance();
      
      //이미지 생성
      String filename = "C:/upload/"+zzim.getZzim_savedfile();
      Image image = Image.getInstance(filename);
      image.scaleAbsolute(400f, 300f);
      image.setAbsolutePosition(100, 400);
      image.setSpacingBefore(10f);
      image.setSpacingAfter(10f);
      document.add(image);
      
      //pdf 배경
      // Create an Image object
      Image background = Image.getInstance("C:/Java/workspace/recifit/src/main/resources/static/image/pdfbackground.jpg");
      // Set the position of the image to the bottom left corner of the page
      background.setAbsolutePosition(0, 0);
      // Get the PdfContentByte object for the page
      PdfContentByte canvas = writer.getDirectContentUnder();
      // Add the image to the background
      canvas.addImage(background);
      
      
      // CSS
      CSSResolver cssResolver = new StyleAttrCSSResolver();
      CssFile cssFile = helper.getCSS(new FileInputStream("C:/Java/workspace/recifit/src/main/resources/static/css/pdf.css"));
      cssResolver.addCss(cssFile);
      
      // HTML, 폰트 설정
      XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);


      fontProvider.register("C:/Java/workspace/recifit/src/main/resources/static/font/malgun.ttf", "MalgunGothic"); // MalgunGothic은 alias,

      
      
      CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);

      HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
      htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
      
      // Pipelines
      PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
      HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
      CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
       
      XMLWorker worker = new XMLWorker(css, true);
      XMLParser xmlParser = new XMLParser(worker, Charset.forName("UTF-8"));
      
      String htmlStr = "<p style='font-family: MalgunGothic;'><span> <h3>제목: " + zzim.getZzim_name() + "</h3></span></p>";
      htmlStr += "<p style='font-family: MalgunGothic;'> <span> 재료: " + zzim.getZzim_need() + "</span></p>";
      htmlStr += "<p style='font-family: MalgunGothic;'> <span> 만드는 법: " + zzim.getZzim_howto().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "") + "</span></p>";
      htmlStr += "<p style='font-family: MalgunGothic;'> <span> 레시피 아이콘: " + zzim.getZzim_icon() + "</span></p>";
      
      StringReader strReader = new StringReader(htmlStr);
      xmlParser.parse(strReader);
      
      
//      PdfPTable table = new PdfPTable(5);
//      table.setWidthPercentage(100);
//      table.setSpacingBefore(15);
      
//      writeTableHeader(table);
//      writeTableData(table);
      
//      document.add(table);
      
      document.close();
      
   }
}