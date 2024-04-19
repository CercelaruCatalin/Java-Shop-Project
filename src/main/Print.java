package main;

import java.sql.*;
import java.awt.Color;

import java.awt.ComponentOrientation;
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



public class Print extends Comanda{
	public JFrame pFrame;
	private JTextField nume;
	private JTextField prenume;
	private static JTextField totPlata;
	private JTextField tel;
	private JTextField localitate;
	private JTextField strada;
	private JTextField ap;
	protected static JTable tabel;
	protected static String payment;
	protected static String barCode;
	protected static String pTotal;
	protected Comanda comanda=new Comanda();
	public static int user_id;
	public static double plata2;



	/**
	 * Create the application.
	 */
	public Print() {
		pinitialize();
		}
	
	public static void update(String str, JTable table, String pay, String code,double tot) {
		totPlata.setText(str);
		tabel=table;
		payment=pay;
		barCode=code;
		pTotal=str;
		plata2=tot;
	}
	
	public static void getid(int id) {
		user_id=id;
	}
	
	
	private void pinitialize() {
		
		pFrame = new JFrame();
		pFrame.getContentPane().setBackground(Color.WHITE);
		pFrame.setBounds(500, 180, 900, 480);
		pFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pFrame.getContentPane().setLayout(null);
		Image templateimg=new ImageIcon(this.getClass().getResource("/templateapp2.png")).getImage();
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		JLabel template = new JLabel("");
		template.setBounds(-31, -102, 917, 634);
		template.setIcon(new ImageIcon(templateimg));
		pFrame.getContentPane().add(template);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(10, 46, 866, 349);
		pFrame.getContentPane().add(panel_3);
		
		JLabel lblNume = new JLabel("Nume");
		lblNume.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNume.setBounds(10, 20, 74, 32);
		panel_3.add(lblNume);
		
		nume = new JTextField();
		nume.setFont(new Font("Arial", Font.PLAIN, 24));
		nume.setColumns(10);
		nume.setBounds(235, 23, 190, 35);
		panel_3.add(nume);
		
		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPrenume.setBounds(10, 68, 126, 32);
		panel_3.add(lblPrenume);
		
		prenume = new JTextField();
		prenume.setFont(new Font("Arial", Font.PLAIN, 24));
		prenume.setColumns(10);
		prenume.setBounds(235, 71, 190, 35);
		panel_3.add(prenume);
		
		JLabel lblTotalDePlata = new JLabel("Total de plata");
		lblTotalDePlata.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTotalDePlata.setBounds(10, 306, 164, 32);
		panel_3.add(lblTotalDePlata);
		
		totPlata = new JTextField();
		totPlata.setEditable(false);
		totPlata.setFont(new Font("Arial", Font.PLAIN, 24));
		totPlata.setColumns(10);
		totPlata.setBounds(235, 306, 190, 35);
		totPlata.setText(comanda.total);
		panel_3.add(totPlata);
		
		JLabel lblNrTelefon = new JLabel("Nr. Telefon");
		lblNrTelefon.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNrTelefon.setBounds(10, 118, 126, 32);
		panel_3.add(lblNrTelefon);
		
		tel = new JTextField();
		tel.setFont(new Font("Arial", Font.PLAIN, 24));
		tel.setColumns(10);
		tel.setBounds(235, 118, 190, 35);
		panel_3.add(tel);
		
		JLabel lblLocalitate = new JLabel("Localitate");
		lblLocalitate.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLocalitate.setBounds(10, 165, 126, 32);
		panel_3.add(lblLocalitate);
		
		localitate = new JTextField();
		localitate.setFont(new Font("Arial", Font.PLAIN, 24));
		localitate.setColumns(10);
		localitate.setBounds(235, 165, 190, 35);
		panel_3.add(localitate);
		
		JLabel lblStrada = new JLabel("Strada");
		lblStrada.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblStrada.setBounds(10, 212, 126, 32);
		panel_3.add(lblStrada);
		
		strada = new JTextField();
		strada.setFont(new Font("Arial", Font.PLAIN, 24));
		strada.setColumns(10);
		strada.setBounds(235, 212, 190, 35);
		panel_3.add(strada);
		
		JLabel lblAp = new JLabel("Apartament");
		lblAp.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAp.setBounds(10, 259, 126, 32);
		panel_3.add(lblAp);
		
		ap = new JTextField();
		ap.setFont(new Font("Arial", Font.PLAIN, 24));
		ap.setColumns(10);
		ap.setBounds(235, 259, 190, 35);
		panel_3.add(ap);
		
		JLabel lblAlteSpecificatii = new JLabel("Alte specificatii\r\n");
		lblAlteSpecificatii.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblAlteSpecificatii.setBounds(530, 28, 267, 44);
		panel_3.add(lblAlteSpecificatii);
		
		JTextArea spec = new JTextArea();
		spec.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		spec.setLineWrap(true);
		spec.setWrapStyleWord(true);
		spec.setRows(5);
		spec.setFont(new Font("Arial", Font.PLAIN, 18));
		spec.setBounds(497, 82, 328, 104);
		spec.setBorder(border);
		panel_3.add(spec);
		
		JButton trimite = new JButton("SEND");
		trimite.setFont(new Font("Tahoma", Font.PLAIN, 30));
		trimite.setBackground(Color.GREEN);
		trimite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int min=1;
				int max=10000;
				int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
				
				String file="C:/Users/cerce/Downloads/comenzi/";
				String txtComanda="Comanda "+Integer.toString(random_int);
				file+=txtComanda+".pdf";
				
				comanda.total=pTotal;
				comanda.metodaPlata=payment;
				comanda.ap=ap.getText();
				comanda.nume=nume.getText();
				comanda.prenume=prenume.getText();
				comanda.loc=localitate.getText();
				comanda.nrTel=tel.getText();
				comanda.strada=strada.getText();
				comanda.desc=spec.getText();
				
				MessageFormat header = new MessageFormat("PRODUSE COMANDATE");
				MessageFormat footer = new MessageFormat("TOTAL DE PLATA: "+comanda.total+"\r\n Tip plata: "+comanda.metodaPlata);
				
				try {
					

				String url="jdbc:mysql://localhost:3306/p3";
				String user="root";
				String password="Defendlaptop5451";

				Connection conn=null;
					
				conn=DriverManager.getConnection(url,user,password);
				
				String adaug="INSERT INTO orders(user_id, name, number, email, method, address, total_products, total_price) VALUES(?,?,?,?,?,?,?,?)";	
				String delete = "Delete from cart where user_id=?;";
				String email = "Select * from users where id=?";
				String produse = "Select * from cart where user_id=?";
					
				PreparedStatement comand = conn.prepareStatement(adaug);
				PreparedStatement del = conn.prepareStatement(delete);
				PreparedStatement mail = conn.prepareStatement(email);
				PreparedStatement prod=conn.prepareStatement(produse);
				
				mail.setString(1, Integer.toString(user_id));
				
				ResultSet m = mail.executeQuery();
				m.next();
				String mf = m.getString("email");
				
				String produsetotal="";
				String numeprod;
				String cantitateprod;
				prod.setString(1, Integer.toString(user_id));
				ResultSet pr = prod.executeQuery();
				while(pr.next()){
					numeprod=pr.getString("name");
					cantitateprod=pr.getString("quantity");
					produsetotal=produsetotal+numeprod+" X ["+cantitateprod+"] ";
				}
				String numele=nume.getText()+prenume.getText();

				comand.setString(1, Integer.toString(user_id));
				comand.setString(2, numele);
				comand.setString(3, comanda.nrTel);
				comand.setString(4, mf);
				comand.setString(5, comanda.metodaPlata);
				comand.setString(6, comanda.loc);
				comand.setString(7, produsetotal);
				comand.setString(8, Double.toString(plata2));
				
				comand.executeUpdate();
				del.setString(1, Integer.toString(user_id));
				del.executeUpdate();

				
	            Document document = new Document(PageSize.A4);
	            
	            // get an instance of the PDF writer
	            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file.toString()));
	            
