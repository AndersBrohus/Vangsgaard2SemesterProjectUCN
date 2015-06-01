package Ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class salesUI extends mainUI {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTable table;
	private JTextField txtProductNumber;
	private double totalPrice;
	private JTextField txtAntal;
	private int ordNumber;
	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					salesUI frame = new salesUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public salesUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 39, 222, 159);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNavn = new JLabel("Navn");
		lblNavn.setBounds(10, 11, 63, 14);
		panel.add(lblNavn);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(10, 36, 63, 14);
		panel.add(lblAdresse);
		
		JLabel lblEmailField = new JLabel("Email");
		lblEmailField.setBounds(10, 86, 63, 14);
		panel.add(lblEmailField);
		
		JLabel lblTlfNr = new JLabel("Tlf nr");
		lblTlfNr.setBounds(10, 61, 63, 14);
		panel.add(lblTlfNr);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setBounds(83, 11, 129, 20);
		panel.add(txtName);
		txtName.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setEditable(false);
		txtAddress.setColumns(10);
		txtAddress.setBounds(83, 36, 129, 20);
		panel.add(txtAddress);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(83, 61, 129, 20);
		panel.add(txtPhone);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(83, 86, 129, 20);
		panel.add(txtEmail);
		
		JButton btnOpret = new JButton("Opret");
		btnOpret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*cCtr.createCustomer(txtName.getText(), txtAddress.getText(), txtPhone.getText(), txtEmail.getText());
				clearFields();
				btnOpret.setVisible(false);
				txtAddress.setEditable(false);
				txtEmail.setEditable(false);
				txtName.setEditable(false);*/
			}
		});
		btnOpret.setBounds(123, 125, 89, 23);
		btnOpret.setVisible(false);
		panel.add(btnOpret);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setBounds(10, 125, 75, 23);
		panel.add(btnClear);
		
		JButton btnFindKunde = new JButton("Find kunde");
		btnFindKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*Customer cus = cCtr.findCustomer(txtPhone.getText());
				txtAddress.setText(cus.getAddress());
				txtName.setText(cus.getName());
				txtEmail.setText(cus.getEmail());*/
			}
		});
		btnFindKunde.setBounds(10, 5, 103, 23);
		contentPane.add(btnFindKunde);
		
		JButton btnOpretKunde = new JButton("Opret kunde");
		btnOpretKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtAddress.setEditable(true);
				txtEmail.setEditable(true);
				txtName.setEditable(true);
				btnOpret.setVisible(true);
				clearFields();
			}
		});
		btnOpretKunde.setBounds(123, 5, 109, 23);
		contentPane.add(btnOpretKunde);
		
		JButton btnReturnProduct = new JButton("Retur vare");
		btnReturnProduct.setBounds(10, 209, 103, 69);
		contentPane.add(btnReturnProduct);
		
		String col[] = {"Vare nr","Vare","Antal","Pris"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		/*Object[] objs ={1,"Test", 100};
		Object[] objs2 ={500,"Test3", 500};
		tableModel.addRow(objs);
		tableModel.addRow(objs2);*/

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(242, 39, 353, 305);
		contentPane.add(scrollPane);
		
		table = new JTable(tableModel);
		table.setRowSelectionAllowed(false);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		txtProductNumber = new JTextField();
		txtProductNumber.setText("Vare nr");
		txtProductNumber.setBounds(413, 6, 86, 20);
		contentPane.add(txtProductNumber);
		txtProductNumber.setColumns(10);
		
		JLabel lblTotalPrice = new JLabel();
		lblTotalPrice.setBounds(242, 355, 353, 14);
		contentPane.add(lblTotalPrice);
		
		JButton btnTilfj = new JButton("Tilf\u00F8j");
		btnTilfj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*Order ord = oCtr.getOrder(ordNumber);
				int prodNumber = Integer.parseInt(txtProductNumber.getText());
				int amount = Integer.parseInt(txtAntal.getText());
				Product p = pCtr.getProduct(prodNumber);
				ord.addPartOrder(amount, p);
				
				Object[] objs = {p.getProductNumber(),p.getName(),amount,p.getPrice()};
				tableModel.addRow(objs);
				txtProductNumber.setText("");
				txtAntal.setText("");
				
				double price = p.getPrice() * amount;
				totalPrice += price;
				lblTotalPrice.setText("Total prisen er: " + totalPrice + "kr");*/
			}
		});
		btnTilfj.setBounds(338, 5, 65, 23);
		contentPane.add(btnTilfj);
		
		txtAntal = new JTextField();
		txtAntal.setText("Antal");
		txtAntal.setColumns(10);
		txtAntal.setBounds(509, 6, 86, 20);
		contentPane.add(txtAntal);
		
		JButton btnNyOrdre = new JButton("Ny ordre");
		btnNyOrdre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	/*Customer cus = cCtr.findCustomer(txtPhone.getText());
		    	Order ord = oCtr.getOrder(oCtr.createOrder(cus));
		    	JOptionPane.showMessageDialog(frame, "En ordre er blevet lavet med følgende ordre nummber " + ord.getOrderNumber());
		    	ordNumber = ord.getOrderNumber();*/
		    }
		});
		btnNyOrdre.setBounds(129, 209, 103, 69);
		contentPane.add(btnNyOrdre);
		
		JButton btnKortTerminal = new JButton("Kort");

		btnKortTerminal.setBounds(129, 289, 103, 69);
		contentPane.add(btnKortTerminal);
		
		JButton btnKontant = new JButton("Kontant");
		
		btnKontant.setBounds(10, 289, 103, 69);
		contentPane.add(btnKontant);
		
		
		
	}
	
	public void clearFields()
	{
		String t = "";
		txtEmail.setText(t);
		txtName.setText(t);
		txtPhone.setText(t);
	}

	}
