package com.chocolatada.crescendo.ui.player

class SongTimer(
    var minutes: Int,
    var seconds: Int
) {
    fun addOneSecond() {
        seconds += 1
        if(seconds >= 60) {
            minutes += 1
            seconds = 0
        }
    }
    fun calculateFinalTime(totalSeconds: Int) {
        var currentSeconds = totalSeconds
        while(currentSeconds > 60) {
            currentSeconds -= 60
            minutes += 1
        }
        seconds = currentSeconds
    }
    override fun toString(): String {
        if(seconds < 10) {
            return "$minutes:0$seconds"
        }
        return "$minutes:$seconds"
    }
}