	            // open the document
	            document.open();
	            
				// add some content
	            Paragraph titlu = new Paragraph(txtComanda.toString(),FontFactory.getFont(FontFactory.COURIER, 30));
	            titlu.setAlignment(Element.ALIGN_CENTER);
	            document.add(titlu);
	            

	            com.itextpdf.text.Font f1=new  com.itextpdf.text.Font();
	            f1.setFamily("Free 3 of 9 Extended");
	            f1.setSize(36);
	            f1.setColor(null);
	            
	            Paragraph bar= new Paragraph(barCode,f1);
	            bar.setFont(f1);
	            bar.setAlignment(Element.ALIGN_CENTER);

	            
	            document.add(new Paragraph("\n\nNume: "+comanda.nume+";", FontFactory.getFont(FontFactory.COURIER, 22)));
	            document.add(new Paragraph("Prenume: "+comanda.prenume+";", FontFactory.getFont(FontFactory.COURIER, 22)));
	            document.add(new Paragraph("Destinatie: "+comanda.loc+", "+comanda.ap+";", FontFactory.getFont(FontFactory.COURIER, 22)));
	            document.add(new Paragraph("Nr. tel.: "+comanda.nrTel+";", FontFactory.getFont(FontFactory.COURIER, 22)));
	            document.add(new Paragraph("Alte detalii: "+comanda.desc+";", FontFactory.getFont(FontFactory.COURIER, 22)));
	            document.add(new Paragraph("Total de plata: "+comanda.total+";", FontFactory.getFont(FontFactory.COURIER, 22)));
	            document.add(new Paragraph("Metdoda de plata: "+comanda.metodaPlata+";", FontFactory.getFont(FontFactory.COURIER, 22)));
	            //document.add(bar);
	            
	            
	            tabel.print(JTable.PrintMode.NORMAL, header, footer);


	            // close the document
	            document.close();
	            
	            // close the writer
	            writer.close();
	            
	            
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
				
				
				pFrame= new JFrame("Exit");
				
				if(JOptionPane.showConfirmDialog(pFrame, "Comfirm if you want to send the delivery", "Send action",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
			
			
		});
		trimite.setBounds(497, 231, 328, 53);
		panel_3.add(trimite);
		
		JButton goBack = new JButton("BACK");
		goBack.setFont(new Font("Tahoma", Font.PLAIN, 30));
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(JOptionPane.showConfirmDialog(pFrame, "Comfirm if you want to go back", "Back",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					  pFrame.dispose();
				}
			}
		});
		goBack.setBackground(new Color(255, 0, 0));
		goBack.setBounds(497, 294, 328, 44);
		panel_3.add(goBack);
		
	}
}
