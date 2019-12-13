import java.util.Map;

/**
 * Class for course, which could add student to this course and update grades for all students.
 */
public interface Course {

  /**
   * Add one student to this course. If the record of the student is null, throw exception.
   *
   * @param record given studentRecord representing one student
   */
  void addStudent(StudentRecord record);

  /**
   * Update grades for all the students registering this courses. If the grade is null, throw
   * exception.
   *
   * @param grades map representing grades, key is the id, value is the grade for the student.
   */
  void updateGrade(Map<Integer, String> grades);

  /**
   * Determine whether the student whose id is given registers the course.
   *
   * @param studentId the student id
   */
  boolean hasStudent(int studentId);
}
