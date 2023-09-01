package com.app.dark.service;

import com.app.dark.dto.CarAndDarkModeDto;
import com.app.dark.dto.CarDto;
import com.app.dark.model.Car;
import com.app.dark.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarAndDarkModeDto findAll(String isDarkMode) {
        List<Car> cars = carRepository.findAll();
        List<CarDto> carDtos = cars.stream()
                .map(car -> new CarDto(car.getName(), car.getModel()))
                .toList();
        return new CarAndDarkModeDto(Boolean.valueOf(isDarkMode), carDtos);
    }

    public Car save(CarDto carDto) {
        return carRepository.save(new Car(null, carDto.getName(), carDto.getModel()));
    }
}
