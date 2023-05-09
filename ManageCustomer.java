import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageCustomer {
	
	public static void createCustomer(String name, String phone, String email, String street, String city, String state, int zipCode) {
		
		SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Customer.class).
                addAnnotatedClass(Address.class).
                addAnnotatedClass(Order.class).
                buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
		
			session.beginTransaction();
			
			Customer tempCustomer = new Customer(name, phone, email);
			
			Address address = new Address(street, city, state, zipCode);
			
			tempCustomer.setAddress(address);
			
			session.save(tempCustomer);
			
			session.getTransaction().commit();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}	
	}
	
	public static void updateCustomer(Customer cust, String name, String phone, String email, String street, String city, String state, int zipCode) {
		SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Customer.class).
                addAnnotatedClass(Address.class).
                addAnnotatedClass(Order.class).
                buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			
			session.beginTransaction();
			
			Customer customer = session.get(Customer.class, cust.getId());
			Address address = customer.getAddress();
			
			customer.setName(name);
			customer.setPhone(phone);
			customer.setEmail(email);
			address.setStreet(street);
			address.setCity(city);
			address.setState(state);
			address.setZipCode(zipCode);
			
			session.getTransaction().commit();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
	
	public static Customer searchCustomer(String search) {
		SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Customer.class).
                addAnnotatedClass(Address.class).
                addAnnotatedClass(Order.class).
                buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		Customer result = null;

		try {
			
			session.beginTransaction();
			
			String hql = "from Customer c where c.name=:searchName";
			
			List<Customer> listCustomers = session.createQuery(hql).setParameter("searchName", search).list();
			
			for (Customer cust: listCustomers) {
				if (cust.getName().equals(search)) {
					System.out.println(cust);
					return(cust);
				}
			}
			
			session.getTransaction().commit();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
		return null;
	}
	
	public static Customer deleteCustomer(Customer cust) {
		SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Customer.class).
                addAnnotatedClass(Address.class).
                addAnnotatedClass(Order.class).
                buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			
			session.beginTransaction();
			
			Customer customer = session.get(Customer.class, cust.getId());
			session.delete(customer);
			
			session.getTransaction().commit();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
		return null;
	}
	
	public static List<String> listNames() {
		SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Customer.class).
                addAnnotatedClass(Address.class).
                addAnnotatedClass(Order.class).
                buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		List<String> result = null;
		
		try {
			
			session.beginTransaction();
			
			List<Customer> customers = session.createQuery("from Customer").getResultList();
			
			if (customers != null) {
			
				result = new ArrayList<String>();
				
				for (Customer cust: customers) {
					result.add(cust.getName());
				}
				
				session.getTransaction().commit();
			} 
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Customer cust;
		//createCustomer("John", "88772182", "john@cpp.edu", "Rabbit st.", "Tokyo", "Japan", 91109);
		//createCustomer("Mary", "987654321", "mary@cpp.edu", "Bear st.", "LA", "California", 14520);
		//cust = searchCustomer("John");
		//updateCustomer(cust, "Johnny", "88772182", "Johnny@cpp.edu", "Tiger st.", "Austin", "Texas", 14520);
		//cust = searchCustomer("Johnny");
		//deleteCustomer(cust);
		System.out.println(listNames());
	}
}
