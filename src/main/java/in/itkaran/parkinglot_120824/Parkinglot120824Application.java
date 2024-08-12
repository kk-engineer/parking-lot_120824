package in.itkaran.parkinglot_120824;

import in.itkaran.parkinglot_120824.controllers.TicketController;
import in.itkaran.parkinglot_120824.dtos.IssueTicketRequestDto;
import in.itkaran.parkinglot_120824.dtos.IssueTicketResponseDto;
import in.itkaran.parkinglot_120824.models.*;
import in.itkaran.parkinglot_120824.repositories.GateRepository;
import in.itkaran.parkinglot_120824.repositories.ParkingFloorRepository;
import in.itkaran.parkinglot_120824.repositories.ParkingLotRepository;
import in.itkaran.parkinglot_120824.repositories.ParkingSpotRepository;
import in.itkaran.parkinglot_120824.services.RandomStringGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Parkinglot120824Application implements CommandLineRunner {

    @Autowired
    private TicketController ticketController;
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private ParkingFloorRepository parkingFloorRepository;
    @Autowired
    private GateRepository gateRepository;

    public static void main(String[] args) {
        SpringApplication.run(Parkinglot120824Application.class, args);
    }


    private static void demoRandomString() {
        System.out.println("Random String: " + RandomStringGenerationService.generateRandomAlphanumericString());
    }

    @Override
    public void run(String... args) throws Exception {
        createTestData();

        IssueTicketRequestDto requestDto = new IssueTicketRequestDto();
        requestDto.setGateId(123L);
        requestDto.setVehicleType(VehicleType.FOUR_WHEELER);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("##############################################");
            System.out.println("Enter vehicle number: ");
            String vehicleNumber = scanner.nextLine();
            requestDto.setVehicleNumber(vehicleNumber);
            System.out.println("Enter owner name: ");
            String ownerName = scanner.nextLine();
            requestDto.setOwnerName(ownerName);
            IssueTicketResponseDto responseDto = ticketController.issueTicket(requestDto);
            Ticket ticket = responseDto.getTicket();
            System.out.println("Ticket details: " + ticket.toString());
        }

    }

    private void createTestData() {
        // create parking spot
        List<VehicleType> supportedVehicleTypes = List.of(VehicleType.FOUR_WHEELER, VehicleType.TWO_WHEELER);
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        ParkingSpot parkingSpot1 = new ParkingSpot();
        parkingSpot1.setSupportedVehicleTypes(supportedVehicleTypes);
        parkingSpot1.setStatus(ParkingSpotStatus.VACANT);
        parkingSpot1.setSpotNumber("A1");
        parkingSpots.add(parkingSpot1);
        parkingSpotRepository.save(parkingSpot1);

        // Create parking floor
        ParkingFloor parkingFloor1 = new ParkingFloor();
        parkingFloor1.setFloorNumber(1);
        parkingFloor1.setParkingSpots(parkingSpots);
        parkingFloor1.setParkingFloorStatus(ParkingFloorStatus.OPERATIONAL);
        parkingFloorRepository.save(parkingFloor1);

        // Create parking lot
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Phoenix Mall");
        parkingLot.setParkingFloors(List.of(parkingFloor1));
        parkingLot.setParkingLotStatus(ParkingLotStatus.OPEN);
        parkingLot.setSupportedVehicleTypes(supportedVehicleTypes);
        parkingLot.setSpotAssignmentStrategyType(SpotAssignmentStrategyType.RANDOM);
        parkingLot.setFeeCalculationStrategyType(FeeCalculationStrategyType.WEEKDAY);
        parkingLotRepository.save(parkingLot);

        // create operator
        Operator operator = new Operator();
        operator.setName("Robot 123");
        operator.setEmpId(555);

        // Create gate
        Gate gate = new Gate();
        gate.setParkingLot(parkingLot);
        gate.setGateType(GateType.ENTRY);
        gate.setOperator(operator);
        gate.setGateStatus(GateStatus.OPEN);
        gate.setId(123L);
        gateRepository.save(gate);
    }
}
