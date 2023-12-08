package sk.emanuelzaymus.aoc23.day07

data class CardHand(
    val cards: String,
    val bid: Int,
    val withJokers: Boolean,
    val rankTypeStrength: RankType = RankType.determineRankType(cards, withJokers),
    val sortableRepresentation: String = determineSortableRepresentation(cards, withJokers)
)

private val sortableCardRepresentations = mapOf(
    '2' to Char(50).toString(), // 2
    '3' to Char(51).toString(), // 3
    '4' to Char(52).toString(), // 4
    '5' to Char(53).toString(), // 5
    '6' to Char(54).toString(), // 6
    '7' to Char(55).toString(), // 7
    '8' to Char(56).toString(), // 8
    '9' to Char(57).toString(), // 9
    'T' to Char(58).toString(), // :
    'J' to Char(59).toString(), // ;
    'Q' to Char(60).toString(), // <
    'K' to Char(61).toString(), // =
    'A' to Char(62).toString(), // >
)

private val sortableCardRepresentationsWithJoker = mapOf(
    'J' to Char(49).toString(), // 1
    '2' to Char(50).toString(), // 2
    '3' to Char(51).toString(), // 3
    '4' to Char(52).toString(), // 4
    '5' to Char(53).toString(), // 5
    '6' to Char(54).toString(), // 6
    '7' to Char(55).toString(), // 7
    '8' to Char(56).toString(), // 8
    '9' to Char(57).toString(), // 9
    'T' to Char(58).toString(), // :

    'Q' to Char(60).toString(), // <
    'K' to Char(61).toString(), // =
    'A' to Char(62).toString(), // >
)

private fun determineSortableRepresentation(cards: String, withJokers: Boolean): String {
    return cards
        .asSequence()
        .joinToString("") { card ->
            (if (withJokers) sortableCardRepresentationsWithJoker else sortableCardRepresentations)
                .getOrElse(card) {
                    throw Exception("Card '$card' not found in sortableCardRepresentations")
                }
        }
}
