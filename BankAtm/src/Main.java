import java.sql.*;
import java.util.*;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;


public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, AddressException, MessagingException {
		
		Connection con=new dbConnection().getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select *from bankac");
		rs.next();
		
		Scanner sc=new Scanner(System.in);
		
		int ch;
		int op;
		
		
		System.out.println("                     _____________________________________");
		System.out.println("                    |      VISHAL RASTTIYA BANK          |");
		System.out.println("                    |       Bank for development          |");
		System.out.println("                    |_____________________________________|");
		
		System.out.println("                      Welcome to National bank of Nepal !!!");
	
		
		
		
		
	
		
		do
		{
		System.out.println("Choose any option");
		System.out.println("1.Goto Bank   2. Go to ATM   3. EXIT");
	    ch=sc.nextInt();
		bankAccount u1=new bankAccount();
		ATM a1=new ATM();
		u1.owner=a1;
		CreatePDF p=new CreatePDF();
		u1.pdf=p;
		Mail mail=new Mail();
		u1.m=mail;
		switch(ch) {
		case 1:do {
			System.out.println("CHoose: 1.Create a/c  2.Deposit    3.Withdraw    4. Transfer   5. Statement    6. back to menu");
			    op=sc.nextInt();
				switch(op) {
				case 1:u1.createAC();
				       System.out.println(" ------Congratulation your AC is Ready !!----");
					   u1.displayACcreate();
					   System.out.println("----------------------------------------------");break;
				case 2:u1.deposit();break;
				case 3:u1.withdraw();break;
				case 4:u1.transfer();break;
				case 5:u1.displayStatement();break;
								}
		}while(op<6);
		continue;
		case 2:do {
			System.out.println("CHoose: 1.Balance Enquiry  2.Deposit    3.Withdraw   4.Change PIN   5.  Statement   6. Create PDF  7 send mail  8.back to menu");
	    op=sc.nextInt();
		switch(op) {
		case 1:u1.owner.balance();break;
		case 2:u1.deposit();break;
		case 3:u1.withdraw();break;
		case 4:u1.owner.pinchange();break;
		case 5:u1.displayStatement();break;
		case 6 :u1.mailing();break;
		//case 6:u1.pdf.pdf();break;
		//case 7:u1.m.send();break;
		}
		}while(op<8);
		continue;
		case 3:System.exit(1);
		}
		}while(ch<5);
		
		
	
		
	}

	

}
