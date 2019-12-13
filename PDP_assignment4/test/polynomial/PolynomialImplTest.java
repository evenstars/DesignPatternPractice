package polynomial;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class is the test class of PolynomialImpl class.
 */
public class PolynomialImplTest {

  /**
   * The object used for testing.
   */
  private Polynomial polynomial;

  /**
   * Test for constructor with string input.
   */
  @org.junit.Test
  public void testStringConstructor() {
    String terms = "-3x^4 -2x^5 -5 +11x^1";
    polynomial = new PolynomialImpl(terms);
    assertEquals(5, polynomial.getDegree());
    assertEquals(-2, polynomial.getCoefficient(5));
    assertEquals(-3, polynomial.getCoefficient(4));
    assertEquals(11, polynomial.getCoefficient(1));
    assertEquals(-5, polynomial.getCoefficient(0));
  }

  /**
   * Test for constructor with empty string input.
   */
  @org.junit.Test
  public void testStringConstructorEmpty() {
    polynomial = new PolynomialImpl("");
    assertTrue(((PolynomialImpl) polynomial).getHead() instanceof TermListADTEmptyNode);
  }

  /**
   * Test for constructor with string input.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testInvalidInput() {
    polynomial = new PolynomialImpl("+23XYZ");
  }

  /**
   * Test for addTerm with 0 coefficient.
   */
  @org.junit.Test
  public void testAddTermRemoveTerm() {
    String terms = "-3x^4 -2x^5 -5 +11x^1";
    polynomial = new PolynomialImpl(terms);
    polynomial.addTerm(3, 4);
    assertEquals(0, (long) polynomial.getCoefficient(4));
  }

  /**
   * Test add term when list is empty.
   */
  @org.junit.Test
  public void testAddTermWhenEmpty() {
    polynomial = new PolynomialImpl();
    polynomial.addTerm(3, 4);
    assertEquals(3, polynomial.getCoefficient(4));
  }

  /**
   * Test for addTerm with new term.
   */
  @org.junit.Test
  public void testAddTermNewTerm() {
    String terms = "-3x^4 -2x^5 -5 +11x^1";
    polynomial = new PolynomialImpl(terms);
    polynomial.addTerm(3, 2);
    assertEquals(3, (long) polynomial.getCoefficient(2));
  }

  /**
   * Test for evaluating the polynomial.
   */
  @org.junit.Test
  public void testEvaluate() {
    String terms = "3x^4 -5x^3 +2x^1 -4";
    polynomial = new PolynomialImpl(terms);
    assertEquals(40.0625, polynomial.evaluate(2.5), 0.00001);
  }

  /**
   * Test for getCoefficient function.
   */
  @org.junit.Test
  public void testGetCoefficient() {
    String terms = "3x^4 -5x^3 +2x^1 -4";
    polynomial = new PolynomialImpl(terms);
    assertEquals(-5, (long) polynomial.getCoefficient(3));
  }

  /**
   * Test for getDegree function.
   */
  @org.junit.Test
  public void testGetDegree() {
    String terms = "-3x^4 -2x^5 -5 +11x^1";
    polynomial = new PolynomialImpl(terms);
    assertEquals(5, (long) polynomial.getDegree());
  }

  /**
   * Test for add function.
   */
  @org.junit.Test
  public void testAddRemoveTerm() {
    String terms = "-3x^4 -2x^5 -5 +11x^1";
    polynomial = new PolynomialImpl(terms);
    Polynomial addedTerm = new PolynomialImpl("3x^4 -5x^3 +2x^1 -4");
    Polynomial res = polynomial.add(addedTerm);
    assertEquals(0, res.getCoefficient(4));
  }

  /**
   * Test for multiply function.
   */
  @org.junit.Test
  public void testMultiply() {
    polynomial = new PolynomialImpl("6x^4 -4x^3");
    Polynomial term2 = new PolynomialImpl("3x^3 +2x^2 +4");
    Polynomial res = polynomial.multiply(term2);
    assertEquals(-8, (long) res.getCoefficient(5));
  }

  /**
   * Test for derivative function.
   */
  @org.junit.Test
  public void testDerivative() {
    String terms = "-3x^4 -2x^5 -5 +11x^1";
    polynomial = new PolynomialImpl(terms);
    assertEquals("-10x^4-12x^3+11", polynomial.derivative().toString());
  }

  /**
   * Test derivative when empty.
   */
  @org.junit.Test
  public void testDerivativeWhenEmpty() {
    polynomial = new PolynomialImpl();
    Polynomial result = polynomial.derivative();
    assertTrue(((PolynomialImpl) result).getHead() instanceof TermListADTEmptyNode);
  }

  /**
   * Test for derivative when power is zero.
   */
  @org.junit.Test
  public void testDerivativeWhenPowerIsZero() {
    polynomial = new PolynomialImpl("5x^0");
    Polynomial result = polynomial.derivative();
    assertEquals(0, result.getCoefficient(0));
  }

  /**
   * Test for toString function.
   */
  @org.junit.Test
  public void testToString() {
    String terms = "-3x^4 -2x^5 -5 +11x^1";
    polynomial = new PolynomialImpl(terms);
    assertEquals("-2x^5-3x^4+11x^1-5", polynomial.toString());
  }

  /**
   * Test for isSame function, depending on whether two object's value are same.
   */
  @org.junit.Test
  public void testIsSame() {
    polynomial = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    PolynomialImpl term2 = new PolynomialImpl("-5 +11x^1 -3x^4 -2x^5 ");
    assertTrue(term2.equals((PolynomialImpl) polynomial));
  }

  /**
   * Test for toString function when the current object is an empty polynomial.
   */
  @org.junit.Test
  public void testToStringWhenEmpty() {
    assertEquals("0", (String) (new PolynomialImpl().toString()));
  }

  /**
   * Test for default constructor.
   */
  @org.junit.Test
  public void testDefaultConstructor() {
    polynomial = new PolynomialImpl();
    assertTrue(((PolynomialImpl) polynomial).getHead() instanceof TermListADTEmptyNode);
  }

  /**
   * Test for string constructor encountering invalid character.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testInvalidInputConstructor() {
    String terms = "-3x^4 add-2x^5 -5 +11x^1";
    polynomial = new PolynomialImpl(terms);
  }

  /**
   * Test for addTerm when the power is invalid.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testInvalidPowerAddTerm() {
    polynomial = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    polynomial.addTerm(2, -3);
  }

  /**
   * Test for getCoefficient function when there is no such power.
   */
  @org.junit.Test
  public void testGetNotExistCoefficient() {
    polynomial = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    assertEquals(0, polynomial.getCoefficient(6));
  }

  /**
   * Test for derivative function when current object is empty polynomial.
   */
  @org.junit.Test
  public void testDerivativeEmpty() {
    polynomial = new PolynomialImpl();
    assertEquals("0", polynomial.derivative().toString());
  }

  /**
   * Test for isSame function when the two given object are not the same.
   */
  @org.junit.Test
  public void testNotSame() {
    polynomial = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    PolynomialImpl term2 = new PolynomialImpl("-5 +11x^1 ");
    assertFalse(term2.equals(polynomial));
  }

}