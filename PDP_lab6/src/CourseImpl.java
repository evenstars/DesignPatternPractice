import java.util.HashMap;
import java.util.Map;

/**
 * Implementation for courses using map representing student registered the class.
 */
public class CourseImpl implements Course {

  /**
   * Name of the course.
   */
  private String name;

  /**
   * Credit of the course.
   */
  private double credit;

  /**
   * Students registered the courses. Key is student id, value is record class.
   */
  private Map<Integer, StudentRecord> students;

  /**
   * Constructor. If the name is null or empty, or the credit is negative, throw exception.
   *
   * @param name   name of this course
   * @param credit credit of this course
   */
  public CourseImpl(String name, double credit) {
    checkNull(name);
    if (name.isEmpty()) {
      throw new IllegalArgumentException("course name cannot be null or empty.");
    }
    if (credit < 0) {
      throw new IllegalArgumentException("credit cannot be negative.");
    }
    this.name = name;
    this.credit = credit;
    students = new HashMap<>();
  }

  /**
   * Get the course name.
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Get the credit of the course.
   *
   * @return credit
   */
  public double getCredit() {
    return credit;
  }

  @Override
  public void addStudent(StudentRecord record) {
    checkNull(record);
    students.put(record.getId(), record);
  }

  @Override
  public void updateGrade(Map<Integer, String> grades) {
    checkNull(grades);
    for (Map.Entry<Integer, StudentRecord> student : students.entrySet()) {
      String grade = grades.get(student.getKey());
      if (grade == null) {
        throw new IllegalArgumentException("lack student grade.");
      }
      student.getValue().updateStatus(new CourseGrade(name, credit, grade));
    }
  }

  /**
   * Whether the argument is null. If yes, throw exception.
   *
   * @param object determine object
   */
  private void checkNull(Object object) {
    if (object == null) {
      throw new IllegalArgumentException("argument cannot be null.");
    }
  }

  @Override
  public boolean hasStudent(int studentId) {
    return students.containsKey(studentId);
  }
}
