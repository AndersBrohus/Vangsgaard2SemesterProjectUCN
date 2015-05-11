package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import ctr.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

import mdl.*;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class AdminUI extends Main {

	private JPanel contentPane;
	private JTextField txtPhoneNumber;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtEmail;
	private CustomerCtrl cCtr;
	private ProductCtrl pCtr;
	private JTextField txtProductName;
	private JTextField txtPrice;
	private JTextField txtType;
	private ArrayList<Product> pList;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminUI frame = new AdminUI();
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
	public AdminUI() {
		cCtr = new CustomerCtrl();
		pCtr = new ProductCtrl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 507, 325);
		contentPane.add(tabbedPane);
		
		JPanel customerPanel = new JPanel();
		tabbedPane.addTab("Kunde", null, customerPanel, null);
		customerPanel.setLayout(null);
		
		JLabel lblPhoneNumber = new JLabel("Telefon nummer");
		lblPhoneNumber.setBounds(92, 32, 112, 16);
		customerPanel.add(lblPhoneNumber);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setBounds(276, 26, 134, 28);
		customerPanel.add(txtPhoneNumber);
		txtPhoneNumber.setColumns(10);
		
		JLabel lblName = new JLabel("Navn");
		lblName.setBounds(92, 66, 61, 16);
		customerPanel.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(276, 60, 134, 28);
		customerPanel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblAddress = new JLabel("Adresse");
		lblAddress.setBounds(92, 100, 61, 16);
		customerPanel.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(276, 94, 134, 28);
		customerPanel.add(txtAddress);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(92, 134, 61, 16);
		customerPanel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(276, 128, 134, 28);
		customerPanel.add(txtEmail);
		
		JButton btnOpretKunde = new JButton("Opret Kunde");
		btnOpretKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String address = txtAddress.getText();
				String phoneNumber = txtPhoneNumber.getText();
				String email = txtEmail.getText();
				cCtr.createCustomer(name,address,phoneNumber,email);
				clearFields();
			}
		});
		btnOpretKunde.setBounds(37, 223, 120, 29);
		customerPanel.add(btnOpretKunde);
		
		JButton btnRedigerKunde = new JButton("Rediger Kunde");
		btnRedigerKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer cus = cCtr.findCustomer(txtPhoneNumber.getText());
				cCtr.updateCustomer(cus, txtName.getText(), txtAddress.getText(), txtPhoneNumber.getText(), txtEmail.getText());
				clearFields();
				btnOpretKunde.setVisible(true);
				btnRedigerKunde.setVisible(false);
			}
		});
		btnRedigerKunde.setBounds(36, 223, 117, 29);
		btnRedigerKunde.setVisible(false);
		customerPanel.add(btnRedigerKunde);
		
		JButton btnFindKunde = new JButton("Find Kunde");
		btnFindKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer cus = cCtr.findCustomer(txtPhoneNumber.getText());
				txtAddress.setText(cus.getAddress());
				txtEmail.setText(cus.getEmail());
				txtName.setText(cus.getName());
				
				btnOpretKunde.setVisible(false);
				btnRedigerKunde.setVisible(true);
			}
		});
		btnFindKunde.setBounds(194, 223, 117, 29);
		customerPanel.add(btnFindKunde);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
				btnOpretKunde.setVisible(true);
				btnRedigerKunde.setVisible(false);
			}
		});
		btnClear.setBounds(348, 223, 117, 29);
		customerPanel.add(btnClear);
		
		JPanel productPanel = new JPanel();
		tabbedPane.addTab("Produkt", null, productPanel, null);
		productPanel.setLayout(null);
		
		JLabel lblProductName = new JLabel("Navn");
		lblProductName.setBounds(102, 33, 61, 16);
		productPanel.add(lblProductName);
		
		txtProductName = new JTextField();
		txtProductName.setBounds(265, 27, 134, 28);
		productPanel.add(txtProductName);
		txtProductName.setColumns(10);
		
		JLabel lblPrice = new JLabel("Pris");
		lblPrice.setBounds(102, 67, 61, 16);
		productPanel.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(265, 61, 134, 28);
		productPanel.add(txtPrice);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(102, 100, 61, 16);
		productPanel.add(lblType);
		
		txtType = new JTextField();
		txtType.setColumns(10);
		txtType.setBounds(265, 94, 134, 28);
		productPanel.add(txtType);
		
		JButton btnOpretProdukt = new JButton("Opret");
		btnOpretProdukt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double price = Double.parseDouble(txtPrice.getText());
				pCtr.createProduct(txtProductName.getText(), txtType.getText(), price);
				
				clearFields();
			}
		});
		
		JList list = new JList();
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(102, 132, 297, 110);
		list.setVisible(false);
		productPanel.add(list);
		btnOpretProdukt.setBounds(22, 254, 70, 29);
		productPanel.add(btnOpretProdukt);
		
		JButton btnFindProdukt = new JButton("Find");
		btnFindProdukt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pList = pCtr.findProductGUI(txtProductName.getText());
				list.setListData(pList.toArray());
				list.setVisible(true);
			}
		});
		btnFindProdukt.setBounds(114, 254, 70, 29);
		productPanel.add(btnFindProdukt);
		
		JButton btnProductClear = new JButton("Clear fields");
		btnProductClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.setVisible(false);
				clearFields();
			}
		});
		btnProductClear.setBounds(390, 254, 102, 29);
		productPanel.add(btnProductClear);
		
		JButton btnVlgProdukt = new JButton("V\u00E6lg");
		btnVlgProdukt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex() + 1;
				Product pro = pCtr.getProduct(index);
				txtProductName.setText(pro.getName());
				double price = pro.getPrice();
				String finalPrice = Double.toString(price);
				txtPrice.setText(finalPrice);
				txtType.setText(pro.getType());
				System.out.println(index);
			}
		});
		btnVlgProdukt.setBounds(206, 254, 70, 29);
		productPanel.add(btnVlgProdukt);
		
		JButton btnSlet = new JButton("Slet");
		btnSlet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int index = list.getSelectedIndex() + 1;
				pCtr.deleteProduct(index);
				pList = pCtr.findProductGUI(txtProductName.getText());
				list.setListData(pList.toArray());
				clearFields();
			}
		});
		btnSlet.setBounds(298, 253, 70, 29);
		productPanel.add(btnSlet);
	}
	
	public void clearFields()
	{
		String t = "";
		txtAddress.setText(t);
		txtEmail.setText(t);
		txtName.setText(t);
		txtPhoneNumber.setText(t);
		txtProductName.setText(t);
		txtPrice.setText(t);
		txtType.setText(t);
	}
}
