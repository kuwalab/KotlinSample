package test

import org.junit.Test
import org.junit.Assert.assertThat
import org.hamcrest.CoreMatchers.`is` as eq
import kotlin.test.assertEquals

class LeapYearTest {
    Test fun 西暦1581年は閏年ではない() {
        assertThat(isLeapYear(1581), eq(false))
        assertEquals(false, isLeapYear(1581))
    }

    Test fun 西暦1582年は閏年ではない() {
        assertEquals(false, isLeapYear(1582))
    }

    Test fun 西暦1584年は閏年() {
        assertEquals(true, isLeapYear(1584))
    }

    Test fun 西暦1900年は閏年ではない() {
        assertEquals(false, isLeapYear(1900))
    }

    Test fun 西暦2000年は閏年() {
        assertEquals(true, isLeapYear(2000))
    }

    Test fun 西暦2004年は閏年() {
        assertEquals(true, isLeapYear(2004))
    }

    Test fun 西暦2005年は閏年ではない() {
        assertEquals(false, isLeapYear(2005))
    }
}
