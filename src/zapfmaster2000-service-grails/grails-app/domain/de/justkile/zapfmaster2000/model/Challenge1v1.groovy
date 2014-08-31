package de.justkile.zapfmaster2000.model

class Challenge1v1 {

    long id
    Date startTime
    int duration
    State state

    User user1
    User user2
    User winner

    static constraints = {
        id name: 'id'
    }

    enum State {
        RUNNING, DECLINED, FINISHED
    }
}
