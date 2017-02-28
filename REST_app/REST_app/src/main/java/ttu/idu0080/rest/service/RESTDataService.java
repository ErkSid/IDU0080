package ttu.idu0080.rest.service;

import ttu.idu0080.rest.data.*;

import java.util.*;
import java.text.*;
import java.util.List;

import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;;
@Service
public class RESTDataService  {



	
	
	public List<Car> getAllCars()  {
		
		Car[] car_array = null;
		try
		{
			RestTemplate restTemplate = new RestTemplate();
		car_array = restTemplate.getForObject("http://localhost:8080/REST_service/service/cars", Car[].class) ;
		System.out.println("Autosid REST-teenusest:" + car_array.length);
		}
		catch(Exception ex)
		{
			System.out.println("RESTDataService.getAllCars():"+ ex.getMessage());
		}

		List<Car> car_list= Arrays.asList(car_array);
		return car_list;
	}

	
	
	
}
