package com.bit.springApp.business.abstracts;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Optional;

import com.bit.springApp.domain.CarImage;


public interface CarImageService {
    
    public CarImage saveCar(CarImage carImage);
    
    public Optional<CarImage> getCarImageById(Long carId);
    
    public BufferedImage markCarImage(BufferedImage carImage, int x, int y, int size, Color color);
}