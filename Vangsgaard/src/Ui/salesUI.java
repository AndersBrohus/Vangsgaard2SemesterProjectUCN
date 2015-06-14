package Ui;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import Mdl.*;
import Ctr.*;

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
	private CtrCustomer cCtr;
	private CtrSalesOrder oCtr;
	private CtrProduct pCtr;
	private CtrPartOrder pOrdCtr;
 	private CtrInvoice iCtr;

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
		cCtr = new CtrCustomer();
		oCtr = new CtrSalesOrder();
		pCtr = new CtrProduct();
		pOrdCtr = new CtrPartOrder();
		iCtr = new CtrInvoice();
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
				customer cus = cCtr.findCustomerPhone(Integer.parseInt(txtPhone.getText()));
				txtAddress.setText(cus.getAddress());
				txtName.setText(cus.getName());
				txtEmail.setText(cus.getEmail());
			}
		});
		btnFindKunde.setBounds(10, 5, 103, 23);
		contentPane.add(btnFindKunde);
		
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
				salesOrder ord = oCtr.getSalesOrder(ordNumber);
				int proNumb = Integer.parseInt(txtProductNumber.getText());
				int amount = Integer.parseInt(txtAntal.getText());
				product p = pCtr.getProduct(proNumb);
				try {
					pOrdCtr.insertNew(p.getId(), ord.getId(), amount, 1);
					Object[] objs = {p.getId(),p.getName(),amount,p.getPrice()};
					tableModel.addRow(objs);
					txtProductNumber.setText("");
					txtAntal.setText("");
					
					double price = p.getPrice() * amount;
					totalPrice += price;
					lblTotalPrice.setText("Total prisen er: " + totalPrice + "kr");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
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
				customer cus = cCtr.findCustomerPhone(Integer.parseInt(txtPhone.getText()));
				int id = 0;
				try {
					salesOrder ord = oCtr.getSalesOrder(oCtr.insertOrder(cus.getId(), getDate(),"", false));
					id = ord.getId();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(frame, "En ordre er blevet lavet med følgende ordre nummber " + id);
				ordNumber = id;
		    }
		});
		btnNyOrdre.setBounds(10, 209, 222, 69);
		contentPane.add(btnNyOrdre);
		
		JButton btnKortTerminal = new JButton("Kort");
		btnKortTerminal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					customer cus = cCtr.findCustomerPhone(Integer.parseInt(txtPhone.getText()));
					int invoice = iCtr.insertNew(getDate(),totalPrice);
					oCtr.updateSalesOrder(ordNumber,totalPrice, cus.getId(), invoice, getDate(), "", false);
					
					if (tableModel.getRowCount() > 0) {
					    for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
					    	tableModel.removeRow(i);
					    }
					}
					lblTotalPrice.setText("");
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		btnKortTerminal.setBounds(129, 289, 103, 69);
		contentPane.add(btnKortTerminal);
		
		JButton btnKontant = new JButton("Kontant");
		btnKontant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					customer cus = cCtr.findCustomerPhone(Integer.parseInt(txtPhone.getText()));
					int invoice = iCtr.insertNew(getDate(),totalPrice);
					oCtr.updateSalesOrder(ordNumber,totalPrice, cus.getId(), invoice, getDate(), "", false);
					
					if (tableModel.getRowCount() > 0) {
					    for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
					    	tableModel.removeRow(i);
					    }
					}
					lblTotalPrice.setText("");
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnKontant.setBounds(10, 289, 103, 69);
		contentPane.add(btnKontant);
		
		
		
	}
	
	private String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return dateFormat.format(date);
	}
	
	public void clearFields()
	{
		String t = "";
		txtEmail.setText(t);
		txtName.setText(t);
		txtPhone.setText(t);
	}

	}
