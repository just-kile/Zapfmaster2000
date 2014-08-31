package de.justkile.zapfmaster2000.controller

import de.justkile.zapfmaster2000.model.Account
import de.justkile.zapfmaster2000.model.Drawing
import de.justkile.zapfmaster2000.model.User
import de.justkile.zapfmaster2000.model.achievement.GainedAchievement
import de.justkile.zapfmaster2000.services.AuthException
import grails.transaction.Transactional
import org.hibernate.criterion.CriteriaSpecification

class StatisticsController {

    static responseFormats = ['json', 'xml']

    def authService

    @Transactional(readOnly = true)
    def retrieveUserStats() {
        try {
            def user = authService.retrieveUser(params.token)
            respond(
                    amount: retrieveUserDrawingStats(user),
                    achievement: retrieveAchievementListing(user)
            )
        } catch (AuthException e) {
            response.sendError 403
        }
    }

    @Transactional(readOnly = true)
    def retrieveUserRanking() {
        try {
            def account = authService.retrieveAccount(params.token)
            def max = params.int('max', Integer.MAX_VALUE)
            def result = internalRetrieveUserRanking(account, max)
            respond result
        } catch (AuthException e) {
            response.sendError 403
        }
    }


    private def internalRetrieveUserRanking(Account account, int max) {
        User.createCriteria().list {
            eq('account', account)
            createAlias('drawings', 'drawings', CriteriaSpecification.LEFT_JOIN)
            projections {
                property('id')
                property('name')
                property('imagePath')
                sum('drawings.amount', 'sum')
                groupProperty('id')
            }
            order('sum', 'desc')
            maxResults(max)
        }.collect {
            [
                    id    : it[0],
                    name  : it[1],
                    image : it[2],
                    amount: it[3]
            ]
        }
    }

    private def retrieveUserDrawingStats(User user) {
        Drawing.createCriteria().get {
            projections {
                sum('amount')
                max('amount')
                groupProperty('user')
            }
            eq('user', user)
        }.collect {
            [
                amountTotal: it[0] ? it[0] : 0,
                greatestDrawing: it[1] ? it[1] : 0
            ]
        }
    }

    private def retrieveAchievementListing(User user) {
        def gainedAchievements = GainedAchievement.findByUser(user)
        def achievements = gainedAchievements.collect {
            def achievement = it.achievement
            [
                    achievementName       : achievement.name,
                    achievementId         : id,
                    achievementImage      : achievement.imagePath,
                    achievementDescription: achievement.description,
                    date                  : it.date
            ]
        }
        return [achievements: achievements, count: achievements.size()]
    }
}
