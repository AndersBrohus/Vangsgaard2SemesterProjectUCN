package Ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;

import Ctr.*;
import Mdl.*;
import Db.*;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

public class testGuiFrame extends JFrame {

	private JPanel contentPane;
	private CtrDepartment ctrDep;
	private CtrCustomerType ctrCType;
	private JTextField txtNavn;
	private JTextField txtAdresse;
	private JTextField txtTelefon;
	private JTextField txtEmail;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testGuiFrame frame = new testGuiFrame();
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
	public testGuiFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		ctrDep = new CtrDepartment();
		ctrCType = new CtrCustomerType();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnKlik = new JButton("KLIK");
		btnKlik.setBounds(165, 201, 89, 23);
		contentPane.add(btnKlik);
		
		txtNavn = new JTextField();
		txtNavn.setText("Navn");
		txtNavn.setBounds(10, 11, 86, 20);
		contentPane.add(txtNavn);
		txtNavn.setColumns(10);
		
		txtAdresse = new JTextField();
		txtAdresse.setText("Adresse");
		txtAdresse.setBounds(10, 42, 86, 20);
		contentPane.add(txtAdresse);
		txtAdresse.setColumns(10);
		
		txtTelefon = new JTextField();
		txtTelefon.setText("Telefon");
		txtTelefon.setBounds(10, 73, 86, 20);
		contentPane.add(txtTelefon);
		txtTelefon.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setText("Email");
		txtEmail.setBounds(10, 104, 86, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JList list = new JList(ctrCType.findAllCustomerType().toArray());
		list.setBounds(195, 13, 229, 111);
		contentPane.add(list);
		
		btnKlik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int phone = Integer.parseInt(txtTelefon.getText());
					customer cus = new customer();
					cus.setAddress(txtAdresse.getText());
					cus.setCustomerType(list.getSelectedIndex() + 1);
					cus.setEmail(txtEmail.getText());
					cus.setName(txtNavn.getText());
					cus.setPhone(phone);
					
			        DbConnection.startTransaction();
			        IFDBcustomer dbCus = new DBcustomer();
			        dbCus.insertCustomer(cus);
			        DbConnection.commitTransaction();
			        
			        JOptionPane.showMessageDialog(null,"En bruger er blevet oprettet!");
			        
			        clearTxtFields();
			        list.clearSelection();
		        }
				catch(Exception e){
					
				}
			}
		});
	}
	public void clearTxtFields()
	{
		String t = "";
		txtAdresse.setText(t);
		txtEmail.setText(t);
		txtNavn.setText(t);
		txtTelefon.setText(t);
	}
}
