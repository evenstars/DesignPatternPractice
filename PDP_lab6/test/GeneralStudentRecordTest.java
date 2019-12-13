import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for studentRecorder constructor.
 */
public class GeneralStudentRecordTest {

  /**
   * Test object.
   */
  GeneralStudentRecord studentRecord;

  /**
   * Constructor with null name input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullName() {
    studentRecord = new GeneralStudentRecord(null, 5);
  }

  /**
   * Constructor with empty name input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorEmptyName() {
    studentRecord = new GeneralStudentRecord("", 5);
  }

  /**
   * Constructor with negative id.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeId() {
    studentRecord = new GeneralStudentRecord("Tom", -4);
  }

  /**
   * Constructor with valid input.
   */
  @Test
  public void testValidConstructor() {
    studentRecord = new GeneralStudentRecord("Tom", 5);
    assertEquals(5, studentRecord.getId());
  }
}