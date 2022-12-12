package com.tonynowater.momotest.ui.util

import org.junit.Assert.assertEquals
import org.junit.Test

class DateFormatUtilTest {

    @Test
    fun `format LastUpdateDate Success`() {
        val sourceDateString = "2022-11-10 09:52:23.255411"
        val expectedDateString = "2022/11/10"

        val output = DateFormatUtil.formatLastUpdateDate(sourceDateString)
        assertEquals(expectedDateString, output)
    }

    @Test
    fun `format LastUpdateDate Error`() {
        val sourceDateString = "2011-12-03T10:15:30+01:00"
        val expectedDateString = ""

        val output = DateFormatUtil.formatLastUpdateDate(sourceDateString)
        assertEquals(expectedDateString, output)
    }
}