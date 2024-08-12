package in.itkaran.parkinglot_120824.services.factory;


import in.itkaran.parkinglot_120824.models.SpotAssignmentStrategyType;
import in.itkaran.parkinglot_120824.services.strategies.CheapestSpotAssignmentStrategy;
import in.itkaran.parkinglot_120824.services.strategies.NearestSpotAssignmentStrategy;
import in.itkaran.parkinglot_120824.services.strategies.RandomSpotAssignmentStrategy;
import in.itkaran.parkinglot_120824.services.strategies.SpotAssignmentStrategy;

public class SpotAssignmentStrategyFactory {
    public static SpotAssignmentStrategy getSpotAssignmentStrategy(SpotAssignmentStrategyType spotAssignmentStrategyType) {
        if (spotAssignmentStrategyType == SpotAssignmentStrategyType.NEAREST) {
            return new NearestSpotAssignmentStrategy();
        } else if (spotAssignmentStrategyType == SpotAssignmentStrategyType.FLOOR_WISE) {
            return null;
        } else if (spotAssignmentStrategyType == SpotAssignmentStrategyType.CHEAPEST) {
            return new CheapestSpotAssignmentStrategy();
        } else if (spotAssignmentStrategyType == SpotAssignmentStrategyType.RANDOM) {
            return new RandomSpotAssignmentStrategy();
        }

        return null;
    }
}
