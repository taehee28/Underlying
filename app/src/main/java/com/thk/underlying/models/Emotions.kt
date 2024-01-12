package com.thk.underlying.models

object Emotions {
    val gloomy = Emotion(original = "우울함")
    val lethargy = Emotion(original = "무기력")
    val annoying = Emotion(original = "짜증")
    val badMood = Emotion(original = "안좋은 기분")
    val stress = Emotion(original = "스트레스")
    val loneliness = Emotion(original = "외로움")

    /*
    걱정, 불안, 두려움, 무서움, 지침, 힘듦, 후회, 막막함, 외면, 피하다, 부담, 긴장, 초조함, 싫음, 불편함, 불쾌함, 속상함, 서운함, 슬픔
     */
    val worry = Emotion(
        original = "걱정",
        linkingEnding = "걱정되",
        statementEnding = "걱정된",
        wasEnding = "걱정되는"
    )
    val anxiety = Emotion(
        original = "불안",
        linkingEnding = "불안하",
        statementEnding = "불안하",
        wasEnding = "불안한"
    )
    val fear = Emotion(
        original = "두려움",
        linkingEnding = "두렵",
        statementEnding = "두렵",
        wasEnding = "두려운"
    )
    val scary = Emotion(
        original = "무서움",
        linkingEnding = "무섭",
        statementEnding = "무섭",
        wasEnding = "무서운"
    )
    val exhausted = Emotion(
        original = "지침",
        linkingEnding = "지치",
        statementEnding = "지치",
        wasEnding = "지친"
    )
    val difficulty = Emotion(
        original = "힘듦",
        linkingEnding = "힘드",
        statementEnding = "힘들",
        wasEnding = "힘든"
    )
    val regret = Emotion(
        original = "후회",
        linkingEnding = "후회하",
        statementEnding = "후회된",
        wasEnding = "후회하는"
    )
    val hopeless = Emotion(
        original = "막막함",
        linkingEnding = "막막하",
        statementEnding = "막막하",
        wasEnding = "막막한"
    )
    val faceAway = Emotion(
        original = "외면",
        linkingEnding = "외면하고 싶",
        statementEnding = "외면하고 싶",
        wasEnding = "외면하고 싶은"
    )
    val avoid = Emotion(
        original = "피하다",
        linkingEnding = "피하고 싶",
        statementEnding = "피하고 싶",
        wasEnding = "피하고 싶은"
    )
    val pressure = Emotion(
        original = "부담",
        linkingEnding = "부담스럽",
        statementEnding = "부담스럽",
        wasEnding = "부담스러운"
    )
    val nerves = Emotion(
        original = "초조함",
        linkingEnding = "초조하",
        statementEnding = "초조하",
        wasEnding = "초조한"
    )
    val upset = Emotion(
        original = "속상함",
        linkingEnding = "속상하",
        statementEnding = "속상하",
        wasEnding = "속상한"
    )
    val hurt = Emotion(
        original = "서운함",
        linkingEnding = "서운하",
        statementEnding = "서운하",
        wasEnding = "서운한"
    )
    val sadness = Emotion(
        original = "슬픔",
        linkingEnding = "슬프",
        statementEnding = "슬프",
        wasEnding = "슬픈"
    )
    val hate = Emotion(
        original = "싫음",
        linkingEnding = "싫",
        statementEnding = "싫",
        wasEnding = "싫은"
    )
    val uncomfortable = Emotion(
        original = "불편함",
        linkingEnding = "불편하",
        statementEnding = "불편하",
        wasEnding = "불편한"
    )
    val unpleasant = Emotion(
        original = "불쾌함",
        linkingEnding = "불쾌하",
        statementEnding = "불쾌하",
        wasEnding = "불쾌한"
    )

    val abstractEmotions = arrayOf(
        gloomy,
        lethargy,
        annoying,
        badMood,
        stress,
        loneliness
    )

    val detailEmotions = arrayOf(
        worry,
        anxiety,
        fear,
        scary,
        exhausted,
        difficulty,
        regret,
        hopeless,
        faceAway,
        avoid,
        pressure,
        nerves,
        upset,
        hurt,
        sadness,
        hate,
        uncomfortable,
        unpleasant
    )
}