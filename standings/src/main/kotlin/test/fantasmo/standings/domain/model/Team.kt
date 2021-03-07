package test.fantasmo.standings.domain.model

data class Team(var name: String) {
    var point: Int = 0
    var goals: Int = 0
    var position: Int = 0

    constructor(name: String, point: Int, goals: Int = 0): this(name) {
        this.point = point
        this.goals = goals
    }

    fun toPointsTableEntry() = "$position. $name, $point pt${if (point != 1) "s" else ""}"
}