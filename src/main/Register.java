package main;

import java.sql.*;
import java.awt.Color;

import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.text.MessageFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.plaf.TextUI;

import org.w3c.dom.Text;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class Register{
	public JFrame rFrame;
	private JTextField nametxt;
	private JTextField passwordtxt;
	private JTextField passwordtxt2;
	private JTextField teltxt;
	private JTextField emailtxt;
	private JTextField adresstxt;

	
	public Register(){
		Rinit();
	}


	public void Rinit() {
		
	    rFrame = new JFrame();
		rFrame.getContentPane().setBackground(Color.WHITE);
		rFrame.setBounds(340, 80, 1001, 680);
		rFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rFrame.getContentPane().setLayout(null);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		Image head=new ImageIcon(this.getClass().getResource("/logolog.png")).getImage();
		
		JLabel lblNewLabel = new JLabel("REGISTER PAGE");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(400, 10, 340, 75);
		rFrame.getContentPane().add(lblNewLabel);
		
		JLabel lblname = new JLabel("Name:");
		lblname.setForeground(Color.BLUE);
		lblname.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblname.setBounds(10, 187, 181, 32);
		rFrame.getContentPane().add(lblname);
		
		nametxt = new JTextField();
		nametxt.setFont(new Font("Tahoma", Font.PLAIN, 30));
		nametxt.setBounds(180, 180, 318, 47);
		rFrame.getContentPane().add(nametxt);
		nametxt.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPassword.setBounds(10, 307, 198, 42);
		rFrame.getContentPane().add(lblPassword);
		
		passwordtxt = new JTextField();
		passwordtxt.setFont(new Font("Tahoma", Font.PLAIN, 30));
		passwordtxt.setColumns(10);
		passwordtxt.setBounds(180, 305, 318, 47);
		rFrame.getContentPane().add(passwordtxt);
		
		
		JLabel lblPassword2 = new JLabel("C. Password:");
		lblPassword2.setForeground(Color.BLUE);
		lblPassword2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPassword2.setBounds(10, 418, 198, 42);
		rFrame.getContentPane().add(lblPassword2);
		
		passwordtxt2 = new JTextField();
		passwordtxt2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		passwordtxt2.setColumns(10);
		passwordtxt2.setBounds(180, 418, 318, 47);
		rFrame.getContentPane().add(passwordtxt2);
		
		JLabel lblTel = new JLabel("Nr. Tel:");
		lblTel.setForeground(Color.BLUE);
		lblTel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTel.setBounds(508, 182, 111, 42);
		rFrame.getContentPane().add(lblTel);
		
		teltxt = new JTextField();
		teltxt.setFont(new Font("Tahoma", Font.PLAIN, 30));
		teltxt.setColumns(10);
		teltxt.setBounds(612, 180, 365, 47);
		rFrame.getContentPane().add(teltxt);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Color.BLUE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEmail.setBounds(508, 307, 111, 42);
		rFrame.getContentPane().add(lblEmail);
		
		emailtxt = new JTextField();
		emailtxt.setFont(new Font("Tahoma", Font.PLAIN, 30));
		emailtxt.setColumns(10);
		emailtxt.setBounds(612, 305, 365, 47);
		rFrame.getContentPane().add(emailtxt);
		
		JLabel lblAdress = new JLabel("Adresa:");
		lblAdress.setForeground(Color.BLUE);
		lblAdress.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblAdress.setBounds(508, 418, 111, 42);
		rFrame.getContentPane().add(lblAdress);
		
		adresstxt = new JTextField();
		adresstxt.setFont(new Font("Tahoma", Font.PLAIN, 30));
		adresstxt.setColumns(10);
		adresstxt.setBounds(612, 418, 365, 47);
		rFrame.getContentPane().add(adresstxt);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setForeground(Color.GREEN);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnRegister.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(passwordtxt.getText()!=passwordtxt2.getText() && passwordtxt.getText()!="") {
					try {
						String url="jdbc:mysql://localhost:3306/p3";
						String user="root";
						String password="Defendlaptop5451";
	
						Connection conn=null;
						Statement stmt=null;
						
						String name=nametxt.getText();
						String pass=passwordtxt.getText();
						String tel=teltxt.getText();
						String adress=adresstxt.getText();
						String mail=emailtxt.getText();
						
						conn=DriverManager.getConnection(url,user,password);
						String reg="INSERT INTO `users`(name, email, password, NR_TEL, address) VALUES(?,?,?,?,?)";
						PreparedStatement ps=conn.prepareStatement(reg);
						ps.setString(1, name);
						ps.setString(2, mail);
						ps.setString(3, pass);
						ps.setString(4, tel);
						ps.setString(5, adress);
						ps.executeUpdate();

						JOptionPane.showMessageDialog(null, "Registration complete!");
						rFrame.setVisible(false);
						Login lWindow = new Login();
						lWindow.lFrame.setVisible(true);
						conn.close();
							
						}catch(Exception e2) {System.out.print(e2);}
					}
				else {
					JOptionPane.showMessageDialog(null, "The passwords don't match!");
				}
				}
			});
		
		btnRegister.setBounds(370, 571, 370, 62);
		rFrame.getContentPane().add(btnRegister);
		

	}
	
}

