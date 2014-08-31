package de.justkile.zapfmaster2000.controller

import de.justkile.zapfmaster2000.model.news.News
import de.justkile.zapfmaster2000.services.AuthException
import de.justkile.zapfmaster2000.utils.news.NewsAdapter
import grails.converters.JSON
import grails.transaction.Transactional

import static grails.async.Promises.tasks

class NewsController {

    static responseFormats = ['json', 'xml']
    def authService
    def newsService
    NewsAdapter newsAdapter = new NewsAdapter()

    @Transactional(readOnly = true)
    def retrieveNews() {
        def start = params.int('start', 0)
        def length = params.int('length', 15)
        try {
            def account = authService.retrieveAccount(params.token)
            def result = News.executeQuery('SELECT n FROM News n WHERE n.account = ? ORDER BY n.date DESC', [account], [max: length, offset: start])
            respond result.collect {newsAdapter.adapt(it)}
        } catch (AuthException e) {
            response.sendError 403
        }
    }

    @Transactional(readOnly = true)
    def retrieveNextNewsAsync() {
        try {
            def account = authService.retrieveAccount(params.token)
            tasks { render newsAdapter.adapt( newsService.retrieveNextNews(account) ) as JSON}
        } catch (AuthException e) {
            response.sendError 403
        }
    }
}
