package in.itkaran.parkinglot_120824.services;


import in.itkaran.parkinglot_120824.exceptions.GateNotFoundException;
import in.itkaran.parkinglot_120824.models.*;
import in.itkaran.parkinglot_120824.repositories.GateRepository;
import in.itkaran.parkinglot_120824.repositories.ParkingLotRepository;
import in.itkaran.parkinglot_120824.repositories.TicketRepository;
import in.itkaran.parkinglot_120824.repositories.VehicleRepository;
import in.itkaran.parkinglot_120824.services.factory.SpotAssignmentStrategyFactory;
import in.itkaran.parkinglot_120824.services.strategies.SpotAssignmentStrategy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;


    public TicketService(TicketRepository ticketRepository,
                         GateRepository gateRepository,
                         VehicleRepository vehicleRepository,
                         ParkingLotRepository parkingLotRepository) {
        this.ticketRepository = ticketRepository;
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public Ticket issueTicket(Long gateId,
                              String vehicleNumber,
                              VehicleType vehicleType,
                              String ownerName) throws GateNotFoundException {
        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        // find gate by id
        Optional<Gate> optionalGate = gateRepository.findGateById(gateId);
        if (optionalGate.isEmpty()) {
            throw new GateNotFoundException("Gate not found with id: " + gateId);
        }
        Gate gate = optionalGate.get();
        ticket.setGeneratedAt(gate);
        ticket.setGeneratedBy(gate.getOperator());

        // Get the vehicle object with number
        // If vehicle not found, create a new vehicle object
        // and save it in the vehicle repository
        Optional<Vehicle> optionalVehicle = vehicleRepository.findVehicleByNumber(vehicleNumber);
        Vehicle savedVehicle = null;
        if (optionalVehicle.isEmpty()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setNumber(vehicleNumber);
            vehicle.setOwnerName(ownerName);
            vehicle.setType(vehicleType);
            savedVehicle = vehicleRepository.save(vehicle);
        } else {
            savedVehicle = optionalVehicle.get();
        }

        ticket.setVehicle(savedVehicle);

        // assign the spot to the vehicle
        Long parkingLotId = gate.getParkingLot().getId();
        ParkingLot parkingLot = parkingLotRepository.findParkingLotById(parkingLotId);
        SpotAssignmentStrategyType spotAssignmentStrategyType = parkingLot.getSpotAssignmentStrategyType();
        SpotAssignmentStrategy spotAssignmentStrategy =
                SpotAssignmentStrategyFactory.getSpotAssignmentStrategy(spotAssignmentStrategyType);
        ParkingSpot parkingSpot = spotAssignmentStrategy.assignSpot(vehicleType, gate);
        ticket.setParkingSpot(parkingSpot);
        // Set a random alphanumeric string for the ticket
        ticket.setNumber(RandomStringGenerationService.generateRandomAlphanumericString());
        return ticketRepository.save(ticket);
    }
}
