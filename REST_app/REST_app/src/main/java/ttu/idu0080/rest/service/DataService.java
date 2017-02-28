package ttu.idu0080.rest.service;

import ttu.idu0080.rest.data.*;

import java.util.*;
import java.text.*;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.transaction.annotation.Propagation;

import javax.persistence.EntityTransaction;
@Repository
public class DataService  {




	@PersistenceContext
	private EntityManager em;

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}


	public Car getCarById(long id)  {


		Car node = null;
		try {


			Query q =  em
					.createQuery(
							"select c from Car c where c.id = :id")
							.setParameter("id", id);
			node = (Car) q.getSingleResult();


		}

		catch(Exception ex)
		{
			System.out.println("DataService.getCarById():"+ ex.getMessage());
		
		}

		return node;
	}


	
	
	
	public List<Car> getAllCars()  {


		List<Car> car_list = null;
		try {


			Query q =  em
					.createQuery(
							"select c from Car c ");
			car_list = (List<Car>)  q.getResultList();


		}

		catch(Exception ex)
		{
			System.out.println("DataService.getAllCars():"+ ex.getMessage());
		}

		return car_list;
	}

	
	
	public Car update(Car car)  {


		System.out.println("car update , car model: " + car.getModel());
		try {

			em.merge(car);
			em.flush();

		}

		catch(Exception ex)
		{
			System.out.println("DataService.update():"+ ex.getMessage());
		}

		return car;
	}

	
	public Car save(Car car) {

		System.out.println("new car insert , car model: " + car.getModel());

		try {
			
			em.persist(car);


		}

		catch(Exception ex)
		{
			System.out.println("DataService.save():"+ ex.getMessage());
		}

		return car;
	}
	
	public void delete(long id) {

		System.out.println("DELETE ");

		try {
			
	          Car car =  em.find(Car.class,id);
	          em.remove(car);


		}

		catch(Exception ex)
		{
			System.out.println("DataService.delete():"+ ex.getMessage());
		}


	}
	
	public  List<Car> searchByModel(String s_model)  {

		List<Car> cars = null;

		try {

			String sql = "from Car c where upper(c.model) like upper(:model) order by c.make";
            
			Query q = em.createQuery(sql);	
			q.setParameter("model", s_model+"%") ;                  
			cars =  (List<Car>) q.getResultList();	 				
                        System.out.println("Otsingu tulemusi:" + cars.size());

		}

		catch(Exception ex)
		{
			System.out.println("DataService.searchByModel():" + ex.getMessage());

		}

		return cars;
	}
	
	
}
