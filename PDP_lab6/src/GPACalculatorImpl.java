import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the gpa calculator. GPA equals quality / credit.
 */
public class GPACalculatorImpl implements GPACalculator {

  /**
   * Total credit.
   */
  private double totalCredit;

  /**
   * Quality point accumulative.
   */
  private double qualityPoint;

  /**
   * Map containing the gpa convert rules.
   */
  private static Map<String, Double> gpaConvertor;

  static {
    gpaConvertor = new HashMap<>();
    gpaConvertor.put("A", 4.0);
    gpaConvertor.put("A-", 3.667);
    gpaConvertor.put("B+", 3.333);
    gpaConvertor.put("B", 3.0);
    gpaConvertor.put("B-", 2.667);
    gpaConvertor.put("C+", 2.333);
    gpaConvertor.put("C", 2.0);
    gpaConvertor.put("C-", 1.667);
    gpaConvertor.put("D+", 1.333);
    gpaConvertor.put("D", 1.0);
    gpaConvertor.put("D-", 0.667);
    gpaConvertor.put("F", 0.0);
  }

  /**
   * Constructor set credit and quality to zero.
   */
  public GPACalculatorImpl() {
    totalCredit = 0;
    qualityPoint = 0;
  }

  @Override
  public void addCourse(double credit, String grade) {
    if (credit < 0 || grade == null || grade.isEmpty() || !gpaConvertor.containsKey(grade)) {
      throw new IllegalArgumentException("error argument");
    }
    totalCredit += credit;
    qualityPoint += gpaConvertor.get(grade) * credit;
  }

  @Override
  public double getCurrentGPA() {
    return qualityPoint / totalCredit;
  }

  @Override
  public double getCurrentCredit() {
    return totalCredit;
  }
}
