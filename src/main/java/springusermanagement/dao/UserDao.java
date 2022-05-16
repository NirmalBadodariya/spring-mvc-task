package springusermanagement.dao;

import java.util.ArrayList;

import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;

import springusermanagement.model.ForgotPassBean;
import springusermanagement.model.UserBean;

public interface UserDao<T> {
    
     T addNewUser(UserBean user);

     int checkUser(UserBean user);

     ArrayList<UserBean> getUserDetails();

     void deleteUser(T user) throws SecurityException, RollbackException, HeuristicMixedException,
            HeuristicRollbackException, SystemException, javax.transaction.RollbackException;

     UserBean getLoggedinUserDetails(String email);

     void updateuser(UserBean user);

     boolean checkForgotpassDetails(String dob, String securityAns);

     void changePass(ForgotPassBean forgotPass);

     ArrayList<UserBean> getRecentUsersList();

     boolean checkEmail(String email);

}
