package de.justkile.zapfmaster2000.utils.zapfing

import de.justkile.zapfmaster2000.model.Drawing
import de.justkile.zapfmaster2000.model.User

/**
 * Created by thomas on 31.08.14.
 */
public interface ZapfServiceListener {

    void onLoginSuccessful(User user)

    void onDrawing(User user, double amount);

    void onEndDrawing(Drawing drawing);

    void onLogout(User user);

    void onLoginFailed(LoginFailureReason reason, long rfidId);

}