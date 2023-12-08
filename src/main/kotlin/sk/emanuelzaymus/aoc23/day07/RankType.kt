package sk.emanuelzaymus.aoc23.day07

enum class RankType(val strength: Int) {
    FIVE_OF_A_KIND(7),
    FOUR_OF_A_KIND(6),
    FULL_HOUSE(5),
    THREE_OF_A_KIND(4),
    TWO_PAIRS(3),
    ONE_PAIR(2),
    HIGH_CARD(1);

    companion object {
        fun determineRankType(cards: String, withJokers: Boolean): RankType {
            return if (withJokers) determineRankTypeWithJokers(cards)
            else determineRankType(cards)
        }

        private fun determineRankType(cards: String): RankType {
            val cardCounts = cards
                .groupingBy { it }
                .eachCount()
                .map { it.value }
                .sortedDescending()

            return when (cardCounts.size) {
                1 -> FIVE_OF_A_KIND
                2 -> if (cardCounts.first() == 4) FOUR_OF_A_KIND else FULL_HOUSE
                3 -> if (cardCounts.first() == 3) THREE_OF_A_KIND else TWO_PAIRS
                4 -> ONE_PAIR
                5 -> HIGH_CARD
                else -> throw UnexpectedCardCountException(cardCounts)
            }
        }

        private fun determineRankTypeWithJokers(cards: String): RankType {
            var jokerCount: Int

            val cardCounts = cards
                .groupingBy { it }
                .eachCount()
                .let { map ->
                    jokerCount = map.getOrDefault('J', 0)
                    map - 'J'
                }
                .map { it.value }
                .sortedDescending()

            if (jokerCount == 0) {
                return when (cardCounts.size) {
                    1 -> FIVE_OF_A_KIND
                    2 -> if (cardCounts.first() == 4) FOUR_OF_A_KIND else FULL_HOUSE
                    3 -> if (cardCounts.first() == 3) THREE_OF_A_KIND else TWO_PAIRS
                    4 -> ONE_PAIR
                    5 -> HIGH_CARD
                    else -> throw UnexpectedCardCountException(cardCounts)
                }
            }

            val firstCardCount by lazy { cardCounts.first() }

            return when (cardCounts.size) {
                0 -> FIVE_OF_A_KIND

                1 -> when {
                    firstCardCount == 4 && jokerCount == 1 -> FIVE_OF_A_KIND
                    firstCardCount == 3 && jokerCount == 2 -> FIVE_OF_A_KIND
                    firstCardCount == 2 && jokerCount == 3 -> FIVE_OF_A_KIND
                    firstCardCount == 1 && jokerCount == 4 -> FIVE_OF_A_KIND
                    firstCardCount == 0 && jokerCount == 5 -> FIVE_OF_A_KIND
                    else -> throw UnexpectedCardCountException(cardCounts, jokerCount)
                }

                2 -> when {
                    firstCardCount == 3 /* 1 */ && jokerCount == 1 -> FOUR_OF_A_KIND
                    firstCardCount == 2 /* 2 */ && jokerCount == 1 -> FULL_HOUSE
                    firstCardCount == 2 /* 1 */ && jokerCount == 2 -> FOUR_OF_A_KIND
                    firstCardCount == 1 /* 1 */ && jokerCount == 3 -> FOUR_OF_A_KIND
                    else -> throw UnexpectedCardCountException(cardCounts, jokerCount)
                }

                3 -> when {
                    firstCardCount == 2 /* 1 1 */ && jokerCount == 1 -> THREE_OF_A_KIND
                    firstCardCount == 1 /* 1 1 */ && jokerCount == 2 -> THREE_OF_A_KIND
                    else -> throw UnexpectedCardCountException(cardCounts, jokerCount)
                }

                4 -> when {
                    firstCardCount == 1 /* 1 1 1 */ && jokerCount == 1 -> ONE_PAIR
                    else -> throw UnexpectedCardCountException(cardCounts, jokerCount)
                }

                else -> throw UnexpectedCardCountException(cardCounts, jokerCount)
            }
        }

    }

    class UnexpectedCardCountException(cardCounts: List<Int>, jokerCount: Int? = null) :
        Exception("Unexpected cardCounts: $cardCounts and jokerCount: $jokerCount")
}
