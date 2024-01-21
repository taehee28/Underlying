package com.thk.underlying.ui.screens.finding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.thk.underlying.models.EmotionInputModel
import com.thk.underlying.models.FlowStep
import com.thk.underlying.models.InputModel
import com.thk.underlying.models.StringInputModel
import com.thk.underlying.utils.logd

class FindingFlowViewModel : ViewModel() {
    private val _inputList: MutableList<InputModel> = mutableStateListOf(
        EmotionInputModel(step = FlowStep.INTRO_FIRST, emotion = null)
    )
    val inputList: List<InputModel>
        get() = _inputList

    fun moveToPrev() {
        if (_inputList.lastIndex == 0) {
            return
        }

        _inputList.removeLastOrNull()
    }

    fun moveToNext() {
        _inputList.add(createNewInputModel())
    }

    fun updateInput(input: InputModel) {
        _inputList[_inputList.lastIndex] = input
    }

    private fun createNewInputModel(): InputModel =
        when (val flowStep = FlowStep.getStep(_inputList.lastIndex + 1)) {
            FlowStep.INTRO_FIRST, FlowStep.REPEAT_FIRST -> {
                EmotionInputModel(step = flowStep, emotion = null)
            }
            FlowStep.INTRO_SECOND, FlowStep.INTRO_THIRD, FlowStep.REPEAT_SECOND -> {
                StringInputModel(step = flowStep, reason = null)
            }
        }
}