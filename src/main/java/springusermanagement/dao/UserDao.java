package springusermanagement.dao;

import java.util.ArrayList;

import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;

import springusermanagement.model.ForgotPassBean;
import springusermanagement.model.UserBean;

public interface UserDao<T> {

    public T addNewUser(UserBean user);

    public int checkUser(UserBean user);

    public ArrayList<UserBean> getUserDetails();

    public void deleteUser(T user) throws SecurityException, RollbackException, HeuristicMixedException,
            HeuristicRollbackException, SystemException, javax.transaction.RollbackException;

    public UserBean getLoggedinUserDetails(String email);

    public void updateuser(UserBean user);

    public boolean checkForgotpassDetails(String dob, String securityAns);

    public void changePass(ForgotPassBean forgotPass);

    public ArrayList<UserBean> getRecentUsersList();

    public boolean checkEmail(String email);

}
