package scramble.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import scramble.model.spaceship.FuelBar;
import scramble.utility.Constants;

class FuelBarTest {
    @Test
    void testInitialFuelLevel() {
        final FuelBar fuelBar = new FuelBar();
        assertEquals(Constants.MAX_FUEL, fuelBar.getFuelLevel());
    }

    @Test
    void testDecreaseFuelLevelCorrectly() {
        final FuelBar fuelBar = new FuelBar();
        final int initialFuelLevel = fuelBar.getFuelLevel();
        final int amountToDecrease = Constants.FUEL_DECREASE_AMOUNT;
        fuelBar.decreaseFuel(amountToDecrease);
        assertEquals(initialFuelLevel - amountToDecrease, fuelBar.getFuelLevel());
    }

    @Test
    void testIncreaseFuelLevelCorrectly() {
        final FuelBar fuelBar = new FuelBar();
        final int amountToDecrease = Constants.FUEL_REFILL;
        fuelBar.decreaseFuel(amountToDecrease);
        fuelBar.decreaseFuel(amountToDecrease);
        final int initialFuelLevel = fuelBar.getFuelLevel();
        final int amountToAdd = Constants.FUEL_REFILL;

        fuelBar.increaseFuel(amountToAdd);

        assertEquals(initialFuelLevel + amountToAdd, fuelBar.getFuelLevel());
    }
}
