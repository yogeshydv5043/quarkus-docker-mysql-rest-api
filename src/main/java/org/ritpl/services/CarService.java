package org.ritpl.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.ritpl.dto.CarRequest;
import org.ritpl.dto.CarResponse;
import org.ritpl.entity.Car;
import org.ritpl.exception.errormodel.CarDetailsIsBlankException;
import org.ritpl.exception.errormodel.CarListIsEmptyException;
import org.ritpl.exception.errormodel.CarNotFoundException;
import org.ritpl.repository.CarRepository;

import java.util.List;

@ApplicationScoped
public class CarService {

    @Inject
    private CarRepository carRepository;


    //ADD CAR :-
    @Transactional
    public String addCar(CarRequest carRequest) {
        if (carRequest == null) {
            throw new CarDetailsIsBlankException("Car details cannot be null");
        }
        Car car = new Car();
        car.setCarName(carRequest.getCarName());
        car.setCompany(carRequest.getCompany());
        car.setCarColor(carRequest.getCarColor());
        car.setModel(carRequest.getModel());
        car.setModelYear(carRequest.getModelYear());
        car.setPrice(carRequest.getPrice());
        carRepository.persist(car);
        return carRequest.getCarName() + " is added Successfully";
    }

    //GET CAR BY ID :-
    public CarResponse getCarById(Long Id) {
        Car car = carRepository.findByIdOptional(Id).orElseThrow(() -> new CarNotFoundException("Car does not find in this Id : " + Id));
        //  Car car = carOptional.get();
        CarResponse carResponse = new CarResponse();
        carResponse.setCarId(car.getCarId());
        carResponse.setCarName(car.getCarName());
        carResponse.setCompany(car.getCompany());
        carResponse.setModel(car.getModel());
        carResponse.setCarColor(car.getCarColor());
        carResponse.setModelYear(car.getModelYear());
        carResponse.setPrice(car.getPrice());
        carResponse.setAvailable(car.isAvailable());
        return carResponse;
    }

    //UPDATE CAR BY ID :-
    @Transactional
    public String updateCarById(Long Id, CarRequest carRequest) {
        Car carOptional = carRepository.findByIdOptional(Id).orElseThrow(() -> new CarNotFoundException("Car does not find in this Id : " + Id));
        //  Car car = carOptional.get();
        Car car = new Car();
        // carResponse.setCarId(car.getCarId());
        car.setCarName(carRequest.getCarName());
        car.setCompany(carRequest.getCompany());
        car.setModel(carRequest.getModel());
        car.setCarColor(carRequest.getCarColor());
        car.setModelYear(carRequest.getModelYear());
        car.setPrice(carRequest.getPrice());
        carRepository.persist(car);
        return carRequest.getCarName() + " Car is updated successfully ";
    }

    //GET ALL CAR LIST :-
    public List<Car> getAllCarsWithPaginationSortingAndFiltering(int page, int size, String sortField, String sortOrder, String carName, String companyName) {
        List<Car> cars = carRepository.getAllCarsWithPaginationSortingAndFiltering(page, size, sortField, sortOrder, carName, companyName);
        if (cars.isEmpty()) {
            throw new CarListIsEmptyException("Car list is empty !! ");
        }
        return cars;
    }


    //DELETE CAR BY ID :-
    @Transactional
    public String deleteCarById(Long Id) {
        carRepository.findByIdOptional(Id).orElseThrow(() -> new CarNotFoundException("Car does not find in this Id : " + Id));
        carRepository.deleteById(Id);
        return "Car Deleted successfully in this Id " + Id;
    }

}
