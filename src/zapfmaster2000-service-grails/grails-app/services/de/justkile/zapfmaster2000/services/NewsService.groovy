package de.justkile.zapfmaster2000.services

import de.justkile.zapfmaster2000.model.*
import de.justkile.zapfmaster2000.model.news.DrawingNews
import de.justkile.zapfmaster2000.model.news.NewKegNews
import de.justkile.zapfmaster2000.model.news.News
import de.justkile.zapfmaster2000.utils.BlockingDataDelegator
import de.justkile.zapfmaster2000.utils.zapfing.LoginFailureReason
import de.justkile.zapfmaster2000.utils.zapfing.ZapfServiceListener
import grails.transaction.Transactional

@Transactional
class NewsService {

    final ZapfServiceListener zapfServiceListener = createZapfServiceListner()

    def zapfKitService
    def kegService
    def mapAccountId2NewsDelegator = [:]


    def init() {
        zapfKitService.addListener(new ZapfKitServiceListener() {
            void onZapfKitCreated(ZapfKit zapfKit) {
                println "adding $zapfServiceListener"
                zapfKitService.retrieveZapfService(zapfKit).addListener(zapfServiceListener)
            }
        })
        ZapfKit.list().each {
            zapfKitService.retrieveZapfService(it).addListener(zapfServiceListener)
        }
        kegService.addKegServiceListener(createKegServiceListener())
    }

    /**
     * Returns the next news for a given account.
     *
     * <p>
     *     This is a blocking call.
     * </p>
     */
    def retrieveNextNews(Account account) {
        if (!mapAccountId2NewsDelegator[account.id]) {
            mapAccountId2NewsDelegator[account
                    .id] = new BlockingDataDelegator()
        }
        return mapAccountId2NewsDelegator[account.id].waitForData()
    }

    def publishNews(News news) {
        news.save()
        mapAccountId2NewsDelegator[news.account.id]?.pushData(news)
    }

    private def createZapfServiceListner() {
        return new ZapfServiceListener() {

            void onLoginSuccessful(User user) {
            }

            void onDrawing(User user, double amount) {
            }

            void onEndDrawing(Drawing drawing) {
                DrawingNews.withTransaction {
                    def d = Drawing.get(drawing.id)
                    publishNews(new DrawingNews(
                            drawing: d,
                            account: d.user.account
                    ))
                }
            }

            void onLogout(User user) {
            }

            void onLoginFailed(LoginFailureReason reason, long rfidId) {
            }
        }
    }

    private KegServiceListener createKegServiceListener() {
        new KegServiceListener() {
            @Override
            def onNewKeg(Keg keg) {
                publishNews(new NewKegNews(
                        account: keg.zapfKit.account,
                        keg: keg
                ))
            }
        }
    }
}
