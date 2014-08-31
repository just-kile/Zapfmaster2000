package de.justkile.zapfmaster2000.services

import de.justkile.zapfmaster2000.model.User
import grails.transaction.Transactional

@Transactional
class SharedQueriesService {

    double retrieveDrawingAmount(long pUserId, Date pFrom,
                                 Date pTo) {
        def users = User.executeQuery("SELECT sum(d.amount) FROM Drawing d "
                        + "WHERE d.user.id = ? AND d.date >= ? AND d.date <= ? "
                        + "GROUP BY d.user.id", [pUserId, pFrom, pTo])

        if (users.size() == 1) {
            return users.get(0);
        }

        return 0;
    }
}
