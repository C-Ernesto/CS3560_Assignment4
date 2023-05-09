import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerUI extends JFrame{
	
	private JPanel panel;
	private JPanel customer;
	private JPanel address;
	private JPanel buttons;
	private JLabel name_label;
	private JLabel phone_label;
	private JLabel email_label;
	private JLabel street_label;
	private JLabel city_label;
	private JLabel state_label;
	private JLabel zipcode_label;
	private JTextField nameTextField;
	private JTextField phoneTextField;
	private JTextField emailTextField;
	private JTextField streetTextField;
	private JTextField cityTextField;
	private JTextField stateTextField;
	private JTextField zipcodeTextField;
	private JButton searchButton;
	private JButton addButton;
	private JButton updateButton;
	private JButton deleteButton;
	private Customer currentCustomer;
	
	
	public CustomerUI()
	 {
		 final int WINDOW_WIDTH = 800; // Window width in pixels
		 final int WINDOW_HEIGHT = 500; // Window height in pixels
		
		 // Set this window's title.
		 setTitle("Customer");
		
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
		
		buildPanelCustomer();
		
		buildPanelAddress();
		
		buildPanelButton();
		
		panel.add(customer);
		panel.add(address);
		panel.add(buttons);
	 }
	
	private void buildPanelCustomer() {
		
		name_label = new JLabel("Name");
		phone_label = new JLabel("Phone");
		email_label = new JLabel("Email");
		
		nameTextField = new JTextField(10);
		phoneTextField = new JTextField(10);
		emailTextField = new JTextField(10);
		
		customer = new JPanel();
		customer.add(name_label);
		customer.add(nameTextField);
		customer.add(phone_label);
		customer.add(phoneTextField);
		customer.add(email_label);
		customer.add(emailTextField);
	 }
	
	private void buildPanelAddress() {
		
		street_label = new JLabel("Street");
		state_label = new JLabel("State");
		city_label = new JLabel("City");
		zipcode_label = new JLabel("Zip Code");
		
		streetTextField = new JTextField(10);
		stateTextField = new JTextField(10);
		cityTextField = new JTextField(10);
		zipcodeTextField = new JTextField(10);
		
		address = new JPanel();
		
		address.add(street_label);
		address.add(streetTextField);
		address.add(city_label);
		address.add(cityTextField);
		address.add(state_label);
		address.add(stateTextField);
		address.add(zipcode_label);
		address.add(zipcodeTextField);
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
			 String name; 
			 String phone; 
			 String email;
			 String street;
			 String city;
			 String state;
			 int zipCode;
			 
			 // get all parameters
			 name = nameTextField.getText();
			 phone = phoneTextField.getText();
			 email = emailTextField.getText();
			 street = streetTextField.getText();
			 city = cityTextField.getText();
			 state = stateTextField.getText();
			 zipCode = Integer.parseInt(zipcodeTextField.getText());
			 
			 // call createCustomer
			 ManageCustomer.createCustomer(name, phone, email, street, city, state, zipCode);
			 
			 // clear fields
			 nameTextField.setText("");
			 phoneTextField.setText("");
			 emailTextField.setText("");
			 streetTextField.setText("");
			 cityTextField.setText("");
			 stateTextField.setText("");
			 zipcodeTextField.setText("");
			 
			 // clear current customer
			 currentCustomer = null;
			 
			 JOptionPane.showMessageDialog(null, "Customer successfully included");
		 }
	 }
	
	private class SearchListener implements ActionListener {
		
		 public void actionPerformed(ActionEvent e)
		 {
			 // Get customer with name
			 Customer result = ManageCustomer.searchCustomer(nameTextField.getText());
			 if (result != null) {
				 currentCustomer = result;
				 Address address = result.getAddress();
				 
				 // set all text boxes
				 nameTextField.setText(result.getName());
				 phoneTextField.setText(result.getPhone());
				 emailTextField.setText(result.getEmail());
				 streetTextField.setText(address.getStreet());
				 cityTextField.setText(address.getCity());
				 stateTextField.setText(address.getState());
				 zipcodeTextField.setText("" + address.getZipCode());
			 } else {
				 nameTextField.setText("");
				 phoneTextField.setText("");
				 emailTextField.setText("");
				 streetTextField.setText("");
				 cityTextField.setText("");
				 stateTextField.setText("");
				 zipcodeTextField.setText("");
				 JOptionPane.showMessageDialog(null, "No records found");
			 }
		 }
	 }
	
	private class UpdateListener implements ActionListener {
		
		 public void actionPerformed(ActionEvent e)
		 {
			 // check if the user has searched before
			 if (currentCustomer != null) {
				 
				// initialize
				 String name; 
				 String phone; 
				 String email;
				 String street;
				 String city;
				 String state;
				 int zipCode;
				 
				 // get all parameters
				 name = nameTextField.getText();
				 phone = phoneTextField.getText();
				 email = emailTextField.getText();
				 street = streetTextField.getText();
				 city = cityTextField.getText();
				 state = stateTextField.getText();
				 zipCode = Integer.parseInt(zipcodeTextField.getText());
				 
				 // call updateCustomer
				 ManageCustomer.updateCustomer(currentCustomer, name, phone, email, street, city, state, zipCode);
				 
				 nameTextField.setText("");
				 phoneTextField.setText("");
				 emailTextField.setText("");
				 streetTextField.setText("");
				 cityTextField.setText("");
				 stateTextField.setText("");
				 zipcodeTextField.setText("");
				 
				 JOptionPane.showMessageDialog(null, "Customer data successfully updated");
			 } else {
				 JOptionPane.showMessageDialog(null, "Please search the customer");
			 }
			 
			 //clear current customer
			 currentCustomer = null;
		 }
	 }
	
	private class DeleteListener implements ActionListener {
		
		 public void actionPerformed(ActionEvent e)
		 {
			 // check if the user has searched before
			 if (currentCustomer != null) {
				 
				 // delete customer
				 ManageCustomer.deleteCustomer(currentCustomer);
				 
				 JOptionPane.showMessageDialog(null, "Customer successfully deleted");
			 } else {
				 JOptionPane.showMessageDialog(null, "Please search the customer");
			 }
			 
			 // clear fields
			 nameTextField.setText("");
			 phoneTextField.setText("");
			 emailTextField.setText("");
			 streetTextField.setText("");
			 cityTextField.setText("");
			 stateTextField.setText("");
			 zipcodeTextField.setText("");
			 
			 //clear current customer
			 currentCustomer = null;
		 }
	 }
}
