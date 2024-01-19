package com.thk.underlying.models

sealed interface InputModel {
    val step: FlowStep
}

data class StringInputModel(override val step: FlowStep, val reason: String?) : InputModel

data class EmotionInputModel(override val step: FlowStep, val emotion: Emotion?) : InputModel
