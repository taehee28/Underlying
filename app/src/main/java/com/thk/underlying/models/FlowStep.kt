package com.thk.underlying.models

import androidx.annotation.IntRange

enum class FlowStep {
    INTRO_FIRST,
    INTRO_SECOND,
    INTRO_THIRD,

    REPEAT_FIRST,
    REPEAT_SECOND;

    fun getStep(@IntRange(from = 0) stepCount: Int): FlowStep = when {
        stepCount == 0 -> INTRO_FIRST
        stepCount == 1 -> INTRO_SECOND
        stepCount == 2 -> INTRO_THIRD
        (stepCount % 2) == 1 -> REPEAT_FIRST
        else -> REPEAT_SECOND
    }
}