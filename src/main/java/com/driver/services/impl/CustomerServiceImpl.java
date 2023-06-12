package com.driver.services.impl;

import com.driver.model.TripBooking;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.model.TripStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
        Customer customer=customerRepository2.findById(customerId).get();
		customerRepository2.delete(customer);
	}

	@Override
	public TripBooking  bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
		List<Driver> driverList=driverRepository2.findAll();
		int min=Integer.MAX_VALUE;
		Driver driver = null;
		for (Driver driver1:driverList){
			if(driver1.getDriverId()<min && driver1.getCab().getAvailable()){
				min=driver1.getDriverId();
				driver=driver1;
			}
		}
		if (driver==null){
			throw  new RuntimeException("No cab available!");
		}
		TripBooking bookedTrip=new TripBooking();
		bookedTrip.setDistanceInKm(distanceInKm);
		bookedTrip.setFromLocation(fromLocation);
		bookedTrip.setDistanceInKm(distanceInKm);
		List<TripBooking> driverTrips=driver.getTripBookingList();
		driverTrips.add(bookedTrip);
		driver.getCab().setAvailable(true);
		driver.setTripBookingList(driverTrips);
		driverRepository2.save(driver);

		Customer customer= customerRepository2.findById(customerId).get();
		List<TripBooking>  list=customer.getTripBookingList();
		list.add(bookedTrip);
		customer.setTripBookingList(list);
		customerRepository2.save(customer);

		return bookedTrip;
	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly

	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly

	}
}
