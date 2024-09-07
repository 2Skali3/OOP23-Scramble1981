package scramble.controller.mediator.api;

import scramble.model.spaceship.FuelBar;

/**
 * Interface for handling game objects collisions.
 */
public interface LogicController {

    /**
     * Check if a collision is happenning between landscape and spaceship.
     */
    void touchedGround();
    
    void touchedEnemy();

    /**
     * Behaviour for case when fuel is depleted.
     *
     * @param fuelBar the fuel bar
     */
    void finishedFuel(FuelBar fuelBar);

}
