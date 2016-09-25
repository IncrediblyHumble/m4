package serverUtil.impl;

import serverUtil.Login;

/**
 * Created by noam on 9/25/16.
 */
public class LoginHardcoded implements Login{
    int count = 0;
    @Override
    public boolean verify(String username, String password) throws TriesExceededException {
        if(count == 2){
            return false;
        }
        if(username.equals("foo") && password.equals("bar")){
            count = 0;
            return true;
        }
        else {
            count++;
            if(count == 2){
                throw new TriesExceededException();
            }
            return false;
        }
    }
}
