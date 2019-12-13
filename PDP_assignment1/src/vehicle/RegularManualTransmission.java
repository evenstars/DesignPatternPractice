package vehicle;

/**
 * A implemented class for the ManualTransmission class. Used to represent the operation of manual
 * transmission.
 */
public class RegularManualTransmission implements ManualTransmission {

  /**
   * Fixed amount used for increasing or decreasing speed.
   */
  private static int SPEED_INCREASE = 1;

  /**
   * The total gear amount.
   */
  private static int GEAR_NUMBER = 5;

  /**
   * The total gears.
   */
  private Gear[] gears;

  /**
   * Current speed.
   */
  private Integer curSpeed;

  /**
   * current gear.
   */
  private Integer curGearIdx;

  /**
   * current status.
   */
  private Status curStatus;

  /**
   * Initialization method used for giving speeds to each gear.
   *
   * @param l1 Lower bound of the speed of first gear
   * @param h1 Upper bound of the speed of first gear
   * @param l2 Lower bound of the speed of second gear
   * @param h2 Upper bound of the speed of second gear
   * @param l3 Lower bound of the speed of first gear
   * @param h3 Upper bound of the speed of first gear
   * @param l4 Lower bound of the speed of third gear
   * @param h4 Upper bound of the speed of third gear
   * @param l5 Lower bound of the speed of forth gear
   * @param h5 Upper bound of the speed of forth gear
   */
  public RegularManualTransmission(Integer l1, Integer h1, Integer l2, Integer h2, Integer l3,
                                   Integer h3, Integer l4, Integer h4, Integer l5, Integer h5) {
    if (l1 != 0) {
      throw new IllegalArgumentException("The minimum speed is error");
    }
    if (h1 < l1 || h2 < l2 || h3 < l3 || h4 < l4 || h5 < l5) {
      throw new IllegalArgumentException("The upper bound is lower than the lower bound");
    }
    if (l1 >= l2 || l2 >= l3 || l3 >= l4 || l4 >= l5) {
      throw new IllegalArgumentException("The lower speed for the prior gear should be" +
              " strictly lesser than that of latter gear");
    }
    if (h1 < l2 || h2 < l3 || h3 < l4 || h4 < l5) {
      throw new IllegalArgumentException("The ranges cannot be non-overlapping");
    }
    gears = new Gear[GEAR_NUMBER];
    gears[0] = new Gear(l1, h1);
    gears[1] = new Gear(l2, h2);
    gears[2] = new Gear(l3, h3);
    gears[3] = new Gear(l4, h4);
    gears[4] = new Gear(l5, h5);
    curGearIdx = 0;
    curSpeed = 0;
    curStatus = Status.SPEED_OR_GEAR_CHANGED_SUCCESSFULLY;
  }

  /**
   * Used for get the status of this transmission.
   *
   * @return Current status as a String
   */
  @Override
  public String getStatus() {
    return curStatus.getInformation();
  }

  /**
   * Get the speed of the vehicle.
   *
   * @return The speed currently, whole number
   */
  @Override
  public int getSpeed() {
    return curSpeed;
  }

  /**
   * Get the current gear of the vehicle as a whole number.
   *
   * @return Current gear.
   */
  @Override
  public int getGear() {
    return curGearIdx + 1;
  }

  /**
   * Increase the speed by a fixed amount without changing gears. This function might change the
   * status and speed attribution of current object. When the current speed reaches the maximum
   * speed, the speed can not be increased. When the speed reaches the maximum speed of current
   * gear, the speed can not be increased. When the speed reaches the lower bound of next gear
   * range, the speed can be increased. When the speed is in the range of current gear, the speed
   * can be increased.
   *
   * @return The resulting transmission object
   */
  @Override
  public ManualTransmission increaseSpeed() {
    int maxSpeed = gears[GEAR_NUMBER - 1].highSpeed;
    if (curSpeed == maxSpeed) {
      curStatus = Status.MAXIMUM_SPEED;
      return this;
    }
    if (curSpeed.equals(gears[curGearIdx].highSpeed)) {
      curStatus = Status.SPEED_INCREASE_TO_NEXT_GEAR_RANGE_FAIL;
      return this;
    }
    curSpeed += SPEED_INCREASE;
    if (curGearIdx < GEAR_NUMBER - 1 && curSpeed >= gears[curGearIdx + 1].lowSpeed) {
      curStatus = Status.SPEED_INCREASE_TO_NEXT_GEAR_RANGE_SUCCESSFULLY;
    } else {
      curStatus = Status.SPEED_OR_GEAR_CHANGED_SUCCESSFULLY;
    }
    return this;
  }

  /**
   * Decrease the speed by a fixed amount without changing gears. This function might change the
   * status and speed attribution of current object. When the current speed reaches the minimum
   * speed, the speed can not be decreased. When the speed reaches the minimum speed of current
   * gear, the speed can not be decreased. When the speed reaches the higher bound of last gear
   * range, the speed can be decreased. When the speed is in the range of current gear, the speed
   * can be decreased.
   *
   * @return The resulting transmission object
   */
  @Override
  public ManualTransmission decreaseSpeed() {
    if (curSpeed == 0) {
      curStatus = Status.MINIMUM_SPEED;
      return this;
    }
    if (curSpeed.equals(gears[curGearIdx].lowSpeed)) {
      curStatus = Status.SPEED_DECREASE_TO_NEXT_GEAR_RANGE_FAIL;
      return this;
    }
    curSpeed -= SPEED_INCREASE;
    if (curGearIdx > 0 && curSpeed <= gears[curGearIdx - 1].highSpeed) {
      curStatus = Status.SPEED_DECREASE_TO_NEXT_GEAR_RANGE_SUCCESSFULLY;
    } else {
      curStatus = Status.SPEED_OR_GEAR_CHANGED_SUCCESSFULLY;
    }
    return this;
  }

  /**
   * Increase the gear by one without changing speed. This function might change the status and
   * speed attribution of current object. When the gear reaches the maximum number of gears, the
   * gear can not be increased. When the speed is lower than the next gear range, the gear can not
   * be increased. Otherwise the gear can be increased.
   *
   * @return The resulting transmission object.
   */
  @Override
  public ManualTransmission increaseGear() {
    if (curGearIdx == GEAR_NUMBER - 1) {
      curStatus = Status.MAXIMUM_GEAR;
      return this;
    }
    if (gears[curGearIdx + 1].lowSpeed > curSpeed) {
      curStatus = Status.GEAR_INCREASE_FAIL;
      return this;
    }
    curGearIdx++;
    curStatus = Status.SPEED_OR_GEAR_CHANGED_SUCCESSFULLY;
    return this;
  }

  /**
   * decreased the gear by one without changing speed. This function might change the status and
   * speed attribution of current object. When the gear reaches the minimum number of gears, the
   * gear can not be decreased. When the speed is higher than the last gear range, the gear can not
   * be decreased. Otherwise the gear can be decreased.
   *
   * @return The resulting transmission object.
   */
  @Override
  public ManualTransmission decreaseGear() {
    if (curGearIdx == 0) {
      curStatus = Status.MINIMUM_GEAR;
      return this;
    }
    if (gears[curGearIdx - 1].highSpeed < curSpeed) {
      curStatus = Status.GEAR_DECREASE_FAIL;
      return this;
    }
    curGearIdx--;
    curStatus = Status.SPEED_OR_GEAR_CHANGED_SUCCESSFULLY;
    return this;
  }
}
