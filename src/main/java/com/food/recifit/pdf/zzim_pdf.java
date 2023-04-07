package com.food.recifit.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class zzim_pdf {

	public static void main(String[] args) {
		
		try {
			
		String file_name="C:\\upload\\Zzim_PDF\\zzim_test.pdf";
		Document document=new Document();
		PdfWriter.getInstance(document, new FileOutputStream(file_name));
		document.open();
		//DB연결
		DBConnection obJDBConnection=new DBConnection();
		Connection connection = obJDBConnection.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String query="select * from codes";
		ps=connection.prepareStatement(query);
		rs=ps.executeQuery();
		
		while(rs.next()) {
			Paragraph para=new Paragraph(rs.getString("sl_no")+" "+rs.getString("name"));
			document.add(para);
			document.add(new Paragraph(" "));
		
		}
		
		document.close();
		
		} catch (Exception e) {
			System.err.println(e);
		} 
	
	}

}
