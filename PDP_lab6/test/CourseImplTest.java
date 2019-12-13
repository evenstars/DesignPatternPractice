import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * CourseImpl tester.
 */
public class CourseImplTest {

  /**
   * Test object.
   */
  CourseImpl course;

  /**
   * Constructor with null name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullConstructorName() {
    course = new CourseImpl(null, 5);
  }

  /**
   * Constructor with empty name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyConstructorName() {
    course = new CourseImpl("", 5);
  }

  /**
   * Constructor with negative credit.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegCreditConstructor() {
    course = new CourseImpl("PDP", -5);
  }

  /**
   * Constructor with valid input.
   */
  @Test
  public void testValidConstructor() {
    course = new CourseImpl("PDP", 5);
    assertEquals(5, course.getCredit(), 0.01);
    assertEquals("PDP", course.getName());
  }

  /**
   * add null studentRecord.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullRecordAddStudent() {
    course = new CourseImpl("PDP", 5);
    course.addStudent(null);
  }


  /**
   * add studentRecord validly.
   */
  @Test
  public void testValidAddStudent() {
    course = new CourseImpl("PDP", 5);
    StudentRecord record = new GeneralStudentRecord("Tom", 3);
    course.addStudent(record);
    assertEquals(true, course.hasStudent(3));
  }

  /**
   * update with null grades.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateNullGrades() {
    course = new CourseImpl("PDP", 5);
    course.updateGrade(null);
  }

  /**
   * update with mismatch grades.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateMismatchGrades() {
    course = new CourseImpl("PDP", 5);
    StudentRecord record = new GeneralStudentRecord("Tom", 3);
    course.addStudent(record);
    Map<Integer, String> grades = new HashMap<>();
    grades.put(6, "A");
    course.updateGrade(grades);
  }

  /**
   * Update wrong grade input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateWrongGrades() {
    course = new CourseImpl("PDP", 5);
    StudentRecord record = new GeneralStudentRecord("Tom", 3);
    course.addStudent(record);
    Map<Integer, String> grades = new HashMap<>();
    grades.put(3, "Y");
    course.updateGrade(grades);
  }

  /**
   * Update from good or bad academic to good academic.
   */
  @Test
  public void testUpdateGradesToGoodAcademic() {
    course = new CourseImpl("PDP", 5);
    StudentRecord record = new GeneralStudentRecord("Tom", 3);
    course.addStudent(record);
    Map<Integer, String> grades = new HashMap<>();
    grades.put(3, "A");
    course.updateGrade(grades);
    assertEquals(true, record.isGoodAcademic());
    Course course1 = new CourseImpl("algo", 5);
    course1.addStudent(record);
    grades.put(3, "A-");
    course1.updateGrade(grades);
    assertEquals(true, record.isGoodAcademic());
  }

  /**
   * Update from good or bad academic to bad academic.
   */
  @Test
  public void testUpdateGradesToBadAcademic() {
    course = new CourseImpl("PDP", 5);
    StudentRecord record = new GeneralStudentRecord("Tom", 3);
    course.addStudent(record);
    Map<Integer, String> grades = new HashMap<>();
    grades.put(3, "B-");
    course.updateGrade(grades);
    assertEquals(false, record.isGoodAcademic());
    grades.put(3, "A");
    course.updateGrade(grades);
    assertEquals(true, record.isGoodAcademic());
    grades.put(3, "F");
    course.updateGrade(grades);
    assertEquals(false, record.isGoodAcademic());
  }

  /**
   * Update from can or cannot coop to can coop.
   */
  @Test
  public void testUpdateGradesToCanCoop() {
    course = new CourseImpl("PDP", 5);
    StudentRecord record = new GeneralStudentRecord("Tom", 3);
    course.addStudent(record);
    Map<Integer, String> grades = new HashMap<>();
    grades.put(3, "B");
    course.updateGrade(grades);
    assertEquals(false, record.canCoop());
    grades.put(3, "A");
    course.updateGrade(grades);
    grades.put(3, "A");
    course.updateGrade(grades);
    grades.put(3, "A");
    course.updateGrade(grades);
    assertEquals(true, record.canCoop());
  }

  /**
   * Update from can or cannot coop to cannot coop.
   */
  @Test
  public void testUpdateGradesToCannotCoop() {
    course = new CourseImpl("PDP", 5);
    StudentRecord record = new GeneralStudentRecord("Tom", 3);
    course.addStudent(record);
    Map<Integer, String> grades = new HashMap<>();
    grades.put(3, "B");
    course.updateGrade(grades);
    assertEquals(false, record.canCoop());
    grades.put(3, "C");
    course.updateGrade(grades);
    grades.put(3, "C");
    course.updateGrade(grades);
    grades.put(3, "C");
    course.updateGrade(grades);
    assertEquals(false, record.canCoop());
  }

  /**
   * Update generate student from can or cannot graduate to can graduate via 5500 elective.
   */
  @Test
  public void testUpdateGradesGeneralToCanGraduateVia5500() {
    course = new CourseImpl("CS5010", 4);
    Course course1 = new CourseImpl("CS5800", 4);
    Course course2 = new CourseImpl("CS5500", 4);
    Map<Integer, String> grades = new HashMap<>();
    grades.put(3, "A");
    StudentRecord record = new GeneralStudentRecord("Tom", 3);
    course.addStudent(record);
    course1.addStudent(record);
    course2.addStudent(record);
    assertEquals(false, record.canGraduate());
    course.updateGrade(grades);
    course1.updateGrade(grades);
    course2.updateGrade(grades);
    assertEquals(true, record.canGraduate());
  }

