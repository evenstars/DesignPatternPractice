/**
 * Align student recorder.
 */
public class AlignStudentRecord extends StudentRecordImpl {

  /**
   * Constructor which is the same as the StudentRecordImpl.
   *
   * @param name name of student
   * @param id   id
   */
  public AlignStudentRecord(String name, int id) {
    super(name, id);
  }

  /**
   * Core course: CS5004,CS5800,CS5500/CS5600, if both CS5500 and CS5600 are taken, pick the higher
   * one.
   *
   * @return whether can graduate
   */
  @Override
  public boolean canGraduate() {
    GPACalculator coreGpa = new GPACalculatorImpl();
    CourseGrade cs5004;
    CourseGrade cs5800;
    CourseGrade cs5500;
    CourseGrade cs5600;
    cs5004 = attendedCourses.get("CS5004");
    cs5800 = attendedCourses.get("CS5800");
    cs5500 = attendedCourses.get("CS5500");
    cs5600 = attendedCourses.get("CS5600");
    if (cs5004 == null || cs5800 == null || (cs5500 == null && cs5600 == null)) {
      return false;
    }
    CourseGrade selection = null;
    if (cs5500 == null) {
      selection = cs5600;
    } else if (cs5600 == null) {
      selection = cs5500;
    } else {
      String cs5500Grade = cs5500.getGrade();
      String cs5600Grade = cs5600.getGrade();
      if (cs5500Grade.charAt(0) != cs5600Grade.charAt(0)) {
        selection = cs5500Grade.charAt(0) < cs5600Grade.charAt(0) ? cs5500 : cs5600;
      } else {
        int cs5500Idx = cs5500Grade.length() == 1 ? 2 : cs5500Grade.charAt(1) == '+' ? 1 : 3;
        int cs5600Idx = cs5600Grade.length() == 1 ? 2 : cs5600Grade.charAt(1) == '+' ? 1 : 3;
        selection = cs5500Idx <= cs5600Idx ? cs5500 : cs5600;
      }
    }
    coreGpa.addCourse(cs5004.getCredit(), cs5004.getGrade());
    coreGpa.addCourse(cs5800.getCredit(), cs5800.getGrade());
    coreGpa.addCourse(selection.getCredit(), selection.getGrade());
    return coreGpa.getCurrentGPA() >= 3.0;
  }
}
