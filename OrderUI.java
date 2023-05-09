import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class OrderUI extends JFrame{
	private JPanel panel;
	private JPanel order;
	private JPanel buttons;
	
	private JLabel number_label;
	private JLabel date_label;
	private JLabel customer_label;
	private JLabel item_label;
	private JLabel price_label;

	private JTextField numberTextField;
	private JTextField dateTextField;
	private JComboBox customerComboBox;
	private JComboBox itemComboBox;
	private JTextField priceTextField;

	private JButton searchButton;
	private JButton addButton;
	private JButton updateButton;
	private JButton deleteButton;
	
	private Order currentOrder;
	
	
	public OrderUI()
	 {
		 final int WINDOW_WIDTH = 800; // Window width in pixels
		 final int WINDOW_HEIGHT = 500; // Window height in pixels
		
		 // Set this window's title.
		 setTitle("Order");
		
		 // Set the size of this window.
		 setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		 // Specify what happens when the close button is clicked.
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 buildPanel();
		 
		 add(panel);
		
		 // Display the window.
		 setVisible(true);
	 }
	
	private void buildPanel() {
		
		panel = new JPanel();
		
		buildPanelOrder();
		
		buildPanelButton();
		
		panel.add(order);
		panel.add(buttons);
	 }
	
	private void buildPanelOrder() {
		
		number_label = new JLabel("Number");
		date_label = new JLabel("Date (yyyy-MM-dd)");
		customer_label = new JLabel("Customer");
		item_label = new JLabel("Item");
		price_label = new JLabel("Price");
		
		
		List<String> names = ManageCustomer.listNames();
		String[] items = {"Caesar Salad", "Greek Salad", "Cobb Salad"};
		
		numberTextField = new JTextField(10);
		dateTextField = new JTextField(10);
		customerComboBox = new JComboBox(names.toArray());
		itemComboBox = new JComboBox(items);
		priceTextField = new JTextField(10);
		
		order = new JPanel();
		order.add(number_label);
		order.add(numberTextField);
		order.add(date_label);
		order.add(dateTextField);
		order.add(customer_label);
		order.add(customerComboBox);
		order.add(item_label);
		order.add(itemComboBox);
		order.add(price_label);
		order.add(priceTextField);
	 }
	
	private void buildPanelButton() {
		
		searchButton = new JButton("Search");
		addButton = new JButton("Add");
		updateButton = new JButton("Update");
		deleteButton = new JButton("Delete");
		
		buttons = new JPanel();
		
		buttons.add(searchButton);
		buttons.add(addButton);
		buttons.add(updateButton);
		buttons.add(deleteButton);
		
		addButton.addActionListener(new AddListener());
		searchButton.addActionListener(new SearchListener());
		updateButton.addActionListener(new UpdateListener());
		deleteButton.addActionListener(new DeleteListener());
	 }
	
	private class AddListener implements ActionListener {
		
		 public void actionPerformed(ActionEvent e)
		 {
			 // initialize
			 int number; 		// number is technically not needed since number is auto generated
			 String date; 
			 Customer customer;
			 String item;
			 double price;
			 
			 // get all parameters
			 number = Integer.parseInt(numberTextField.getText());
			 date = dateTextField.getText();
			 String name = (String) customerComboBox.getSelectedItem();
			 customer = ManageCustomer.searchCustomer(name);
			 item = (String) itemComboBox.getSelectedItem();
			 price = Double.parseDouble(priceTextField.getText());
			 
			 //Parse date
			 DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			 LocalDate parsedDate = LocalDate.parse(date, df);
			 
			 // call createOrder
			 ManageOrder.createOrder(parsedDate, item, price, customer.getId());
			 
			 // clear fields
			 numberTextField.setText("");
			 dateTextField.setText("");
			 customerComboBox.setSelectedIndex(0);
			 itemComboBox.setSelectedIndex(0);
			 priceTextField.setText("");
			 
			 // clear current order
			 currentOrder = null;
			 
			 
			 JOptionPane.showMessageDialog(null, "Order successfully included");
		 }
	 }
	
	private class SearchListener implements ActionListener {
		
		 public void actionPerformed(ActionEvent e)
		 {
			 // Get order with number
			 Order result = ManageOrder.searchOrder(Integer.parseInt(numberTextField.getText()));
			 if (result != null) {
				 currentOrder = result;
				 Customer cust = result.getCustomer();
				 
				 // set all text boxes
				 numberTextField.setText("" + result.getNumber());
				 dateTextField.setText(result.getDate().toString());
				 customerComboBox.setSelectedItem(cust.getName());
				 itemComboBox.setSelectedItem(result.getItem());
				 priceTextField.setText("" + result.getPrice());
			 } else {
				 numberTextField.setText("");
				 dateTextField.setText("");
				 customerComboBox.setSelectedIndex(0);
				 itemComboBox.setSelectedIndex(0);
				 priceTextField.setText("");
				 JOptionPane.showMessageDialog(null, "No records found");
			 }
		 }
	 }
	
	private class UpdateListener implements ActionListener {
		
		 public void actionPerformed(ActionEvent e)
		 {
			 // check if the user has searched before
			 if (currentOrder != null) {
				 
				// initialize
				 int number; 
				 String date; 
				 Customer customer;
				 String item;
				 double price;
				 
				 // get all parameters
				 number = Integer.parseInt(numberTextField.getText());
				 date = dateTextField.getText();
				 String name = (String) customerComboBox.getSelectedItem();
				 customer = ManageCustomer.searchCustomer(name);
				 item = (String) itemComboBox.getSelectedItem();
				 price = Double.parseDouble(priceTextField.getText());
				 
				//Parse date
				 DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				 LocalDate parsedDate = LocalDate.parse(date, df);
				 
				 // call updateOrder
				 ManageOrder.updateOrder(currentOrder, parsedDate, item, price, customer);
				 
				 numberTextField.setText("");
				 dateTextField.setText("");
				 customerComboBox.setSelectedIndex(0);
				 itemComboBox.setSelectedIndex(0);
				 priceTextField.setText("");
				 
				 JOptionPane.showMessageDialog(null, "Order data successfully updated");
			 } else {
				 JOptionPane.showMessageDialog(null, "Please search an order");
			 }
			 
			 //clear current customer
			 currentOrder = null;
		 }
	 }
	
	private class DeleteListener implements ActionListener {
		
		 public void actionPerformed(ActionEvent e)
		 {
			 // check if the user has searched before
			 if (currentOrder != null) {
				 
				 // delete customer
				 ManageOrder.deleteOrder(currentOrder.getNumber());
				 
				 JOptionPane.showMessageDialog(null, "Order successfully deleted");
			 } else {
				 JOptionPane.showMessageDialog(null, "Please search an order");
			 }
			 
			 numberTextField.setText("");
			 dateTextField.setText("");
			 customerComboBox.setSelectedIndex(0);
			 itemComboBox.setSelectedIndex(0);
			 priceTextField.setText("");
			 
			 //clear current customer
			 currentOrder = null;
		 }
	 }
	
	
}
