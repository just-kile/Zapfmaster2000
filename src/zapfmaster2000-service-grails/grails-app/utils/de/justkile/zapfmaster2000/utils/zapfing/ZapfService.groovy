package de.justkile.zapfmaster2000.utils.zapfing

import de.justkile.zapfmaster2000.model.*
import de.justkile.zapfmaster2000.utils.constants.PlatformConstants
import org.apache.log4j.Logger


/**
 * Created by thomas on 31.08.14.
 */
class ZapfService {

    private final Logger LOG = Logger.getLogger(ZapfService.class)

    private final Object SYNC_USER_LOCK = new Object()
    private final currentDrawingTicks = []
    private final List<ZapfServiceListener> listeners = []

    private ZapfKit zapfKit
    private User guestUser
    private User currentUser
    private long lastDrawing
    private long totalTicks
    private Timer timer

    ZapfService(ZapfKit zapfKit) {
        this.zapfKit = zapfKit
    }

    def User login(long rfidId) {
        synchronized (SYNC_USER_LOCK) {
            if (currentUser?.getRfidTag() != rfidId) {

                // new user logs in
                if (checkNewUserCanLogin(rfidId)) {
                    def newUser = User.findByRfidTag rfidId
                    if (newUser) {
                        // login succeeded
                        if (currentUser) {
                            finishCurrentDraw();
                        }

                        currentUser = newUser;
                        scheduleAutoLogout();
                        lastDrawing = System.currentTimeMillis();
                        notifyLoginSuccessful(currentUser);
                        return currentUser;
                    } else {
                        notifyLoginFailed(LoginFailureReason.INVALID_RFID_TAG,
                                rfidId);
                    }
                } else {
                    notifyLoginFailed(
                            LoginFailureReason.OTHER_USER_IS_LOGGED_IN, rfidId);
                }
            } else {
                // current user logs in
                scheduleAutoLogout();
                return currentUser;
            }
        }

        // log did fail
        return null;
    }

    def draw(int pRawAmount) {
        if (pRawAmount < PlatformConstants.MIN_TICKS) {
            // ignore too little tick counts, otherwise a user might not get
            // logged out even if he is not doing anything
            return calcRealAmount(totalTicks)
        }

        currentDrawingTicks.add(new Ticks(numTicks: pRawAmount))

        scheduleAutoLogout()
        lastDrawing = System.currentTimeMillis()

        totalTicks += pRawAmount
        // ignore to little amounts. This is most likely a "problem" of the
        // flowmeter
        double realAmount = calcRealAmount(totalTicks)
        if (realAmount > PlatformConstants.MIN_DRAW_AMOUNT) {
            if (currentUser) {
                notifyDrawing(currentUser, realAmount)
            } else {
                // guest starts to draw
                guestUser = findGuest()
                notifyLoginSuccessful(guestUser)
                currentUser = guestUser
                notifyDrawing(guestUser, realAmount)
                scheduleAutoLogout()
            }

        }

        return realAmount;
    }

    public void addListener(ZapfServiceListener listener) {
        if (listener) {
            listeners.add(listener)
        }
    }

    /**
     * Checks if a new user may login to the this box.
     *
     * @return <code>true</code> if a login is allowed, <code>false</code>
     *         otherwise.
     */
    private boolean checkNewUserCanLogin(long pId) {
        if (currentUser == null || currentUser == guestUser) {
            return true;
        }
        double diff = System.currentTimeMillis() - lastDrawing;
        return diff > PlatformConstants.MIN_LOGIN_DIFF
    }

    private void notifyLoginSuccessful(User pUser) {
        listeners.each {
            it.onLoginSuccessful(pUser)
        }
    }

    private void notifyDrawing(User pUser, double pAmount) {
        listeners.each {
            it.onDrawing(pUser, pAmount);
        }
    }

    private void notifyEndDrawing(Drawing pDrawing) {
        listeners.each {
            it.onEndDrawing(pDrawing);
        }
    }

    private void notifyLogout(User pUser) {
        listeners.each {
            it.onLogout(pUser);
        }
    }

    private void notifyLoginFailed(LoginFailureReason pReason, long pRfidId) {
        listeners.each {
            it.onLoginFailed(pReason, pRfidId);
        }
    }

    private void scheduleAutoLogout() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        int time = PlatformConstants.BOX_LOGIN_AUTO_LOGOUT;
        timer.schedule(createTimerTask(), time);
    }

    private TimerTask createTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    synchronized (SYNC_USER_LOCK) {
                        if (currentUser != null) {
                            LOG.info("Auto-Logout for user: "
                                    + currentUser.getName());
                            finishCurrentDraw();
                            currentUser = null;
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace()
                    LOG.error("Error while performing auto log out", th);
                }
            }
        };
    }

    private double calcRealAmount(long pRawTicks) {
        double realAmount = 0;
        for (Ticks ticks : currentDrawingTicks) {
            int rawTicks = ticks.numTicks
            double localAmount = zapfKit.getA0() + zapfKit.getA1() * rawTicks
            +zapfKit.getA2() * (rawTicks * rawTicks);
            realAmount += localAmount;
        }
        return realAmount;
    }

    /**
     * Finds the guest user. If there is no guest user in the db yet, it is
     * created now.
     *
     * @return guest user, never <code>null</code>.
     */
    private User findGuest() {
        zapfKit = ZapfKit.get(zapfKit.id)
        def guest = User.findByTypeAndAccount(User.Type.GUEST, zapfKit.account);
        if (!guest) {
            guest = new User(
                    name: 'Guest',
                    imagePath: 'images/others/guest.png',
                    weight: 100,
                    type: User.Type.GUEST,
                    account: zapfKit.account,
                    password: 'none',
                    sex: User.Sex.FEMALE)
            guest.save()
        }
        return guest;
    }

    private void finishCurrentDraw() {
        zapfKit = ZapfKit.read(zapfKit.id)
        currentUser = User.read(currentUser.id)
        println "!!! $currentUser"
        double realAmount = calcRealAmount(totalTicks);
        totalTicks = 0;

        boolean drewMinAmount = realAmount >= PlatformConstants.MIN_DRAW_AMOUNT
        if (currentUser && drewMinAmount) {
            // add drawing to database
            def activeKeg = Keg.createCriteria().get {
                eq('zapfKit', zapfKit)
                order('startDate', 'desc')
                maxResults(1)
            }
            def drawing = new Drawing(
                    amount: realAmount,
                    keg: activeKeg,
                    ticks: currentDrawingTicks,
                    user: currentUser
            )
            currentUser.drawings.add drawing
            println "valid? ${currentUser.validate()}"
            currentUser.save(failOnError: true)
            notifyEndDrawing(drawing);
        }

        if (currentUser) {
            notifyLogout(currentUser);
        }

        // reset values
        totalTicks = 0;
        currentUser = null;
        currentDrawingTicks.clear();


    }

}
