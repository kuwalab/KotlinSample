package test

import org.junit.Test
import kotlin.test.assertEquals

class LeapYearTest {
    Test fun 西暦1581年は閏年ではない() {
        assertEquals(false, isLeapYear(1581))
    }

    Test fun 西暦1582年は閏年ではない() {
        assertEquals(false, isLeapYear(1582))
    }

    Test fun 西暦1584年は閏年() {
        assertEquals(true, isLeapYear(1584))
    }
}
