import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class CreatePDF{
	CreatePDF pdf;
	String pass,pin;
	int acNO;
	public void pdf() throws SQLException {
		Scanner scan=new Scanner(System.in);
		Connection con=new dbConnection().getConnection();
		Statement st=con.createStatement();
		
		
		// System.out.println("Enter acNO");
	   //  acNO=scan.nextInt();
	   //  scan.nextLine();
	  //   System.out.println("Enter PIN:");
	     pass=scan.nextLine();
	     ResultSet rs1=st.executeQuery("select *from bankac where acNO='"+acNO+"' ");
	     rs1.next();
	     String name=rs1.getString("name");
	     int acno=rs1.getInt("acNO");
	     pin=rs1.getString("password");
	     
	     if(pass.equals(pin))
	     {
		Document document=new Document();
		try {
			
			PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream("Statementfinall.pdf"));
			document.open();
			
			Image image=Image.getInstance("logo.png");
			image.setAbsolutePosition(0f,650f);
			document.add(image);
			
	      
			document.add(new Paragraph("   "));
			document.add(new Paragraph("   "));
			document.add(new Paragraph("   "));

			document.add(new Paragraph("   "));
			document.add(new Paragraph("   "));
			document.add(new Paragraph("   "));
			

			document.add(new Paragraph("   "));
			document.add(new Paragraph("   "));
			document.add(new Paragraph("   "));
			
			document.add(new Paragraph("                                                          BANK STATEMENT"));
			document.add(new Paragraph("   "));
			document.add(new Paragraph("NAME :"+name));
			document.add(new Paragraph("Account Number   :"+acno));
			document.add(new Paragraph(new Date().toString()));
			document.add(new Paragraph("   "));
			document.add(new Paragraph("                                                   THE DETAIL OF TRANSACTION "));
			document.add(new Paragraph("-------------------------------------------------------------------------------------------------------------------------------"));
			
			 PdfPTable table = new PdfPTable(4);
		     
		
		      PdfPCell c1 = new PdfPCell(new Phrase("DEPOSIT"));
		      c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		      table.addCell(c1);
	
		      
		      c1 = new PdfPCell(new Phrase("WITHDRAW"));
		      c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		      table.addCell(c1);

		      c1 = new PdfPCell(new Phrase("DATE/TIME"));
		      c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		      table.addCell(c1);
		      
		      c1 = new PdfPCell(new Phrase("Balance"));
		      c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		      table.addCell(c1);
		     
		  
		    
		     int i=0;
				 ResultSet rs2=st.executeQuery("select *from statement where ac='"+acNO+"' order by DateTime desc ");
				 while(rs2.next() && i<=100)
		    		{
		    	
                    
		    		table.addCell(rs2.getString("deposit"));
		    		table.addCell(rs2.getString("withdraw"));
		    		table.addCell(rs2.getString("DateTime"));
		    		table.addCell(rs2.getString("balance"));
				     
				     
		    		
		  
		     }
		     document.add(table);
		     document.add(new Paragraph("-------------------------------------------------------------------------------------------------------------------------------"));
			
			
			
			
			
			
		     Image image1=Image.getInstance("foot.png");
				image1.setAbsolutePosition(0,0);
				document.add(image1);
			
			document.close();
			writer.close();
			
			
		}
	     

		catch(DocumentException e) {
			e.printStackTrace();
		} 
		catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	     }
	     else {
	    	 System.out.println("Invalid PIN");
	     }
		
	}
	
	
}
