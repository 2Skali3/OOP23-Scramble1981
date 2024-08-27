package scramble.model.spaceship;

import javax.swing.Timer;

import scramble.utility.Constants;

/**
 * This class is in charge of the fuel bar part of the hud. It loads two images,
 * for empty and full bar.
 */
public final class FuelBar {

    private int fuelLevel; // shows the fuel level on the bar (varies from 1 to 100)

    /**
     * Class constructor.
     */
    public FuelBar() {
        fuelLevel = Constants.MAX_FUEL; // Initially the fuel bar is full
        final Timer timer = new Timer(1000, e -> decreaseFuel(Constants.FUEL_DECREASE_AMOUNT));
        timer.start();
    }

    /**
     * Decreases the fuel level.
     * 
     * @param amount the amount to subtract
     */
    public void decreaseFuel(final int amount) {
        fuelLevel -= amount;
        if (fuelLevel < 0) {
            fuelLevel = 0;
        }
    }

    /**
     * Increases the fuel level.
     * 
     * @param amount the amount to add
     */
    public void increaseFuel(final int amount) {
        fuelLevel += amount;
        if (fuelLevel > Constants.MAX_FUEL) {
            fuelLevel = Constants.MAX_FUEL;
        }
    }

    /**
     * Checks if fuel bar is empty.
     * 
     * @return True if fuel bar is empty
     */
    public boolean checkFuelZero() {
        return fuelLevel <= 0;
    }

    /**
     * Getter for the fuel threshold.
     * 
     * @return the current fuel level
     */
    public int getFuelLevel() {
        return fuelLevel;
    }

    /** Resets fuel bar to MAX. */
    public void fillFuel() {
        this.fuelLevel = Constants.MAX_FUEL;
    }
}
