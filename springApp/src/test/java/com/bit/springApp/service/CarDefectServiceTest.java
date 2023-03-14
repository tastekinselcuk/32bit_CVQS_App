//package com.bit.springApp.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.bit.springApp.business.abstracts.CarDefectService;
//import com.bit.springApp.dto.CarDefectDTO;
//import com.bit.springApp.repository.CarRepository;
//import com.bit.springApp.repository.DefectRepository;
//import com.bit.springApp.repository.LocationRepository;
//import com.bit.springApp.repository.TerminalRepository;
//
//@SpringBootTest
//public class CarDefectServiceTest {
//
//    @Autowired
//    private CarDefectService carDefectService;
//
//    @Autowired
//    private DefectRepository defectRepository;
//
//    @Autowired
//    private CarRepository carRepository;
//
//    @Autowired
//    private LocationRepository locationRepository;
//
//    @Autowired
//    private TerminalRepository terminalRepository;
//
//    @Test
//    public void testSaveCarDefect() {
//        // Given
//        int carId = 1;
//        String defectPartCategory = "Engine";
//        String defectPartName = "Oil leak";
//        String reportedBy = "John Doe";
//        double latitude = 40.0;
//        double longitude = 30.0;
//        String terminalName = "Terminal A";
//
//        // When
//        carDefectService.saveCarDefect(carId, defectPartCategory, defectPartName, reportedBy, latitude, longitude,
//                terminalName);
//
//        // Then
//        assertEquals(1, defectRepository.count()); // Only one defect should be saved
//        assertEquals(1, carRepository.count()); // Only one car should be saved
//        assertEquals(1, locationRepository.count()); // Only one location should be saved
//        assertEquals(1, terminalRepository.count()); // Only one terminal should be saved
//    }
//
//    @Test
//    public void testGetCarDefects() {
//        // Given
//        int carId = 1;
//        String defectPartCategory = "Engine";
//        String defectPartName = "Oil leak";
//        String reportedBy = "John Doe";
//        double latitude = 40.0;
//        double longitude = 30.0;
//        String terminalName = "Terminal A";
//        carDefectService.saveCarDefect(carId, defectPartCategory, defectPartName, reportedBy, latitude, longitude,
//                terminalName);
//
//        // When
//        List<CarDefectDTO> carDefects = carDefectService.getCarDefects();
//
//        // Then
//        assertEquals(1, carDefects.size()); // Only one defect should be retrieved
//        CarDefectDTO dto = carDefects.get(0);
//        assertEquals(carId, dto.getCarId());
//        assertEquals(defectPartCategory, dto.getDefectPartCategory());
//        assertEquals(defectPartName, dto.getDefectPartName());
//        assertEquals(reportedBy, dto.getReportedBy());
//        assertEquals(latitude, dto.getLatitude());
//        assertEquals(longitude, dto.getLongitude());
//        assertEquals(terminalName, dto.getTerminalName());
//    }
//
//}
//
