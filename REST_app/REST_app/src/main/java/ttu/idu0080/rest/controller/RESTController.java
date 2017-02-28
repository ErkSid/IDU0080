package ttu.idu0080.rest.controller;

import java.io.IOException;

import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.ui.Model;

import ttu.idu0080.rest.service.*;
import ttu.idu0080.rest.data.*;


@Controller
public class RESTController {
	
	@Autowired
	private DataService dataService;
	@Autowired
	private RESTDataService restDataService;

	@RequestMapping(value="/service/cars",method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Car> getCars(HttpServletResponse response) throws IOException{
		
		List<Car> cars = dataService.getAllCars();
		return cars;
	}
	
	@RequestMapping(value="/service/car/{id}",method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Car getCar(@PathVariable int id) throws IOException{
		
		Car car = dataService.getCarById(id);
		return car;
	}
	
	@Transactional
	@RequestMapping(value = "/service/car/{id}", method=RequestMethod.POST)
	public @ResponseBody void updateCar(@RequestBody Car car)
	{
		dataService.update(car);
		
	}

	@Transactional
	@RequestMapping(value = "/service/car/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteCar(@PathVariable int id) {
		dataService.delete(id);
	}
	
	@Transactional
	@RequestMapping(value = "/service/car/", method = RequestMethod.PUT)
	public @ResponseBody void insertCar(@RequestBody Car car) {
		dataService.save(car);
	}
	
	@RequestMapping(value="/service/external/cars",method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Car> getExternalCars(HttpServletResponse response) throws IOException{
		List<Car> cars = restDataService.getAllCars();
		return cars;
	}
	
	@RequestMapping(value = "/service/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Car> searchCars(@RequestParam(value = "model") String model) throws IOException {
		List<Car> cars = dataService.searchByModel(model);
		return cars;
	}
	

	
}
