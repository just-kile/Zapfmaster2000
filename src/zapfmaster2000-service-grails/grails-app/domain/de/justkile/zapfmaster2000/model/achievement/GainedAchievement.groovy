package de.justkile.zapfmaster2000.model.achievement

import de.justkile.zapfmaster2000.model.User
import de.justkile.zapfmaster2000.model.news.AchievementNews

class GainedAchievement {

    Achievement achievement
    User user
    Date date = new Date()

    static constraints = {
    }
}
