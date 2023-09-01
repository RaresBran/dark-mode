package com.app.dark.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarAndDarkModeDto {
    private Boolean isDarkMode;
    List<CarDto> cars;
}
