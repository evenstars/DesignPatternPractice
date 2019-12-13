/**
 * GPA calculator. Could use different algorithm to get gpa and total credit.
 */
public interface GPACalculator {

  /**
   * Add one new course to the calculator.
   *
   * @param credit credit of the course
   * @param grade  grade of the course
   */
  void addCourse(double credit, String grade);

  /**
   * Get the accumulative gpa according to implementation algorithm.
   *
   * @return gpa
   */
  double getCurrentGPA();

  /**
   * Get the accumulative credit according to the implementation algorithm.
   *
   * @return total credit
   */
  double getCurrentCredit();
}
