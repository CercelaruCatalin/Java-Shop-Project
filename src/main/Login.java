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


public class Login{
	public JFrame lFrame;
	private JTextField username;
	private JTextField passwordtxt;

	
	public Login(){
		Linit();
	}


	public void Linit() {
		
	    lFrame = new JFrame();
		lFrame.getContentPane().setBackground(Color.WHITE);
		lFrame.setBounds(500, 180, 601, 480);
		lFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lFrame.getContentPane().setLayout(null);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		JLabel header = new JLabel("");
		header.setBounds(82, -31, 419, 193);
		Image head=new ImageIcon(this.getClass().getResource("/logolog.png")).getImage();
		header.setIcon(new ImageIcon(head));
		lFrame.getContentPane().add(header);
		
		JLabel lbluser = new JLabel("EMAIL");
		lbluser.setForeground(Color.BLUE);
		lbluser.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lbluser.setBounds(240, 155, 198, 32);
		lFrame.getContentPane().add(lbluser);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPassword.setBounds(213, 254, 198, 42);
		lFrame.getContentPane().add(lblPassword);
		
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.PLAIN, 30));
		username.setBounds(20, 197, 543, 47);
		lFrame.getContentPane().add(username);
		username.setColumns(10);
		
		passwordtxt = new JTextField();
		passwordtxt.setFont(new Font("Tahoma", Font.PLAIN, 30));
		passwordtxt.setColumns(10);
		passwordtxt.setBounds(20, 306, 543, 47);
		lFrame.getContentPane().add(passwordtxt);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setForeground(Color.BLUE);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					String url="jdbc:mysql://localhost:3306/p3";
					String user="root";
					String password="Defendlaptop5451";

					Connection conn=null;
					
					conn=DriverManager.getConnection(url,user,password);
					String log="Select email from users where email=? and password=?";
					String cautid="Select id from users where email=? and password=?";
					
					PreparedStatement ps2=conn.prepareStatement(cautid);
					PreparedStatement ps=conn.prepareStatement(log);
					
					String us=username.getText();
					String pass=passwordtxt.getText();
					
					ps.setString(1, us);
					ps.setString(2, pass);
					
					ps2.setString(1, us);
					ps2.setString(2, pass);
					
					ResultSet rs=ps.executeQuery();
					
					ResultSet idu=ps2.executeQuery();
					idu.next();
					
					int id = idu.getInt("id");
					Interfata.customer(id);
					
					if(rs.next()) {
						
						JOptionPane.showMessageDialog(null, "Login Sucsesfully...");
						lFrame.setVisible(false);
						Interfata window = new Interfata();
						window.frame.setVisible(true);
						
						conn.close();
					}
					else
						JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
					
				}catch(Exception e2) {System.out.print(e2);}
			}
		});
		btnLogin.setBounds(50, 383, 250, 50);
		lFrame.getContentPane().add(btnLogin);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setForeground(Color.GREEN);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnRegister.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent er) {
				try {
					lFrame.setVisible(false);
					Register rWindow = new Register();
					rWindow.rFrame.setVisible(true);
				}catch(Exception er1) {System.out.print(er1);}
			}
		});
				
		btnRegister.setBounds(313, 383, 250, 50);
		lFrame.getContentPane().add(btnRegister);
		

		

	}
	
}

