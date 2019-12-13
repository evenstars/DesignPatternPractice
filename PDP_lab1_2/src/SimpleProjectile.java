import java.math.BigDecimal;

/**
 * this class is used for getting states of particles.
 */
public class SimpleProjectile implements Particle {

  /**
   * the original x position of this particle.
   */
  private BigDecimal x;

  /**
   * the original y position of this particle.
   */
  private BigDecimal y;

  /**
   * the original velocity of x-coordinate.
   */
  private BigDecimal vx;

  /**
   * the original velocity of y-coordinate.
   */
  private BigDecimal vy;

  /**
   * the gravity acceleration.
   */
  private static BigDecimal graivity = new BigDecimal(String.valueOf("-9.81"));

  /**
   * construction function of this class.
   *
   * @param x  the x-coordination position
   * @param y  the y-coordination position
   * @param vx the x-coordination velocity
   * @param vy the y-coordination velocity
   */
  public SimpleProjectile(float x, float y, float vx, float vy) {
    this.x = new BigDecimal(String.valueOf(x));
    this.y = new BigDecimal(String.valueOf(y));
    this.vx = new BigDecimal(String.valueOf(vx));
    this.vy = new BigDecimal(String.valueOf(vy));
  }

  /**
   * Return the state of this particle as a formatted string. The format of the string is as
   * follows:
   * <code>At time [t]: position is ([x],[y])</code> where
   * <ul>
   * <li>[t] is the time passed to this method, rounded to two decimal
   * places</li>
   * <li>[x] is the x-coordinate of the position of this particle at this
   * time, rounded to two decimal places </li>
   * <li>[y] is the y-coordinate of the position of this particle at this
   * time, rounded to two decimal places
   * </li> </ul>
   *
   * @param time the time at which the state must be obtained
   * @return the state of the particle as a string formatted as above
   */
  @Override
  public String getState(float time) {
    BigDecimal distX = new BigDecimal(String.valueOf("0"));
    BigDecimal distY = new BigDecimal(String.valueOf("0"));
    if (time > 0) {
      BigDecimal two = new BigDecimal("2");
      BigDecimal limitTime = two.multiply(vy)
              .divide(graivity.abs(), 5, BigDecimal.ROUND_HALF_EVEN);
      BigDecimal bigDecimal1Time = new BigDecimal(String.valueOf(time));
      if (limitTime.floatValue() >= time) {
        distX = vx.multiply(bigDecimal1Time);
        BigDecimal distY1 = vy.multiply(bigDecimal1Time);
        BigDecimal half = new BigDecimal("0.5");
        BigDecimal distY2 = half.multiply(graivity).multiply(bigDecimal1Time)
                .multiply(bigDecimal1Time);
        distY = distY1.add(distY2);
      } else {
        distX = vx.multiply(limitTime);
      }
    }
    String formatTime = format(time);
    String formatX = format(x.add(distX).floatValue());
    String formatY = format(y.add(distY).floatValue());
    return "At time " + formatTime + ": position is ("
            + formatX + "," + formatY + ")";
  }

  /**
   * used for format float data to two decimal.
   *
   * @param data float
   */
  private String format(float data) {
    float res = (float) Math.round(data * 100) / 100;
    return String.format("%.2f", res);
  }
}
