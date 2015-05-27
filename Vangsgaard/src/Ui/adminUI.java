package Ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Ctr.*;
import Mdl.*;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.sql.Array;

import javax.swing.border.MatteBorder;

public class adminUI extends mainUI {

	private JPanel contentPane;
	private JTextField txtNavn;
	private JTextField txtAdresse;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JButton btnUpdate;
	private JButton btnIndstKunde;
	private Object[] cTypes;
	private JTextField txtDiscount;
	private JTextField txtTitle;
	private JTextField txtPhoneSize;
	private JTextField txtSize;
	private JTextField txtType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminUI frame = new adminUI();
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
	public adminUI() {
		CtrCustomerType ctrCType = new CtrCustomerType();
		CtrCustomer ctrCus = new CtrCustomer();
		CtrSizes ctrSize = new CtrSizes();
		cTypes = ctrCType.findAllCustomerType().toArray();
		setTitle("Administration - Vangsgaard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 851);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 590, 791);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Kunder", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNavn = new JLabel("Navn");
		lblNavn.setBounds(10, 13, 75, 14);
		panel.add(lblNavn);
		
		txtNavn = new JTextField();
		txtNavn.setBounds(107, 13, 104, 20);
		panel.add(txtNavn);
		txtNavn.setColumns(10);
		
		txtAdresse = new JTextField();
		txtAdresse.setColumns(10);
		txtAdresse.setBounds(107, 48, 104, 20);
		panel.add(txtAdresse);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(10, 48, 75, 14);
		panel.add(lblAdresse);
		
		JLabel lblPhone = new JLabel("Telefon");
		lblPhone.setBounds(10, 86, 75, 14);
		panel.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(107, 86, 104, 20);
		panel.add(txtPhone);
		
		JLabel llbEmail = new JLabel("Email");
		llbEmail.setBounds(10, 122, 75, 14);
		panel.add(llbEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(107, 122, 104, 20);
		panel.add(txtEmail);
		
		JList list = new JList(cTypes);
		list.setBounds(249, 10, 272, 129);
		panel.add(list);
		
		JButton btnFindKunde = new JButton("Find Kunde");
		btnFindKunde.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int phoneNr = Integer.parseInt(txtPhone.getText());
				customer cus = ctrCus.findCustomerPhone(phoneNr);
				
				txtAdresse.setText(cus.getAddress());
				txtEmail.setText(cus.getEmail());
				txtNavn.setText(cus.getName());
				list.setSelectedIndex(cus.getCustomerType() - 1);
				btnIndstKunde.setVisible(false);
				btnUpdate.setVisible(true);
			}
		});
		btnFindKunde.setBounds(247, 196, 145, 23);
		panel.add(btnFindKunde);
		
