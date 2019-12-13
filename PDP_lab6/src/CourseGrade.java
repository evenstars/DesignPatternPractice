/**
 * Course grade information container.
 */
public class CourseGrade {

  /**
   * Name of the course.
   */
  private String name;

  /**
   * Credit of the course.
   */
  private double credit;

  /**
   * Grade of the course.
   */
  private String grade;

  /**
   * Constructor for the course grade.
   *
   * @param name   name of course, cannot be null or empty
   * @param credit credit of the course, cannot be negative
   * @param grade  grade of the course cannot be null or empty
   */
  public CourseGrade(String name, double credit, String grade) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("wrong name format");
    }
    if (credit < 0) {
      throw new IllegalArgumentException("credit cannot be negative");
    }
    if (grade == null || grade.isEmpty()) {
      throw new IllegalArgumentException("wrong grade form");
    }
    this.name = name;
    this.credit = credit;
    this.grade = grade;
  }

  /**
   * Get name.
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Get grade.
   *
   * @return grade
   */
  public String getGrade() {
    return grade;
  }

  /**
   * Get credit.
   *
   * @return credit
   */
  public double getCredit() {
    return credit;
  }
}
