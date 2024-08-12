package in.itkaran.parkinglot_120824;

import in.itkaran.parkinglot_120824.models.*;
import in.itkaran.parkinglot_120824.repositories.GateRepository;
import in.itkaran.parkinglot_120824.repositories.ParkingFloorRepository;
import in.itkaran.parkinglot_120824.repositories.ParkingLotRepository;
import in.itkaran.parkinglot_120824.repositories.ParkingSpotRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Parkinglot120824ApplicationTests {

    @Autowired
    ParkingSpotRepository parkingSpotRepository;
    @Autowired
    ParkingFloorRepository parkingFloorRepository;
    @Autowired
    ParkingLotRepository parkingLotRepository;
    @Autowired
    GateRepository gateRepository;


    @Test
    void contextLoads() {
    }

}
