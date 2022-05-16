package springusermanagement.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springusermanagement.encryotion.AES;
import springusermanagement.model.ForgotPassBean;
import springusermanagement.model.UserBean;
import springusermanagement.model.UserRoles;
import javax.persistence.*;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

@Repository
@Transactional
public class UserDaoImpl<T> implements UserDao<T> {

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

        final String secretKey = "ssshhhhhhhhhhh!!!!";
        String encryptedString = AES.encrypt(user.getPass(), secretKey);
        user.setPass(encryptedString);
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

    public int checkUser(UserBean user) {
        System.out.println("uid: " + user.getId());
        String QUERY1 = " from UserRoles  where user_id=" + user.getId();
        System.out.println(QUERY1);
        List<UserRoles> role = (List<UserRoles>) hibernateTemplate.find(QUERY1);
        System.out.println("eolw: " + role.get(0).getRole());

        final String secretKey = "ssshhhhhhhhhhh!!!!";
        String encryptedString = AES.encrypt(user.getPass(), secretKey);
        System.out.println(encryptedString);
        System.out.println(user.getEmail());

        int usertype = 0;
        String QUERY = " from UserBean as o where o.email=?0 and o.pass=?1";
        List list = hibernateTemplate.getSessionFactory().openSession().createQuery(QUERY)
                .setParameter(0, user.getEmail())
                .setParameter(1, encryptedString).list();
        // Query query = session.createQuery(SQL_QUERY);
        // List list = (List) hibernateTemplate.find(SQL_QUERY, email, pass);

        if ((list != null) && (list.size() > 0) && role.get(0).getRole() == 1) {
            usertype = 1;
        } else if ((list != null) && (list.size() > 0) && role.get(0).getRole() == 2) {
            usertype = 2;

        }
        return usertype;
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
        System.out.println("pass: " + userDetails.get(0).getPass());
        final String secretKey = "ssshhhhhhhhhhh!!!!";
        String decryptedPass = AES.decrypt(userDetails.get(0).getPass(), secretKey);
        userDetails.get(0).setPass(decryptedPass);
        System.out.println("decc: " + userDetails.get(0).getPass());
        return userDetails.get(0);
    }

    public void updateuser(UserBean user) {
        hibernateTemplate.merge(user);
    }

    public boolean checkForgotpassDetails(String dob, String securityAns) {
        boolean isValid = false;
        String QUERY = " from UserBean as o where o.dob=?0 and o.securityAns=?1";
        List list = hibernateTemplate.getSessionFactory().openSession().createQuery(QUERY).setParameter(0, dob)
                .setParameter(1, securityAns).list();

        if ((list != null) && (list.size() > 0)) {
            isValid = true;
        }
        return isValid;
    }

    @Transactional
    public void changePass(ForgotPassBean forgotPass) {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        final String secretKey = "ssshhhhhhhhhhh!!!!";
        String encryptedString = AES.encrypt(forgotPass.getNewPass(), secretKey);

        Query q = session.createQuery("update UserBean set pass=:newPass where dob=:dob and securityAns=:securityAns")
                .setParameter("newPass", encryptedString)
                .setParameter("dob", forgotPass.getDob())
                .setParameter("securityAns", forgotPass.getSecurityAns());

        q.executeUpdate();
        tx.commit();
    }

    public ArrayList<UserBean> getRecentUsersList() {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        ArrayList<UserBean> userDetails = new ArrayList<>();

        // Get All Employees
        Transaction tx = session.beginTransaction();
        SQLQuery query = session
                .createSQLQuery("select * from users where CreatedTime  >= NOW() - INTERVAL 1 DAY");
        List<Object[]> rows = query.list();
        int i = 0;
        for (Object[] row : rows) {
            userDetails.get(i).setId(Integer.parseInt(row[0].toString()));
            userDetails.get(i).setEmail(row[1].toString());
            userDetails.get(i).setFirstName(row[2].toString());
            System.out.println(userDetails);
        }
        tx.commit();
        return userDetails;
    }

    public boolean checkEmail(String email) {
        boolean emailExists = false;
        String QUERY = " from UserBean as o where o.email=?0";
        List list = hibernateTemplate.getSessionFactory().openSession().createQuery(QUERY)
                .setParameter(0, email).list();
        // Query query = session.createQuery(SQL_QUERY);
        // List list = (List) hibernateTemplate.find(SQL_QUERY, email, pass);

        if ((list != null) && (list.size() > 0)) {
            emailExists = true;
        }
        return emailExists;
    }

}
