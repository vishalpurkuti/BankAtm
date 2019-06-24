import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ATM {
	String name,address,email,password,query;
	int acNO,deposit=0,withdraw=0,balance=0;
	ATM owner;
	Scanner scan=new Scanner(System.in);
	void withdraw() {
		
		
	}
	void balance()  {
		Connection con=new dbConnection().getConnection();
		Statement st;
		try {
			st = con.createStatement();
		
		System.out.println("------------------Bank AC Enquiry--------------");
		
		System.out.println("Enter AC no");
		acNO=scan.nextInt();
		scan.nextLine();
		System.out.println("Enter Your PIN:");
		password=scan.nextLine();
		ResultSet rs=st.executeQuery("select  *from bankac where acNO='"+acNO+"'");
		rs.next();
		String pass=rs.getString("password");
		if(password.contentEquals(pass))
		{
	   String name=rs.getString("name");
	   int acNo=rs.getInt("acNO");
	   int balance=rs.getInt("balance");
	   
	   System.out.println("Name:"+name);
	   System.out.println("AC NO:"+acNO);
	   System.out.println("Current Balance:"+balance);
	   System.out.println("-------------------------------------------------");
	   
		}
		else
			System.out.println("Invalid a/c no or password");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Either your a/c number or password is incorrect, confirm and try again!!!!!");
		}
	

	}
	void pinchange() throws SQLException {
		
		Connection con=new dbConnection().getConnection();
		Statement st=con.createStatement();
		
	    
	
		System.out.println("Enter a/c no or ATM no. to change its pin");
		acNO=scan.nextInt();
		ResultSet rs=st.executeQuery("select *from bankac where acNO='"+acNO+"'");
		rs.next();
		
		String pass=rs.getString("password");
		scan.nextLine();
		System.out.println("Enter old PIN:");
		password=scan.nextLine();
		if(password.equals(pass))
		{
		System.out.println("Enter new PIN:");
		password=scan.nextLine();
		query="update bankac set password='"+password+"'  where acNO='"+acNO+"'";
		st.executeUpdate(query);
		}
		else
			System.out.println("Invalid old PIN!!!");
		System.out.println("-----------CHANGE PIN----------------");
		
	}
	void statement() {
		System.out.println("STATement");
	}
//pdf
public void pdf() {
		
		Document document=new Document();
		try {
			PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream("statementSSS.pdf"));
			document.open();
			document.add(new Paragraph("Hello MF"));
			document.close();
			writer.close();
		
		}
		catch(DocumentException e) {
			e.printStackTrace();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
}
