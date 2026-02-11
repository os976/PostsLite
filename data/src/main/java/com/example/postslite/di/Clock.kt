package com.example.postslite.di

interface AppClock {
    fun now(): Long
}

class SystemAppClock : AppClock {
    override fun now(): Long = System.currentTimeMillis()
}
