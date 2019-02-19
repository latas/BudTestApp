package com.bud.app.com.bud.app.core.formatters

import javax.inject.Inject

class AmountFormatter @Inject constructor() {

    fun format(amount: Double): String = "£${Math.abs(amount)}"

}