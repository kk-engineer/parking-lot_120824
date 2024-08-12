package in.itkaran.parkinglot_120824.repositories;


import in.itkaran.parkinglot_120824.models.Gate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class GateRepository {
    private Map<Long, Gate> gateMap = new HashMap<>();
    private Long previousGateId = 0L;

    public Optional<Gate> findGateById(Long gateId) {
        if (gateMap.containsKey(gateId)) {
            return Optional.of(gateMap.get(gateId));
        }
        return Optional.empty();
    }

    public Gate save(Gate gate) {
        if (gate.getId() == null) {
            previousGateId += 1;
            gate.setId(previousGateId);
        }
        gateMap.put(gate.getId(), gate);
        return gate;
    }

    public Gate delete(Gate gate) {
        gateMap.remove(gate.getId());
        return gate;
    }
}
