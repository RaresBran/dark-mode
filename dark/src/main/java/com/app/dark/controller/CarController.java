package com.app.dark.controller;

import com.app.dark.dto.CarAndDarkModeDto;
import com.app.dark.dto.CarDto;
import com.app.dark.dto.DarkModeDto;
import com.app.dark.model.Car;
import com.app.dark.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public ResponseEntity<CarAndDarkModeDto> getAllCars(@CookieValue(name = "darkMode", defaultValue = "false") String isDarkMode) {
        return ResponseEntity.ok(carService.findAll(isDarkMode));
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody @Validated CarDto carDto) {
        Car savedCar = carService.save(carDto);

        if (savedCar != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/dark-mode")
    public ResponseEntity<String> setDarkMode(@RequestBody @Validated DarkModeDto darkModeDto) {
        boolean darkModePreference = darkModeDto.getState();

        ResponseCookie cookie = ResponseCookie.from("darkMode", String.valueOf(darkModePreference))
                .path("/")
                .build();

        String message = "Dark Mode preference has been set.";
        String jsonResponse = "{\"message\": \"" + message + "\"}";

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(jsonResponse);
    }
}
