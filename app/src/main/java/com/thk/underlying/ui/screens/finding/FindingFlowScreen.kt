package com.thk.underlying.ui.screens.finding

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thk.underlying.R
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
import com.thk.underlying.ui.components.UnderlineTextButton
import com.thk.underlying.ui.components.dialog.SimpleDialog
import com.thk.underlying.ui.theme.UnderlyingTheme
import com.thk.underlying.ui.theme.textButtonSmall

@Composable
fun FindingFlowScreen(
    visibleList: List<InputModel>,
    moveToPrev: () -> Unit,
    moveToNext: () -> Unit,
    updateInput: (InputModel) -> Unit,
    moveToHomeScreen: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
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

        // 종료 다이얼로그 표시 플래그
        var quitDialogVisible: Boolean by remember { mutableStateOf(false) }

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

        LazyColumn(
            contentPadding = PaddingValues(start = 8.dp, top = 20.dp, end = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            itemsIndexed(
                items = visibleList,
                key = { index: Int, item: InputModel -> index },
                contentType = { index: Int, item: InputModel -> item.step }
            ) {index: Int, item: InputModel ->

                AnimatedVisibility(
                    visible = index == visibleList.lastIndex - 1 || index == visibleList.lastIndex,
                    enter = fadeIn(tween(500)) + expandVertically(),
                    exit = fadeOut(tween(200)) + shrinkVertically(
                        animationSpec = tween(
                            easing = LinearOutSlowInEasing,
                            delayMillis = 50,
                        ),
                    ),
                ) {
                    when (item.step) {
                        FlowStep.INTRO_FIRST -> {
                            require(item is EmotionInputModel)

                            IntroFirstQuestionText(
                                enabled = index == visibleList.lastIndex,
                                emotion = item.emotion,
                                onEmotionSelected = { inputEmotion = it },
                            )
                        }
                        FlowStep.INTRO_SECOND -> {
                            require(item is StringInputModel)

                            IntroSecondQuestionText(
                                enabled = index == visibleList.lastIndex,
                                text = item.reason,
                                onTextChange = { inputReason = it },
                            )
                        }
                        FlowStep.INTRO_THIRD -> {
                            require(item is StringInputModel)

                            RepeatFirstQuestionText(
                                enabled = index == visibleList.lastIndex,
                                keyword = "${(visibleList[index - 1] as StringInputModel).reason} 때문이",
                                text = item.reason,
                                onTextChange = { inputReason = it },
                            )
                        }
                        FlowStep.REPEAT_FIRST -> {
                            require(item is EmotionInputModel)

                            RepeatSecondQuestionText(
                                enabled = index == visibleList.lastIndex,
                                emotion = item.emotion,
                                onEmotionSelected = { inputEmotion = it },
                            )
                        }
                        FlowStep.REPEAT_SECOND -> {
                            require(item is StringInputModel)

                            RepeatFirstQuestionText(
                                enabled = index == visibleList.lastIndex,
                                keyword = "${(visibleList[index - 1] as EmotionInputModel).emotion?.linkingEnding}",
                                text = item.reason,
                                onTextChange = { inputReason = it },
                            )
                        }
                    }
                }

                AnimatedVisibility(
                    visible = index == visibleList.lastIndex-1
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

            item {
                AnimatedVisibility(
                    visible = (visibleList.last().step == FlowStep.REPEAT_FIRST) && (inputEmotion != null),
                    enter = fadeIn(tween(300)),
                    exit = fadeOut(tween(100))
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.height(100.dp))
                        UnderlineTextButton(
                            text = "알맹이를 찾았어요",
                            color = Color.White,
                            style = MaterialTheme.typography.textButtonSmall,
                            onClick = { /*TODO*/ }
                        )
                    }
                }


            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UnderlineTextButton(
                text = "그만하기",
                color = Color.White.copy(alpha = 0.75f),
                style = MaterialTheme.typography.textButtonSmall,
                onClick = { quitDialogVisible = true },
            )

            Spacer(modifier = Modifier.height(80.dp))
        }

        // 종료 다이얼로그
        AnimatedVisibility(
            visible = quitDialogVisible,
            exit = fadeOut(tween(100)) + shrinkVertically()
        ) {
            SimpleDialog(
                content = stringResource(id = R.string.dialog_message_quit_finding),
                onDismiss = { quitDialogVisible = false },
                onConfirmClick = {
                    quitDialogVisible = false
                    moveToHomeScreen()
                },
                onCancelClick = { quitDialogVisible = false }
            )
        }

        // 시스템 back 버튼
        BackHandler { quitDialogVisible = true }
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
                updateInput = viewModel::updateInput,
                moveToHomeScreen = {}
            )
        }
    }
}