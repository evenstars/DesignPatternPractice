/**
 * Record for students, containing name, id, gpa, and courses they attend.
 */
public interface StudentRecord {

  /**
   * Update the status according to given course grade. Update the credit, gpa, and status include
   * academic status, coop and graduate.
   */
  void updateStatus(CourseGrade grade);

  /**
   * Whether is good academic status which means the gpa is >= 3.0;
   *
   * @return whether is good academic status
   */
  boolean isGoodAcademic();

  /**
   * Whether can coop, which means gpa >=3.0 and credits are >=16.
   *
   * @return whether can go to coop
   */
  boolean canCoop();

  /**
   * Whether can graduate, which means having attended all core courses and the core gpa >=3.0;
   *
   * @return whether can graduate.
   */
  boolean canGraduate();

  /**
   * Get the id of the student.
   */
  int getId();
}