		btnIndstKunde = new JButton("Inds\u00E6t Kunde");
		btnIndstKunde.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try
				{
					int phoneNr = Integer.parseInt(txtPhone.getText());
					ctrCus.insertNew(txtNavn.getText(), txtAdresse.getText(), phoneNr, txtEmail.getText(), list.getSelectedIndex() + 1);
					clearfields();
					list.clearSelection();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,
						    e.getMessage());
				}
			}
		});
		btnIndstKunde.setBounds(51, 196, 145, 23);
		panel.add(btnIndstKunde);
		
		btnUpdate = new JButton("Opdater kunde");
		btnUpdate.setVisible(false);
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int phoneNr = Integer.parseInt(txtPhone.getText());
				ctrCus.updateCustomer(phoneNr, txtNavn.getText(), txtAdresse.getText(), phoneNr, txtEmail.getText(), list.getSelectedIndex() + 1);
				clearfields();
				list.clearSelection();
				btnIndstKunde.setVisible(true);
				btnUpdate.setVisible(false);
				}
		});
		btnUpdate.setBounds(51, 196, 145, 23);
		panel.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearfields();
				list.clearSelection();
				btnIndstKunde.setVisible(true);
				btnUpdate.setVisible(false);
			}
		});
		btnClear.setBounds(443, 196, 89, 23);
		panel.add(btnClear);
		
		JList list_1 = new JList(cTypes);
		list_1.setBounds(303, 242, 272, 172);
		panel.add(list_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		panel_2.setBounds(10, 230, 565, 11);
		panel.add(panel_2);
		
		JLabel lblRabat = new JLabel("Rabat");
		lblRabat.setBounds(10, 278, 75, 14);
		panel.add(lblRabat);
		
		txtDiscount = new JTextField();
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(107, 278, 104, 20);
		panel.add(txtDiscount);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(10, 242, 75, 14);
		panel.add(lblTitle);
		
		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		txtTitle.setBounds(107, 242, 104, 20);
		panel.add(txtTitle);
		
		JButton btnInsertCustomerType = new JButton("Inds\u00E6t kundetype");
		btnInsertCustomerType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try
				{
					int discount = Integer.parseInt(txtDiscount.getText());
					ctrCType.insertNew(txtTitle.getText(), discount);
					clearfields();
					list_1.clearSelection();
					cTypes = ctrCType.findAllCustomerType().toArray();
					list_1.setListData(cTypes);
				}
				catch(Exception f)
				{
					JOptionPane.showMessageDialog(null,
						    f.getMessage());
				}
			}
		});
		btnInsertCustomerType.setBounds(10, 326, 131, 23);
		panel.add(btnInsertCustomerType);
		
		JButton btnUpdateCustomerType = new JButton("Opdater kundetype");
		btnUpdateCustomerType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				customerType cTypeTest = (customerType) list_1.getModel().getElementAt(list_1.getSelectedIndex());
				int discount = Integer.parseInt(txtDiscount.getText());
				ctrCType.updateCustomerType(cTypeTest.getId(), txtTitle.getText(), discount);
				clearfields();
				list_1.clearSelection();
				cTypes = ctrCType.findAllCustomerType().toArray();
				list_1.setListData(cTypes);
			}
		});
		btnUpdateCustomerType.setBounds(162, 360, 131, 23);
		panel.add(btnUpdateCustomerType);
		
		JButton btnClearCType = new JButton("Clear");
		btnClearCType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnClearCType.setBounds(10, 410, 89, 23);
		panel.add(btnClearCType);
		
		JButton btnFindCType = new JButton("Find kundetype");
		btnFindCType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				customerType cTypeTest = (customerType) list_1.getModel().getElementAt(list_1.getSelectedIndex());
				String discount = Integer.toString(cTypeTest.getDiscount());
				txtTitle.setText(cTypeTest.getTitle());
				txtDiscount.setText(discount);
			}
		});
		btnFindCType.setBounds(10, 360, 131, 23);
		panel.add(btnFindCType);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		panel_4.setBounds(10, 444, 565, 11);
		panel.add(panel_4);
		
		JList listSize = new JList();
		listSize.setBounds(303, 462, 272, 157);
		panel.add(listSize);
		
		JLabel label = new JLabel("Telefon");
		label.setBounds(303, 633, 75, 14);
		panel.add(label);
		
		txtPhoneSize = new JTextField();
		txtPhoneSize.setColumns(10);
		txtPhoneSize.setBounds(471, 630, 104, 20);
		panel.add(txtPhoneSize);
		
		JLabel lblSize = new JLabel("St\u00F8rrelse");
		lblSize.setBounds(10, 466, 75, 14);
		panel.add(lblSize);
		
		txtSize = new JTextField();
		txtSize.setEnabled(false);
		txtSize.setColumns(10);
		txtSize.setBounds(107, 466, 104, 20);
		panel.add(txtSize);
		
		txtType = new JTextField();
		txtType.setEnabled(false);
		txtType.setColumns(10);
		txtType.setBounds(107, 502, 104, 20);
		panel.add(txtType);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(10, 502, 75, 14);
		panel.add(lblType);
		
		JButton btnInsertSize = new JButton("Inds\u00E6t st\u00F8rrelse");
		btnInsertSize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int phone = Integer.parseInt(txtPhoneSize.getText());
				try {
					ctrSize.insertNew(txtSize.getText(), txtType.getText(), phone);
					clearfields();
					listSize.setListData(ctrSize.findAllSizesByCustomer(phone).toArray());
				} catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,
						   e1.getMessage());
				}
			}
		});
		btnInsertSize.setEnabled(false);
		btnInsertSize.setBounds(10, 545, 131, 23);
		panel.add(btnInsertSize);
		
		JButton btnEditSize = new JButton("\u00C6ndre st\u00F8rrelse");
		btnEditSize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sizes size = (sizes) listSize.getModel().getElementAt(listSize.getSelectedIndex());
				txtSize.setText(size.getSize());
				txtType.setText(size.getType());
				
			}
		});
		btnEditSize.setEnabled(false);
		btnEditSize.setBounds(10, 579, 131, 23);
		panel.add(btnEditSize);
		
		JButton btnUpdateSize = new JButton("Opdater st\u00F8rrelse");
		btnUpdateSize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int phone = Integer.parseInt(txtPhoneSize.getText());
				sizes size = (sizes) listSize.getModel().getElementAt(listSize.getSelectedIndex());
				ctrSize.updateSize(size.getId(), txtSize.getText(), txtType.getText(), phone);
				listSize.setListData(ctrSize.findAllSizesByCustomer(phone).toArray());

			}
		});
		btnUpdateSize.setEnabled(false);
		btnUpdateSize.setBounds(162, 579, 131, 23);
		panel.add(btnUpdateSize);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Produkter", null, panel_1, null);
		
		JButton btnFindCustomerSize = new JButton("Find Kunde");
		btnFindCustomerSize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int phone = Integer.parseInt(txtPhoneSize.getText());
				listSize.setListData(ctrSize.findAllSizesByCustomer(phone).toArray());
				
				txtType.setEnabled(true);
				txtSize.setEnabled(true);
				btnEditSize.setEnabled(true);
				btnInsertSize.setEnabled(true);
				btnUpdateSize.setEnabled(true);
			}
		});
		btnFindCustomerSize.setBounds(430, 661, 145, 23);
		panel.add(btnFindCustomerSize);
	}
	
	public void clearfields()
	{
		String t = "";
		txtAdresse.setText(t);
		txtEmail.setText(t);
		txtNavn.setText(t);
		txtPhone.setText(t);
		txtDiscount.setText(t);
		txtTitle.setText(t);
		txtType.setText(t);
		txtSize.setText(t);
	}
}
