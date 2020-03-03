package roman;

import static roman.RomanNumerals.roman;
import static org.junit.Assert.*;

import org.junit.Test;

public class RomanNumeralsTest {

	@Test
	public void one_arabic_digit_one_to_three() {
		assertEquals(  "I",  roman( 1));
		assertEquals( "II",  roman( 2));
		assertEquals("III",  roman( 3));
	}

	@Test
	public void one_arabic_digit_four_to_eight() {
		assertEquals( "IV",  roman( 4));
		assertEquals(  "V",  roman( 5));
		assertEquals( "VI",  roman( 6));
		assertEquals( "VII", roman( 7));
		assertEquals("VIII", roman( 8));
	}

	@Test
	public void one_arabic_digit_nine_and_ten() {
		assertEquals( "IX",  roman( 9));
		assertEquals(  "X",  roman(10));
	}

	
	@Test
	public void two_arabic_digits() {
		assertEquals(   "X", roman( 10));
		assertEquals(  "XX", roman( 20));
		assertEquals( "XXX", roman( 30));
		assertEquals(  "XL", roman( 40));
		assertEquals(   "L", roman( 50));
		assertEquals(  "LX", roman( 60));
		assertEquals( "LXX", roman( 70));
		assertEquals("LXXX", roman( 80));
		assertEquals(  "XC", roman( 90));
		assertEquals(   "C", roman(100));
	}

	@Test
	public void three_arabic_digits() {
		assertEquals(   "C", roman( 100));
		assertEquals(  "CC", roman( 200));
		assertEquals( "CCC", roman( 300));
		assertEquals(  "CD", roman( 400));
		assertEquals(   "D", roman( 500));
		assertEquals(  "DC", roman( 600));
		assertEquals( "DCC", roman( 700));
		assertEquals("DCCC", roman( 800));
		assertEquals(  "CM", roman( 900));
		assertEquals(   "M", roman(1000));
	}

	@Test
	public void four_arabic_digits() {
		assertEquals(   "M", roman(1000));
		assertEquals(  "MM", roman(2000));
		assertEquals( "MMM", roman(3000));
		assertEquals("MMMM", roman(4000));
	}

	@Test
	public void some_special_numbers() {
		assertEquals( "MCMXC", roman(1990));
		assertEquals("MMVIII", roman(2008));
		assertEquals(  "XCIX", roman(  99));
		assertEquals( "XLVII", roman(  47));
	}

	@Test
	public void boundary_cases(){
		assertEquals(    "IX", roman(   9));
		assertEquals(     "X", roman(  10));
		assertEquals(    "XI", roman(  11));
		assertEquals(  "XCIX", roman(  99));
		assertEquals(     "C", roman( 100));
		assertEquals(    "CI", roman( 101));
		assertEquals("CMXCIX", roman( 999));
		assertEquals(     "M", roman(1000));
		assertEquals(    "MI", roman(1001));
	}
}
