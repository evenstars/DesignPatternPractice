package vehicle;

/**
 * A enumeration class used for representing the information of the status of vehicles.
 */
public enum Status {
  SPEED_OR_GEAR_CHANGED_SUCCESSFULLY("OK: everything is OK."),
  SPEED_INCREASE_TO_NEXT_GEAR_RANGE_SUCCESSFULLY("OK: you may increase the gear."),
  SPEED_DECREASE_TO_NEXT_GEAR_RANGE_SUCCESSFULLY("OK: you may decrease the gear."),
  SPEED_INCREASE_TO_NEXT_GEAR_RANGE_FAIL("Cannot increase speed, increase gear first."),
  SPEED_DECREASE_TO_NEXT_GEAR_RANGE_FAIL("Cannot decrease speed, decrease gear first."),
  GEAR_INCREASE_FAIL("Cannot increase gear, increase speed first."),
  GEAR_DECREASE_FAIL("Cannot decrease gear, decrease speed first."),
  MAXIMUM_SPEED("Cannot increase speed. Reached maximum speed."),
  MINIMUM_SPEED("Cannot decrease speed. Reached minimum speed."),
  MAXIMUM_GEAR("Cannot increase gear. Reached maximum gear."),
  MINIMUM_GEAR("Cannot decrease gear. Reached minimum gear.");
  /**
   * Information of this status.
   */
  private String information;

  /**
   * Initialization for this status.
   *
   * @param information Information of this status
   */
  private Status(String information) {
    this.information = information;
  }

  /**
   * Getter function for information.
   */
  public String getInformation() {
    return this.information;
  }
}
