package springusermanagement.dao;

import org.springframework.stereotype.Repository;

import springusermanagement.model.UserBean;


public interface UserDao<T> {

    public T addNewUser(UserBean user);

}
