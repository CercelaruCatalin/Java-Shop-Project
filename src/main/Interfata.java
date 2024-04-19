package main;

import java.sql.*;
import java.awt.EventQueue;



import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Element;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.jar.Attributes.Name;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Canvas;
import java.awt.Panel;
import java.awt.Window;

public class Interfata {

	public JFrame frame;
	public JTable table;
	public JTextField txtBarCode;
	private JTextField txtTax;
	private JTextField txtSubTotal;
	public JTextField txtTotal;
	private JTextField txtDisplay;
	private JTextField txtChange;
	public JComboBox Payment;
	public Login lWindow;
	public static int user_id;
	public static double plata1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login lWindow = new Login();
					lWindow.lFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Interfata() {
		initialize();
	}
	
	public static void customer(int nr) {
		user_id=nr;
	}
	
	public void updtabel() {
		try {
		String urls="jdbc:mysql://localhost:3306/p3";
		String users="root";
		String passwords="Defendlaptop5451";

		Connection conns=null;
		
		conns=DriverManager.getConnection(urls,users,passwords);
		
		String tabels = "Select * from cart WHERE user_id=?";
		
		PreparedStatement tables = conns.prepareStatement(tabels);
		
		tables.setString(1, Integer.toString(user_id));
		ResultSet afisare = tables.executeQuery();
		DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
		RecordTable.setRowCount(0);
		
		while(afisare.next()) {
			String namep = afisare.getString("name");
			String price = afisare.getString("price");
			String cantitate = afisare.getString("quantity");
			String tbData[] = {namep,cantitate,price};
			DefaultTableModel model=(DefaultTableModel) getTable().getModel();
			model.addRow(tbData);
			costProdus();
		}
		conns.close();
		}catch(Exception e2) {System.out.print(e2);}
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	
	//====================Functii====================
	
	public void costProdus(){
		double sum=0;
		double tax=9;
		double produs=0;
		
		for(int i=0; i<getTable().getRowCount(); i++) {
			produs=Double.parseDouble(getTable().getValueAt(i, 2).toString())*Double.parseDouble(getTable().getValueAt(i, 1).toString());
			sum=sum+produs;
			}
		//...................................................................................
	    //String cart_query =  "SELECT * FROM `cart` WHERE user_id = '$user_id'";
		//String fetch_cart = mysqli_fetch_assoc(cart_query);
		
		
		txtSubTotal.setText(Double.toString(sum));
		double cTotal=Double.parseDouble(txtSubTotal.getText());
		double cTax=(cTotal*tax)/100;
		String iTaxTotal=String.format("%.2f RON", cTax);
		txtTax.setText(iTaxTotal);
		
		String iSubTotal=String.format("%.2f RON", cTotal);
		txtSubTotal.setText(iSubTotal);
		
		String iTotal=String.format("%.2f RON", cTotal+cTax);
		txtTotal.setText(iTotal);
		plata1=cTotal+cTax;
		
		String BarCode=String.format("Total is %.2f", cTotal+cTax);
		txtBarCode.setText(BarCode);
	}
	
	//====================Functii====================
	
	public void change(){
		double sum=0;
		double tax=9;
		double cash=Double.parseDouble(txtDisplay.getText());
		double produs=0;
		
		for(int i=0; i<getTable().getRowCount(); i++) {
			produs=Double.parseDouble(getTable().getValueAt(i, 2).toString())*Double.parseDouble(getTable().getValueAt(i, 1).toString());
			sum=sum+produs;
		}
		
		double cTax=(tax*sum)/100;
		double cChange=(cash-(sum+cTax));
		String changeGiven=String.format("%.2f RON", cChange);
		txtChange.setText(changeGiven);
		
	}
	
	
	//====================Functii====================
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(500, 180, 1450, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, null, null));
		panel_2.setBounds(20, 445, 1382, 180);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBackground(Color.WHITE);
		panel_3_1.setLayout(null);
		panel_3_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		panel_3_1.setBounds(460, 10, 448, 161);
		panel_2.add(panel_3_1);
		
