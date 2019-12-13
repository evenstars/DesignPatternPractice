import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the studentRecord.
 */
public abstract class StudentRecordImpl implements StudentRecord {

  /**
   * Get name.
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Student name.
   */
  private String name;

  /**
   * Student id.
   */
  private int id;

  /**
   * Get gpa calculator.
   */
  private GPACalculator totalGpaCalculator;

  /**
   * The course the student attended.Key is the name and value if course information.
   */
  protected Map<String, CourseGrade> attendedCourses;

  /**
   * Constructor taking student name and id.If name is null or empty, or id is negative, throw
   * exception.
   *
   * @param name student name
   * @param id   student id
   */
  public StudentRecordImpl(String name, int id) {
    if (name == null || name.isEmpty() || id < 0) {
      throw new IllegalArgumentException("error argument");
    }
    this.name = name;
    this.id = id;
    attendedCourses = new HashMap<>();
    totalGpaCalculator = new GPACalculatorImpl();
  }

  @Override
  public void updateStatus(CourseGrade grade) {
    if (grade == null) {
      throw new IllegalArgumentException("null courses result");
    }
    attendedCourses.put(grade.getName(), grade);
    totalGpaCalculator.addCourse(grade.getCredit(), grade.getGrade());
  }

  @Override
  public boolean isGoodAcademic() {
    return totalGpaCalculator.getCurrentGPA() >= 3.0;
  }

  @Override
  public boolean canCoop() {
    return totalGpaCalculator.getCurrentCredit() >= 16 && isGoodAcademic();
  }

  @Override
  public int getId() {
    return id;
  }
}
