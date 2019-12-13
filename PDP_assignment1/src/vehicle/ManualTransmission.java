package vehicle;

/**
 * this interface is used for representing the operation of manual transmission.
 */
public interface ManualTransmission {

  /**
   * Used for get the status of this transmission.
   *
   * @return Current status as a String
   */
  public String getStatus();

  /**
   * Get the speed of the vehicle.
   *
   * @return The speed currently, whole number
   */
  public int getSpeed();

  /**
   * Get the current gear of the vehicle as a whole number.
   *
   * @return Current gear.
   */
  public int getGear();

  /**
   * Increase the speed by a fixed amount without changing gears. This function might change the
   * status and speed attribution of current object.
   * When the current speed reaches the maximum speed, the speed can not be increased.
   * When the speed reaches the maximum speed of current gear, the speed can not be increased.
   * When the speed reaches the lower bound of next gear range, the speed can be increased.
   * When the speed is in the range of current gear, the speed can be increased.
   *
   * @return The resulting transmission object
   */
  public ManualTransmission increaseSpeed();

  /**
   * Decrease the speed by a fixed amount without changing gears. This function might change the
   * status and speed attribution of current object.
   * When the current speed reaches the minimum speed, the speed can not be decreased.
   * When the speed reaches the minimum speed of current gear, the speed can not be decreased.
   * When the speed reaches the higher bound of last gear range, the speed can be decreased.
   * When the speed is in the range of current gear, the speed can be decreased.
   *
   * @return The resulting transmission object
   */
  public ManualTransmission decreaseSpeed();

  /**
   * Increase the gear by one without changing speed. This function might change the status and
   * speed attribution of current object.
   * When the gear reaches the maximum number of gears, the gear can not be increased.
   * When the speed is lower than the next gear range, the gear can not be increased.
   * Otherwise the gear can be increased.
   *
   * @return The resulting transmission object.
   */
  public ManualTransmission increaseGear();

  /**
   * decreased the gear by one without changing speed. This function might change the status and
   * speed attribution of current object.
   * When the gear reaches the minimum number of gears, the
   * gear can not be decreased. When the speed is higher than the last gear range, the gear can not
   * be decreased. Otherwise the gear can be decreased.
   *
   * @return The resulting transmission object.
   */
  public ManualTransmission decreaseGear();
}
