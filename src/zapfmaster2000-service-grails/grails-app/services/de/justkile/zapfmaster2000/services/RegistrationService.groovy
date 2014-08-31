package de.justkile.zapfmaster2000.services

import de.justkile.zapfmaster2000.model.Account
import de.justkile.zapfmaster2000.model.User
import de.justkile.zapfmaster2000.model.User.Sex
import grails.transaction.Transactional

@Transactional
class RegistrationService {

    private static def DEFAULT_IMAGE = 'images/others/defaultUser.png'

    def registerUser(Account account, String name, String password, Sex sex, int weight) {
        if (User.findByName(name)) {
            throw new RegistrationException("User with name $name exists already.")
        } else {
            def user = new User(
                    name: name,
                    password: password,
                    sex: sex,
                    weight: weight,
                    imagePath: DEFAULT_IMAGE,
                    account: account
            );
            user.save()
            return user
        }
    }

}