  /**
   * Update generate student from can or cannot graduate to can graduate via 5600 elective.
   */
  @Test
  public void testUpdateGradesGeneralToCanGraduateVia5600() {
    course = new CourseImpl("CS5010", 4);
    Course course1 = new CourseImpl("CS5800", 4);
    Course course2 = new CourseImpl("CS5600", 4);
    Map<Integer, String> grades = new HashMap<>();
    grades.put(3, "A");
    StudentRecord record = new GeneralStudentRecord("Tom", 3);
    course.addStudent(record);
    course1.addStudent(record);
    course2.addStudent(record);
    assertEquals(false, record.canGraduate());
    course.updateGrade(grades);
    course1.updateGrade(grades);
    course2.updateGrade(grades);
    assertEquals(true, record.canGraduate());
  }

  /**
   * Update generate student to cannot graduate since lack of core courses.
   */
  @Test
  public void testUpdateGradesGeneralToCannotGraduateLackCoreCourse() {
    course = new CourseImpl("CS5010", 4);
    Map<Integer, String> grades = new HashMap<>();
    grades.put(3, "A");
    StudentRecord record = new GeneralStudentRecord("Tom", 3);
    course.addStudent(record);
    assertEquals(false, record.canGraduate());
    course.updateGrade(grades);
    assertEquals(false, record.canGraduate());
  }

  /**
   * Update generate student to cannot graduate since low gpa.
   */
  @Test
  public void testUpdateGradesGeneralToCannotGraduateLowGPA() {
    course = new CourseImpl("CS5010", 4);
    Course course1 = new CourseImpl("CS5800", 4);
    Course course2 = new CourseImpl("CS5600", 4);
    Map<Integer, String> grades = new HashMap<>();
    grades.put(3, "C");
    StudentRecord record = new GeneralStudentRecord("Tom", 3);
    course.addStudent(record);
    course1.addStudent(record);
    course2.addStudent(record);
    assertEquals(false, record.canGraduate());
    course.updateGrade(grades);
    course1.updateGrade(grades);
    course2.updateGrade(grades);
    assertEquals(false, record.canGraduate());
  }


  /**
   * Update align student from can or cannot graduate to can graduate via 5500 elective.
   */
  @Test
  public void testUpdateGradesAlignToCanGraduateVia5500() {
    course = new CourseImpl("CS5004", 4);
    Course course1 = new CourseImpl("CS5800", 4);
    Course course2 = new CourseImpl("CS5500", 4);
    Map<Integer, String> grades = new HashMap<>();
    grades.put(3, "A");
    StudentRecord record = new AlignStudentRecord("Tom", 3);
    course.addStudent(record);
    course1.addStudent(record);
    course2.addStudent(record);
    assertEquals(false, record.canGraduate());
    course.updateGrade(grades);
    course1.updateGrade(grades);
    course2.updateGrade(grades);
    assertEquals(true, record.canGraduate());
  }

  /**
   * Update align student from can or cannot graduate to can graduate via 5600 elective.
   */
  @Test
  public void testUpdateGradesAlignToCanGraduateVia5600() {
    course = new CourseImpl("CS5004", 4);
    Course course1 = new CourseImpl("CS5800", 4);
    Course course2 = new CourseImpl("CS5600", 4);
    Map<Integer, String> grades = new HashMap<>();
    grades.put(3, "A");
    StudentRecord record = new AlignStudentRecord("Tom", 3);
    course.addStudent(record);
    course1.addStudent(record);
    course2.addStudent(record);
    assertEquals(false, record.canGraduate());
    course.updateGrade(grades);
    course1.updateGrade(grades);
    course2.updateGrade(grades);
    assertEquals(true, record.canGraduate());
  }

  /**
   * Update align student to cannot graduate since lack of core courses.
   */
  @Test
  public void testUpdateGradesAlignToCannotGraduateLackCoreCourse() {
    course = new CourseImpl("CS5004", 4);
    Map<Integer, String> grades = new HashMap<>();
    grades.put(3, "A");
    StudentRecord record = new AlignStudentRecord("Tom", 3);
    course.addStudent(record);
    assertEquals(false, record.canGraduate());
    course.updateGrade(grades);
    assertEquals(false, record.canGraduate());
  }

  /**
   * Update align student to cannot graduate since low gpa.
   */
  @Test
  public void testUpdateGradesAlignToCannotGraduateLowGPA() {
    course = new CourseImpl("CS5004", 4);
    Course course1 = new CourseImpl("CS5800", 4);
    Course course2 = new CourseImpl("CS5600", 4);
    Map<Integer, String> grades = new HashMap<>();
    grades.put(3, "C");
    StudentRecord record = new AlignStudentRecord("Tom", 3);
    course.addStudent(record);
    course1.addStudent(record);
    course2.addStudent(record);
    assertEquals(false, record.canGraduate());
    course.updateGrade(grades);
    course1.updateGrade(grades);
    course2.updateGrade(grades);
    assertEquals(false, record.canGraduate());
  }
}