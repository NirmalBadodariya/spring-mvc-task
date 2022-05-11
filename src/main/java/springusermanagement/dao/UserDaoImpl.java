package springusermanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import springusermanagement.model.UserBean;
import springusermanagement.model.UserRoles;
import javax.persistence.*;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDaoImpl<T> {

    @Autowired
    protected SessionFactory factory;

    // public SessionFactory getFactory() {
    // return factory;
    // }

    // public void setFactory(SessionFactory factory) {
    // this.factory = factory;
    // }

    // Session session = factory.openSession();
    private Class<T> type;
    @Autowired
    private HibernateTemplate hibernateTemplate;
    // Session session = hibernateTemplate.getSessionFactory().openSession();
    // @Autowired
    // UserRoles roles = new UserRoles();

    @Transactional
    public T addNewUser(UserBean user) {

        // String SQL_QUERY = " insert into UserRoles (role) values (:givenrole)";
        // hibernateTemplate.getSessionFactory().openSession().createSQLQuery(SQL_QUERY).setParameter("givenrole",
        // 1)
        // .executeUpdate();
        // roles.setRole(1);

        // roles.setRole(1);
        // session.save(roles);
        // roles.setUid(user.getId());

        return (T) hibernateTemplate.save(user);
    }

    public boolean checkUser(T email, T pass) {
        boolean userFound = false;
        String QUERY = " from UserBean as o where o.email=?0 and o.pass=?1";
        List list = hibernateTemplate.getSessionFactory().openSession().createQuery(QUERY).setParameter(0, email)
                .setParameter(1, pass).list();
        // Query query = session.createQuery(SQL_QUERY);
        // List list = (List) hibernateTemplate.find(SQL_QUERY, email, pass);

        if ((list != null) && (list.size() > 0)) {
            userFound = true;
        }
        return userFound;
    }

    public ArrayList<UserBean> getUserDetails() {
        ArrayList<UserBean> userDetails = new ArrayList<>();
        Session session = hibernateTemplate.getSessionFactory().openSession();
        Query query = session.createQuery("from UserBean");
        userDetails = (ArrayList<UserBean>) query.getResultList();
        return userDetails;
    }

    public void deleteUser(T user) throws SecurityException, RollbackException, HeuristicMixedException,
            HeuristicRollbackException, SystemException {

        hibernateTemplate.delete(user);

    }

    public UserBean getLoggedinUserDetails(String email) {
        ArrayList<UserBean> userDetails = new ArrayList<>();
        Session session = hibernateTemplate.getSessionFactory().openSession();
        Query query = session.createQuery("from UserBean where email=:email").setParameter("email", email);
        userDetails = (ArrayList<UserBean>) query.getResultList();
        return userDetails.get(0);
    }

    public void updateuser(UserBean user) {
        hibernateTemplate.merge(user);
    }

}
