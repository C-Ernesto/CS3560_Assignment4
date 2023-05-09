import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageOrder {
	public static void createOrder(LocalDate date, String item, double price, int customerId) {
		
		SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Customer.class).
                addAnnotatedClass(Address.class).
                addAnnotatedClass(Order.class).
                buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			Customer tempCustomer = session.get(Customer.class, customerId);
			
			System.out.println(tempCustomer);
			
			Order tempOrder = new Order(date, item, price);
			
			System.out.println(tempOrder);
			
			tempCustomer.addOrder(tempOrder);
			
			System.out.println("The order for this customer");
			System.out.println(tempCustomer.getOrders());
			
			session.save(tempOrder);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}	
	}
	
	public static Order searchOrder(int search) {
		SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Customer.class).
                addAnnotatedClass(Address.class).
                addAnnotatedClass(Order.class).
                buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		Order result = null;

		try {
			
			session.beginTransaction();
			
			result = session.get(Order.class, search);
			
			session.getTransaction().commit();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
		return result;
	}
	
	public static void updateOrder(Order ord, LocalDate date, String item, double price, Customer cust) {
		SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Customer.class).
                addAnnotatedClass(Address.class).
                addAnnotatedClass(Order.class).
                buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			
			session.beginTransaction();
			
			Order order = session.get(Order.class, ord.getNumber());
			
			order.setDate(date);
			order.setItem(item);
			order.setPrice(price);
			order.setCustomer(cust);
			
			session.getTransaction().commit();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
	
	public static void deleteOrder(int orderNumber) {
		SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Customer.class).
                addAnnotatedClass(Address.class).
                addAnnotatedClass(Order.class).
                buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			
			session.beginTransaction();
			
			Order order = session.get(Order.class, orderNumber);
			
			session.delete(order);
			
			session.getTransaction().commit();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
	
	public static void main(String[] args) {
		//LocalDate date = LocalDate.parse("2022-01-02");
		//createOrder(date,"Soda", 5.18, 2);
		//LocalDate date = LocalDate.parse("2022-02-16");
		//createOrder(date,"Burger", 10.01, 2);
		//System.out.println(searchOrder(3));
		//deleteOrder(3);
		
	}
}