		JButton btnclear = new JButton("C");
		btnclear.setBounds(393, 61, 45, 34);
		panel_3_1.add(btnclear);
		btnclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDisplay.setText(null);
				txtChange.setText(null);
				
			}
		});
		btnclear.setBackground(Color.GREEN);
		btnclear.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblCash = new JLabel("DISPLAY CASH");
		lblCash.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCash.setBounds(10, 58, 167, 32);
		panel_3_1.add(lblCash);
		
		txtChange = new JTextField();
		txtChange.setEditable(false);
		txtChange.setFont(new Font("Arial", Font.PLAIN, 24));
		txtChange.setColumns(10);
		txtChange.setBounds(235, 112, 203, 35);
		panel_3_1.add(txtChange);
		
		JLabel lblChange = new JLabel("CHANGE");
		lblChange.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblChange.setBounds(10, 112, 126, 32);
		panel_3_1.add(lblChange);
		
		JLabel lblPayment = new JLabel("PAY METHOD");
		lblPayment.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPayment.setBounds(10, 10, 155, 32);
		panel_3_1.add(lblPayment);
		
		JComboBox Payment_1 = new JComboBox();
		Payment_1.setBackground(Color.WHITE);
		Payment_1.setModel(new DefaultComboBoxModel(new String[] {"", "Cash", "Visa Card", "Master Card"}));
		Payment_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		Payment_1.setBounds(235, 18, 203, 29);
		panel_3_1.add(Payment_1);
		
		txtDisplay = new JTextField();
		txtDisplay.setFont(new Font("Arial", Font.PLAIN, 24));
		txtDisplay.setColumns(10);
		txtDisplay.setBounds(235, 61, 203, 35);
		panel_3_1.add(txtDisplay);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setBackground(Color.WHITE);
		panel_3_2.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, null, null));
		panel_3_2.setBounds(918, 10, 440, 161);
		panel_2.add(panel_3_2);
		panel_3_2.setLayout(null);
		
		JButton btnPrint = new JButton("SEND");
		btnPrint.setBounds(10, 10, 219, 68);
		btnPrint.setBackground(Color.YELLOW);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Print pwindow = new Print();
				String tot=txtTotal.getText();
				String pay=Payment_1.getSelectedItem().toString();
				Print.update(tot,table,pay,txtBarCode.getText(), plata1);
				Print.getid(user_id);
				pwindow.pFrame.setVisible(true);
								
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel_3_2.add(btnPrint);
		
		JButton btnReset = new JButton("RESET");
		btnReset.setBounds(239, 10, 191, 68);
		btnReset.setBackground(Color.GRAY);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtDisplay.setText(null);
				txtChange.setText(null);
				txtTax.setText(null);
				txtSubTotal.setText(null);
				txtTotal.setText(null);
				txtBarCode.setText(null);
				
				try {
					String url="jdbc:mysql://localhost:3306/p3";
					String user="root";
					String password="Defendlaptop5451";
	
					Connection conn=null;
					
					conn=DriverManager.getConnection(url,user,password);
					String res = "Delete from cart where user_id=?;";
					
					PreparedStatement reset = conn.prepareStatement(res);
					reset.setString(1, Integer.toString(user_id));

					reset.executeUpdate();
				}catch(Exception e2) {System.out.print(e2);}
				
				DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
				RecordTable.setRowCount(0);
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel_3_2.add(btnReset);
		
		JButton btnRemove = new JButton("REMOVE ITEM");
		btnRemove.setBounds(10, 88, 219, 68);
		btnRemove.setBackground(Color.ORANGE);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) getTable().getModel();
				int RemoveItem = getTable().getSelectedRow();
				int i=0;
				if(RemoveItem >= 0) {
					try {
						String url="jdbc:mysql://localhost:3306/p3";
						String user="root";
						String password="Defendlaptop5451";
						
						Connection conn=null;
						conn=DriverManager.getConnection(url,user,password);
						
						String delete = "Delete from cart where user_id=? and name=?";
						String nume = (getTable().getValueAt(RemoveItem, 0).toString());
						PreparedStatement del = conn.prepareStatement(delete);
						del.setString(1, Integer.toString(user_id));
						del.setString(2, nume);

						del.executeUpdate();
						model.removeRow(RemoveItem);
					}catch(Exception e2) {System.out.print(e2);}
				}
				
				costProdus();
				
				if(Payment_1.getSelectedItem().equals("Cash")) {
					change();
				}
				else {
					txtChange.setText("");
					txtDisplay.setText("");
				}
				
				
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel_3_2.add(btnRemove);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setBounds(239, 88, 191, 68);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame= new JFrame("Exit");
				
				if(JOptionPane.showConfirmDialog(frame, "Comfirm if you want to Exit", "Point of Sale",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setForeground(Color.BLACK);
		btnExit.setBackground(Color.RED);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel_3_2.add(btnExit);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, null, null));
		panel_3.setBounds(10, 10, 440, 161);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TVA");
		lblNewLabel.setBounds(10, 10, 74, 32);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_3.add(lblNewLabel);
		
		txtTax = new JTextField();
		txtTax.setBounds(235, 13, 177, 35);
		txtTax.setEditable(false);
		txtTax.setFont(new Font("Arial", Font.PLAIN, 24));
		panel_3.add(txtTax);
		txtTax.setColumns(10);
		
		JLabel lblSubtotal = new JLabel("SUBTOTAL");
		lblSubtotal.setBounds(10, 58, 126, 32);
		lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_3.add(lblSubtotal);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setBounds(235, 61, 177, 35);
		txtSubTotal.setEditable(false);
		txtSubTotal.setFont(new Font("Arial", Font.PLAIN, 24));
		txtSubTotal.setColumns(10);
		panel_3.add(txtSubTotal);
		
		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setBounds(10, 112, 126, 32);
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_3.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(235, 112, 177, 35);
		txtTotal.setEditable(false);
		txtTotal.setFont(new Font("Arial", Font.PLAIN, 24));
		txtTotal.setColumns(10);
		panel_3.add(txtTotal);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		panel_1.setBounds(647, 11, 755, 410);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		try {
			
			String url="jdbc:mysql://localhost:3306/p3";
			String user="root";
			String password="Defendlaptop5451";

			Connection conn=null;
			
			conn=DriverManager.getConnection(url,user,password);
			
			String adaug="INSERT INTO `cart`(user_id, name, price, quantity) VALUES(?,?,?,?)";
			
			String produs = "Select name, price From produse Where name=?";
			
			String update="UPDATE cart SET quantity=? WHERE name=? and user_id=?";
			
			String caut="Select * From cart WHERE name=? and user_id=?";
			
			String cantitate="Select quantity From cart WHERE name=? and user_id=?";
			
			String tabel = "Select * from cart WHERE user_id=?";
			
			PreparedStatement cautps = conn.prepareStatement(caut);
			PreparedStatement addps = conn.prepareStatement(adaug);
			PreparedStatement updps = conn.prepareStatement(update);
			PreparedStatement cant = conn.prepareStatement(cantitate);
			PreparedStatement prod = conn.prepareStatement(produs);
			
			PreparedStatement table = conn.prepareStatement(tabel);
			table.setString(1, Integer.toString(user_id));
			
			
			JButton btn7up = new JButton("");
			btn7up.setForeground(new Color(255, 255, 255));
			btn7up.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						cautps.setString(1, "7up");
						cautps.setString(2, Integer.toString(user_id));
						
						prod.setString(1, "7up");
						
						cant.setString(1, "7up");
						cant.setString(2, Integer.toString(user_id));
						
						ResultSet q=cant.executeQuery();
						q.next();
						
						ResultSet p=prod.executeQuery();
						p.next();
						
						ResultSet rs=cautps.executeQuery();
						if(rs.next()) {
							
							int qf = q.getInt(1)+1;
							updps.setString(1, Integer.toString(qf));
							updps.setString(2, p.getString("name"));
							updps.setString(3, Integer.toString(user_id));
							updps.executeUpdate();
							

							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
							}
						
						else {
							double pret=p.getDouble("price");
							String name = p.getString("name");
							
							addps.setString(1, Integer.toString(user_id));
							addps.setString(2, name);
							addps.setString(3, Double.toString(pret));
							addps.setString(4, "1");
							
							addps.executeUpdate();
							
							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();

						}
						
					}catch(Exception e2) {System.out.print(e2);}
					
				}
			});
			Image up7=new ImageIcon(this.getClass().getResource("/7up.png")).getImage();
			btn7up.setIcon(new ImageIcon(up7));
			btn7up.setFont(new Font("Tahoma", Font.BOLD, 48));
			btn7up.setBounds(10, 10, 140, 124);
			panel_1.add(btn7up);
			
			JButton btnMms = new JButton("");
			btnMms.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						cautps.setString(1, "mms");
						cautps.setString(2, Integer.toString(user_id));
						
						prod.setString(1, "mms");
						
						cant.setString(1, "mms");
						cant.setString(2, Integer.toString(user_id));
						
						ResultSet q=cant.executeQuery();
						q.next();
						
						ResultSet p=prod.executeQuery();
						p.next();
						
						ResultSet rs=cautps.executeQuery();
						if(rs.next()) {
							
							int qf = q.getInt(1)+1;
							updps.setString(1, Integer.toString(qf));
							updps.setString(2, p.getString("name"));
							updps.setString(3, Integer.toString(user_id));
							updps.executeUpdate();
							
							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
							}
						
						else {
							double pret=p.getDouble("price");
							String name = p.getString("name");
							
							addps.setString(1, Integer.toString(user_id));
							addps.setString(2, name);
							addps.setString(3, Double.toString(pret));
							addps.setString(4, "1");
							
							addps.executeUpdate();
							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();

						}
					
					}catch(Exception e2) {System.out.print(e2);}
					
				}
			});
			Image mms=new ImageIcon(this.getClass().getResource("/mms.png")).getImage();
			btnMms.setIcon(new ImageIcon(mms));
			btnMms.setFont(new Font("Tahoma", Font.BOLD, 48));
			btnMms.setBounds(10, 144, 140, 124);
			panel_1.add(btnMms);
			
			JButton btnBiscuiti = new JButton("");
			btnBiscuiti.setForeground(Color.BLACK);
			btnBiscuiti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						cautps.setString(1, "biscuiti");
						cautps.setString(2, Integer.toString(user_id));
						
						prod.setString(1, "biscuiti");
						
						cant.setString(1, "biscuiti");
						cant.setString(2, Integer.toString(user_id));
						
						ResultSet q=cant.executeQuery();
						q.next();
						
						ResultSet p=prod.executeQuery();
						p.next();
						
						ResultSet rs=cautps.executeQuery();
						if(rs.next()) {
							
							int qf = q.getInt(1)+1;
							updps.setString(1, Integer.toString(qf));
							updps.setString(2, p.getString("name"));
							updps.setString(3, Integer.toString(user_id));
							updps.executeUpdate();
							
							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
							}
						
						else {
							double pret=p.getDouble("price");
							String name = p.getString("name");
							
							addps.setString(1, Integer.toString(user_id));
							addps.setString(2, name);
							addps.setString(3, Double.toString(pret));
							addps.setString(4, "1");
							
							addps.executeUpdate();

							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
						}
					
					}catch(Exception e2) {System.out.print(e2);}
					
				}
			});
			Image biscuiti=new ImageIcon(this.getClass().getResource("/biscuiti.png")).getImage();
			btnBiscuiti.setIcon(new ImageIcon(biscuiti));
			btnBiscuiti.setFont(new Font("Tahoma", Font.BOLD, 48));
			btnBiscuiti.setBounds(10, 278, 140, 124);
			panel_1.add(btnBiscuiti);
			
			JButton btnPepsi = new JButton("");
			btnPepsi.setForeground(new Color(255, 255, 255));
			btnPepsi.setBackground(new Color(0, 0, 0));
			btnPepsi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						cautps.setString(1, "pepsi");
						cautps.setString(2, Integer.toString(user_id));
						
						prod.setString(1, "pepsi");
						
						cant.setString(1, "pepsi");
						cant.setString(2, Integer.toString(user_id));
						
						ResultSet q=cant.executeQuery();
						q.next();
						
						ResultSet p=prod.executeQuery();
						p.next();
						
						ResultSet rs=cautps.executeQuery();
						if(rs.next()) {
							
							int qf = q.getInt(1)+1;
							updps.setString(1, Integer.toString(qf));
							updps.setString(2, p.getString("name"));
							updps.setString(3, Integer.toString(user_id));
							updps.executeUpdate();
							
							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
							}
						
						else {
							double pret=p.getDouble("price");
							String name = p.getString("name");
							
							addps.setString(1, Integer.toString(user_id));
							addps.setString(2, name);
							addps.setString(3, Double.toString(pret));
							addps.setString(4, "1");
							
							addps.executeUpdate();

							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
						}
					
					}catch(Exception e2) {System.out.print(e2);}
					
				}
			});
			Image pepsi=new ImageIcon(this.getClass().getResource("/pepsi.png")).getImage();
			btnPepsi.setIcon(new ImageIcon(pepsi));
			btnPepsi.setFont(new Font("Tahoma", Font.BOLD, 48));
			btnPepsi.setBounds(160, 10, 140, 124);
			panel_1.add(btnPepsi);
			
			JButton btnOreo = new JButton("");
			btnOreo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						cautps.setString(1, "oreo");
						cautps.setString(2, Integer.toString(user_id));
						
						prod.setString(1, "oreo");
						
						cant.setString(1, "oreo");
						cant.setString(2, Integer.toString(user_id));
						
						ResultSet q=cant.executeQuery();
						q.next();
						
						ResultSet p=prod.executeQuery();
						p.next();
						
						ResultSet rs=cautps.executeQuery();
						if(rs.next()) {
							
							int qf = q.getInt(1)+1;
							updps.setString(1, Integer.toString(qf));
							updps.setString(2, p.getString("name"));
							updps.setString(3, Integer.toString(user_id));
							updps.executeUpdate();
							
							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
							}
						
						else {
							double pret=p.getDouble("price");
							String name = p.getString("name");
							
							addps.setString(1, Integer.toString(user_id));
							addps.setString(2, name);
							addps.setString(3, Double.toString(pret));
							addps.setString(4, "1");
							
							addps.executeUpdate();

							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
						}
					
					}catch(Exception e2) {System.out.print(e2);}
					
				}
			});
			Image oreo=new ImageIcon(this.getClass().getResource("/oreo.png")).getImage();
			btnOreo.setIcon(new ImageIcon(oreo));
			btnOreo.setFont(new Font("Tahoma", Font.BOLD, 48));
			btnOreo.setBounds(160, 144, 140, 124);
			panel_1.add(btnOreo);
			
			JButton btnTwix = new JButton("");
			btnTwix.setBackground(Color.WHITE);
			btnTwix.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						cautps.setString(1, "twix");
						cautps.setString(2, Integer.toString(user_id));
						
						prod.setString(1, "twix");
						
						cant.setString(1, "twix");
						cant.setString(2, Integer.toString(user_id));
						
						ResultSet q=cant.executeQuery();
						q.next();
						
						ResultSet p=prod.executeQuery();
						p.next();
						
						ResultSet rs=cautps.executeQuery();
						if(rs.next()) {
							
							int qf = q.getInt(1)+1;
							updps.setString(1, Integer.toString(qf));
							updps.setString(2, p.getString("name"));
							updps.setString(3, Integer.toString(user_id));
							updps.executeUpdate();
							
							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
							}
						
						else {
							double pret=p.getDouble("price");
							String name = p.getString("name");
							
							addps.setString(1, Integer.toString(user_id));
							addps.setString(2, name);
							addps.setString(3, Double.toString(pret));
							addps.setString(4, "1");
							
							addps.executeUpdate();

							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
						}
					
					}catch(Exception e2) {System.out.print(e2);}
					
				}
			});
			Image twix=new ImageIcon(this.getClass().getResource("/twix.png")).getImage();
			btnTwix.setIcon(new ImageIcon(twix));
			btnTwix.setFont(new Font("Tahoma", Font.BOLD, 48));
			btnTwix.setBounds(160, 278, 140, 124);
			panel_1.add(btnTwix);
			
			JButton btnJoe = new JButton("");
			btnJoe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						cautps.setString(1, "joe");
						cautps.setString(2, Integer.toString(user_id));
						
						prod.setString(1, "joe");
						
						cant.setString(1, "joe");
						cant.setString(2, Integer.toString(user_id));
						
						ResultSet q=cant.executeQuery();
						q.next();
						
						ResultSet p=prod.executeQuery();
						p.next();
						
						ResultSet rs=cautps.executeQuery();
						if(rs.next()) {
							
							int qf = q.getInt(1)+1;
							updps.setString(1, Integer.toString(qf));
							updps.setString(2, p.getString("name"));
							updps.setString(3, Integer.toString(user_id));
							updps.executeUpdate();
							
							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
							}
						
						else {
							double pret=p.getDouble("price");
							String name = p.getString("name");
							
							addps.setString(1, Integer.toString(user_id));
							addps.setString(2, name);
							addps.setString(3, Double.toString(pret));
							addps.setString(4, "1");
							
							addps.executeUpdate();

							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
						}
					
					}catch(Exception e2) {System.out.print(e2);}
					
				}
			});
			Image joe =new ImageIcon(this.getClass().getResource("/joe.png")).getImage();
			btnJoe.setIcon(new ImageIcon(joe));
			btnJoe.setFont(new Font("Tahoma", Font.BOLD, 48));
			btnJoe.setBounds(310, 10, 140, 124);
			panel_1.add(btnJoe);
			
			JButton btnRom = new JButton("");
			btnRom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						cautps.setString(1, "rom");
						cautps.setString(2, Integer.toString(user_id));
						
						prod.setString(1, "rom");
						
						cant.setString(1, "rom");
						cant.setString(2, Integer.toString(user_id));
						
						ResultSet q=cant.executeQuery();
						q.next();
						
						ResultSet p=prod.executeQuery();
						p.next();
						
						ResultSet rs=cautps.executeQuery();
						if(rs.next()) {
							
							int qf = q.getInt(1)+1;
							updps.setString(1, Integer.toString(qf));
							updps.setString(2, p.getString("name"));
							updps.setString(3, Integer.toString(user_id));
							updps.executeUpdate();
							
							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							
							costProdus();
							}
						
						else {
							double pret=p.getDouble("price");
							String name = p.getString("name");
							
							addps.setString(1, Integer.toString(user_id));
							addps.setString(2, name);
							addps.setString(3, Double.toString(pret));
							addps.setString(4, "1");
							
							addps.executeUpdate();

							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
						}
					
					}catch(Exception e2) {System.out.print(e2);}
					
				}
			});
			Image rom=new ImageIcon(this.getClass().getResource("/rom.png")).getImage();
			btnRom.setIcon(new ImageIcon(rom));
			btnRom.setFont(new Font("Tahoma", Font.BOLD, 48));
			btnRom.setBounds(310, 144, 140, 124);
			panel_1.add(btnRom);
			
			JButton btnMilka = new JButton("");
			btnMilka.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						cautps.setString(1, "milka");
						cautps.setString(2, Integer.toString(user_id));
						
						prod.setString(1, "milka");
						
						cant.setString(1, "milka");
						cant.setString(2, Integer.toString(user_id));
						
						ResultSet q=cant.executeQuery();
						q.next();
						
						ResultSet p=prod.executeQuery();
						p.next();
						
						ResultSet rs=cautps.executeQuery();
						if(rs.next()) {
							
							int qf = q.getInt(1)+1;
							updps.setString(1, Integer.toString(qf));
							updps.setString(2, p.getString("name"));
							updps.setString(3, Integer.toString(user_id));
							updps.executeUpdate();
							
							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
							}
						
						else {
							double pret=p.getDouble("price");
							String name = p.getString("name");
							
							addps.setString(1, Integer.toString(user_id));
							addps.setString(2, name);
							addps.setString(3, Double.toString(pret));
							addps.setString(4, "1");
							
							addps.executeUpdate();

							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
						}
					
					}catch(Exception e2) {System.out.print(e2);}
					
				}
			});
			Image milka=new ImageIcon(this.getClass().getResource("/milka.png")).getImage();
			btnMilka.setIcon(new ImageIcon(milka));
			btnMilka.setFont(new Font("Tahoma", Font.BOLD, 48));
			btnMilka.setBounds(310, 278, 140, 124);
			panel_1.add(btnMilka);
			
			JButton btnKnoppers = new JButton("");
			btnKnoppers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						cautps.setString(1, "knoppers");
						cautps.setString(2, Integer.toString(user_id));
						
						prod.setString(1, "knoppers");
						
						cant.setString(1, "knoppers");
						cant.setString(2, Integer.toString(user_id));
						
						ResultSet q=cant.executeQuery();
						q.next();
						
						ResultSet p=prod.executeQuery();
						p.next();
						
						ResultSet rs=cautps.executeQuery();
						if(rs.next()) {
							
							int qf = q.getInt(1)+1;
							updps.setString(1, Integer.toString(qf));
							updps.setString(2, p.getString("name"));
							updps.setString(3, Integer.toString(user_id));
							updps.executeUpdate();
							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
							}
						
						else {
							double pret=p.getDouble("price");
							String name = p.getString("name");
							
							addps.setString(1, Integer.toString(user_id));
							addps.setString(2, name);
							addps.setString(3, Double.toString(pret));
							addps.setString(4, "1");
							
							addps.executeUpdate();

							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
						}
					
					}catch(Exception e2) {System.out.print(e2);}
					
				}
			});
			Image knoppers=new ImageIcon(this.getClass().getResource("/knoppers.png")).getImage();
			btnKnoppers.setIcon(new ImageIcon(knoppers));
			btnKnoppers.setFont(new Font("Tahoma", Font.BOLD, 48));
			btnKnoppers.setBounds(460, 10, 140, 124);
			panel_1.add(btnKnoppers);
			
			JButton btnSalatini = new JButton("");
			btnSalatini.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						cautps.setString(1, "salatini");
						cautps.setString(2, Integer.toString(user_id));
						
						prod.setString(1, "salatini");
						
						cant.setString(1, "salatini");
						cant.setString(2, Integer.toString(user_id));
						
						ResultSet q=cant.executeQuery();
						q.next();
						
						ResultSet p=prod.executeQuery();
						p.next();
						
						ResultSet rs=cautps.executeQuery();
						if(rs.next()) {
							
							int qf = q.getInt(1)+1;
							updps.setString(1, Integer.toString(qf));
							updps.setString(2, p.getString("name"));
							updps.setString(3, Integer.toString(user_id));
							updps.executeUpdate();
							
							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
							}
						
						else {
							double pret=p.getDouble("price");
							String name = p.getString("name");
							
							addps.setString(1, Integer.toString(user_id));
							addps.setString(2, name);
							addps.setString(3, Double.toString(pret));
							addps.setString(4, "1");
							
							addps.executeUpdate();

							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
						}
					
					}catch(Exception e2) {System.out.print(e2);}
					
				}
			});
			Image salatini=new ImageIcon(this.getClass().getResource("/salatini.png")).getImage();
			btnSalatini.setIcon(new ImageIcon(salatini));
			btnSalatini.setFont(new Font("Tahoma", Font.BOLD, 48));
			btnSalatini.setBounds(460, 144, 140, 124);
			panel_1.add(btnSalatini);
			
			JButton btnLays = new JButton("");
			btnLays.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						cautps.setString(1, "lays");
						cautps.setString(2, Integer.toString(user_id));
						
						prod.setString(1, "lays");
						
						cant.setString(1, "lays");
						cant.setString(2, Integer.toString(user_id));
						
						ResultSet q=cant.executeQuery();
						q.next();
						
						ResultSet p=prod.executeQuery();
						p.next();
						
						ResultSet rs=cautps.executeQuery();
						if(rs.next()) {
							
							int qf = q.getInt(1)+1;
							updps.setString(1, Integer.toString(qf));
							updps.setString(2, p.getString("name"));
							updps.setString(3, Integer.toString(user_id));
							updps.executeUpdate();
							
							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							
							costProdus();
							}
						
						else {
							double pret=p.getDouble("price");
							String name = p.getString("name");
							
							addps.setString(1, Integer.toString(user_id));
							addps.setString(2, name);
							addps.setString(3, Double.toString(pret));
							addps.setString(4, "1");
							
							addps.executeUpdate();

							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
						}
					
					}catch(Exception e2) {System.out.print(e2);}
					
				}
			});
			Image lays=new ImageIcon(this.getClass().getResource("/lays.png")).getImage();
			btnLays.setIcon(new ImageIcon(lays));
			btnLays.setFont(new Font("Tahoma", Font.BOLD, 48));
			btnLays.setBounds(460, 278, 140, 124);
			panel_1.add(btnLays);
			
			JButton btnMirinda = new JButton("");
			btnMirinda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						cautps.setString(1, "mirinda");
						cautps.setString(2, Integer.toString(user_id));
						
						prod.setString(1, "mirinda");
						
						cant.setString(1, "mirinda");
						cant.setString(2, Integer.toString(user_id));
						
						ResultSet q=cant.executeQuery();
						q.next();
						
						ResultSet p=prod.executeQuery();
						p.next();
						
						ResultSet rs=cautps.executeQuery();
						if(rs.next()) {
							
							int qf = q.getInt(1)+1;
							updps.setString(1, Integer.toString(qf));
							updps.setString(2, p.getString("name"));
							updps.setString(3, Integer.toString(user_id));
							updps.executeUpdate();
							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
							}
						
						else {
							double pret=p.getDouble("price");
							String name = p.getString("name");
							
							addps.setString(1, Integer.toString(user_id));
							addps.setString(2, name);
							addps.setString(3, Double.toString(pret));
							addps.setString(4, "1");
							
							addps.executeUpdate();

							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
						}
					
					}catch(Exception e2) {System.out.print(e2);}
					
				}
			});
			Image mirinda=new ImageIcon(this.getClass().getResource("/mirinda.png")).getImage();
			btnMirinda.setIcon(new ImageIcon(mirinda));
			btnMirinda.setFont(new Font("Tahoma", Font.BOLD, 48));
			btnMirinda.setBounds(607, 10, 140, 124);
			panel_1.add(btnMirinda);
			
			JButton btnSnickers = new JButton("");
			btnSnickers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						cautps.setString(1, "snickers");
						cautps.setString(2, Integer.toString(user_id));
						
						prod.setString(1, "snickers");
						
						cant.setString(1, "snickers");
						cant.setString(2, Integer.toString(user_id));
						
						ResultSet q=cant.executeQuery();
						q.next();
						
						ResultSet p=prod.executeQuery();
						p.next();
						
						ResultSet rs=cautps.executeQuery();
						if(rs.next()) {
							
							int qf = q.getInt(1)+1;
							updps.setString(1, Integer.toString(qf));
							updps.setString(2, p.getString("name"));
							updps.setString(3, Integer.toString(user_id));
							updps.executeUpdate();
							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
							}
						
						else {
							double pret=p.getDouble("price");
							String name = p.getString("name");
							
							addps.setString(1, Integer.toString(user_id));
							addps.setString(2, name);
							addps.setString(3, Double.toString(pret));
							addps.setString(4, "1");
							
							addps.executeUpdate();

							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
						}
					
					}catch(Exception e2) {System.out.print(e2);}
					
				}
			});
			Image snickers=new ImageIcon(this.getClass().getResource("/snickers.png")).getImage();
			btnSnickers.setIcon(new ImageIcon(snickers));
			btnSnickers.setFont(new Font("Tahoma", Font.BOLD, 48));
			btnSnickers.setBounds(607, 144, 140, 124);
			panel_1.add(btnSnickers);
			
			JButton btnMtn = new JButton("");
			btnMtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						cautps.setString(1, "mtn dew");
						cautps.setString(2, Integer.toString(user_id));
						
						prod.setString(1, "mtn dew");
						
						cant.setString(1, "mtn dew");
						cant.setString(2, Integer.toString(user_id));
						
						ResultSet q=cant.executeQuery();
						q.next();
						
						ResultSet p=prod.executeQuery();
						p.next();
						
						ResultSet rs=cautps.executeQuery();
						if(rs.next()) {
							
							int qf = q.getInt(1)+1;
							updps.setString(1, Integer.toString(qf));
							updps.setString(2, p.getString("name"));
							updps.setString(3, Integer.toString(user_id));
							updps.executeUpdate();
							
							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							costProdus();
							
							}
						
						else {
							double pret=p.getDouble("price");
							String name = p.getString("name");
							
							addps.setString(1, Integer.toString(user_id));
							addps.setString(2, name);
							addps.setString(3, Double.toString(pret));
							addps.setString(4, "1");
							
							addps.executeUpdate();

							ResultSet afisare = table.executeQuery();
							DefaultTableModel RecordTable= (DefaultTableModel) getTable().getModel();
							RecordTable.setRowCount(0);
							
							while(afisare.next()) {
								String namep = afisare.getString("name");
								String price = afisare.getString("price");
								String cantitate = afisare.getString("quantity");
								String tbData[] = {namep,cantitate,price};
								DefaultTableModel model=(DefaultTableModel) getTable().getModel();
								model.addRow(tbData);
								costProdus();
							}
							
							
						}
					
					}catch(Exception e2) {System.out.print(e2);}
					
				}
			});
			Image mtn=new ImageIcon(this.getClass().getResource("/mtn dew.png")).getImage();
			btnMtn.setIcon(new ImageIcon(mtn));
			btnMtn.setFont(new Font("Tahoma", Font.BOLD, 48));
			btnMtn.setBounds(607, 278, 140, 124);
			panel_1.add(btnMtn);
			
		}catch(Exception e2) {System.out.print(e2);}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, null, null));
		scrollPane.setBounds(323, 11, 303, 341);
		frame.getContentPane().add(scrollPane);
		
		setTable(new JTable());
		getTable().setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"PRODUSE", "CANTITATE", "PRET"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(135);
		table.getColumnModel().getColumn(0).setMinWidth(135);
		table.getColumnModel().getColumn(1).setMinWidth(20);
		scrollPane.setViewportView(getTable());
		
		txtBarCode = new JTextField();
		txtBarCode.setEditable(false);
		txtBarCode.setFont(new Font("Free 3 of 9 Extended", Font.PLAIN, 36));
		txtBarCode.setBounds(323, 362, 303, 55);
		frame.getContentPane().add(txtBarCode);
		txtBarCode.setColumns(10);
		
		JLabel logo = new JLabel("");
		logo.setBounds(10, 183, 303, 211);
		frame.getContentPane().add(logo);
		Image sigla=new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		logo.setIcon(new ImageIcon(sigla));
		
		JLabel template = new JLabel("");
		Image templateimg=new ImageIcon(this.getClass().getResource("/templateapp.png")).getImage();
		template.setIcon(new ImageIcon(templateimg));
		template.setBounds(0, 10, 1436, 653);
		frame.getContentPane().add(template);
		
		//Afisare tabel la start
		updtabel();
		
	}

	public JTextField getTxtBarCode() {
		return txtBarCode;
	}

	public void setTxtBarCode(JTextField txtBarCode) {
		this.txtBarCode = txtBarCode;
	}

	public JComboBox getPayment() {
		return Payment;
	}

	public void setPayment(JComboBox payment) {
		Payment = payment;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
}
