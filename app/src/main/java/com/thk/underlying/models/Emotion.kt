package com.thk.underlying.models

/**
 * 감정 단어
 *
 * @param original 단어의 기본 형태
 * @param linkingEnding "-냐면" 어미가 붙은 형태
 * @param statementEnding "-다" 어미가 붙은 형태
 * @param wasEnding "-것 이었다" 어미가 붙은 형태
 */
data class Emotion(
    val original: String,
    val linkingEnding: String = "",
    val statementEnding: String = "",
    val wasEnding: String = ""
)