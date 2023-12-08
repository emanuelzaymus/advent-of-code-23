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
            var jokerCount: Int

            val cardCounts = cards
                .groupingBy { it }
                .eachCount()
                .let { map ->
                    jokerCount = map.getOrDefault('J', 0)
                    if (withJokers) map - 'J' else map
                }
                .map { it.value }
                .sortedDescending()

            return if (withJokers && jokerCount > 0) determineRankTypeWithCardCountsAndJokers(cardCounts, jokerCount)
            else determineRankTypeWithCardCounts(cardCounts)
        }

        private fun determineRankTypeWithCardCounts(cardCounts: List<Int>): RankType {
            return when (cardCounts.size) {
                1 -> FIVE_OF_A_KIND
                2 -> if (cardCounts.first() == 4) FOUR_OF_A_KIND else FULL_HOUSE
                3 -> if (cardCounts.first() == 3) THREE_OF_A_KIND else TWO_PAIRS
                4 -> ONE_PAIR
                5 -> HIGH_CARD
                else -> throw UnexpectedCardCountException(cardCounts)
            }
        }

        private fun determineRankTypeWithCardCountsAndJokers(cardCounts: List<Int>, jokerCount: Int): RankType {
            return when (cardCounts.size) {
                0, 1 -> FIVE_OF_A_KIND
                2 -> if (cardCounts.first() == 2 && jokerCount == 1) FULL_HOUSE else FOUR_OF_A_KIND
                3 -> THREE_OF_A_KIND
                4 -> ONE_PAIR
                else -> throw UnexpectedCardCountException(cardCounts, jokerCount)
            }
        }
    }

    class UnexpectedCardCountException(cardCounts: List<Int>, jokerCount: Int? = null) :
        Exception("Unexpected cardCounts: $cardCounts and jokerCount: $jokerCount")
}
