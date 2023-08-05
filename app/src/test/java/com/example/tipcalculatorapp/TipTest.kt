package com.example.tipcalculatorapp

import junit.framework.TestCase.assertEquals
import org.junit.Test

class TipTest {

    @Test
    fun calculateTip_15PercentNoRoundUp(){
        val amount = "10"
        val percent = "15"
        val actualTip = 1.5f
        val resultedTip = getCalculatedTip(amount,percent,false)
        assertEquals(actualTip, resultedTip)
    }

    @Test
    fun calculateTip_15PercentRoundUp(){
        val amount = "10"
        val percent = "15"
        val actualTip = 2f
        val resultedTip = getCalculatedTip(amount,percent,true)
        assertEquals(actualTip, resultedTip)
    }

    @Test
    fun calculateTip_NegativePercentNoRoundUp(){
        val amount = "10"
        val percent = "-15"
        val actualTip = -1.5f
        val resultedTip = getCalculatedTip(amount,percent,false)
        assertEquals(actualTip, resultedTip)
    }

    @Test
    fun calculateTip_NegativePercentRoundUp(){
        val amount = "10"
        val percent = "-15"
        val actualTip = -1f
        val resultedTip = getCalculatedTip(amount,percent,true)
        assertEquals(actualTip, resultedTip)
    }
}