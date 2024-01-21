package com.thk.underlying.ui.screens.finding

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thk.underlying.models.Emotion
import com.thk.underlying.models.EmotionInputModel
import com.thk.underlying.models.FlowStep
import com.thk.underlying.models.GradientState
import com.thk.underlying.models.InputModel
import com.thk.underlying.models.StringInputModel
import com.thk.underlying.ui.components.GradientBackground
import com.thk.underlying.ui.components.IntroFirstQuestionText
import com.thk.underlying.ui.components.IntroSecondQuestionText
import com.thk.underlying.ui.components.NavigationButtonRow
import com.thk.underlying.ui.components.RepeatFirstQuestionText
import com.thk.underlying.ui.components.RepeatSecondQuestionText
import com.thk.underlying.ui.theme.UnderlyingTheme
import com.thk.underlying.utils.logd

@Composable
fun FindingFlowScreen(
    visibleList: List<InputModel>,
    moveToPrev: () -> Unit,
    moveToNext: () -> Unit,
    updateInput: (InputModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp)
            .padding(horizontal = 16.dp)
    ) {
        var inputReason: String? by remember(visibleList.last()) {
            mutableStateOf((visibleList.last() as? StringInputModel)?.reason)
        }
        var inputEmotion: Emotion? by remember(visibleList.last()) {
            mutableStateOf((visibleList.last() as? EmotionInputModel)?.emotion)
        }

        NavigationButtonRow(
            prevEnable = { visibleList.lastIndex != 0 },
            onPrevClick = { moveToPrev() },
            nextEnable = { inputReason.isNullOrBlank().not() || (inputEmotion != null) },
            onNextClick = {
                val newInput: InputModel = when(val currentInput = visibleList.last()) {
                    is EmotionInputModel -> {
                        currentInput.copy(emotion = inputEmotion)
                    }
                    is StringInputModel -> {
                        currentInput.copy(reason = inputReason)
                    }
                }
                updateInput(newInput)

                moveToNext()
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp),
            /*verticalArrangement = Arrangement.spacedBy(24.dp),*/
            modifier = Modifier
                .fillMaxWidth()
        ) {
            itemsIndexed(
                items = visibleList,
                key = { index: Int, item: InputModel -> index }
            ) {index: Int, item: InputModel ->
                logd(">> LazyColumn: $index")

                if (index == visibleList.lastIndex-1 || index == visibleList.lastIndex) {
                    when (item.step) {
                        FlowStep.INTRO_FIRST -> {
                            require(item is EmotionInputModel)

                            IntroFirstQuestionText(
                                enabled = index == visibleList.lastIndex,
                                emotion = item.emotion,
                                onEmotionSelected = { inputEmotion = it }
                            )
                        }
                        FlowStep.INTRO_SECOND -> {
                            require(item is StringInputModel)

                            IntroSecondQuestionText(
                                enabled = index == visibleList.lastIndex,
                                text = item.reason,
                                onTextChange = { inputReason = it }
                            )
                        }
                        FlowStep.INTRO_THIRD -> {
                            require(item is StringInputModel)

                            RepeatFirstQuestionText(
                                enabled = index == visibleList.lastIndex,
                                keyword = "${(visibleList[index - 1] as StringInputModel).reason} 때문이",
                                text = item.reason,
                                onTextChange = { inputReason = it }
                            )
                        }
                        FlowStep.REPEAT_FIRST -> {
                            require(item is EmotionInputModel)

                            RepeatSecondQuestionText(
                                enabled = index == visibleList.lastIndex,
                                emotion = item.emotion,
                                onEmotionSelected = { inputEmotion = it }
                            )
                        }
                        FlowStep.REPEAT_SECOND -> {
                            require(item is StringInputModel)

                            RepeatFirstQuestionText(
                                enabled = index == visibleList.lastIndex,
                                keyword = "${(visibleList[index - 1] as EmotionInputModel).emotion?.linkingEnding}",
                                text = item.reason,
                                onTextChange = { inputReason = it }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }

            }
        }
    }
}

@Preview(name = "default display")
@Preview(device = "id:8in Foldable", name = "fold-out display")
@Preview(device = "id:6.7in Foldable", name = "fold-in display")
@Composable
fun FindingFlowScreenPreview() {
    val viewModel: FindingFlowViewModel = viewModel()

    UnderlyingTheme {
        GradientBackground(stateProvider = { GradientState.FULL_DARK }) {
            FindingFlowScreen(
                visibleList = viewModel.inputList,
                moveToNext = viewModel::moveToNext,
                moveToPrev = viewModel::moveToPrev,
                updateInput = viewModel::updateInput
            )
        }
    }
}