package springusermanagement.service;

import java.util.ArrayList;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import springusermanagement.model.ForgotPassBean;
import springusermanagement.model.UserBean;

public interface SignupService {
    int addNewUser(UserBean user);

    int checkUser(String email, String pass);

    ArrayList getUserDetails();

    void deleteUser(int userId) throws SecurityException, RollbackException, HeuristicMixedException,
            HeuristicRollbackException, SystemException;

    void updateUser(UserBean user);

    boolean checkForgotpassDetails(String dob, String securityAns);

    void changePass(ForgotPassBean forgotPass);

    ArrayList getRecentUsersList();

    boolean checkEmail(String email);

    UserBean getLoggedinUserDetails(String email);
}
