package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.util.List;

public class UserDaoHibernateImpl  implements UserDao {

    public UserDaoHibernateImpl() {
    }
    Util util = Util.getInstance();
    SessionFactory factory = util.getSessionFactory();



    @Override
    public void createUsersTable() {
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS user(" +
                    "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "lastname VARCHAR(255)NOT NULL," +
                    "age TINYINT NOT NULL)";
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    @Override
    public void dropUsersTable() {
            try {
                Session session = factory.getCurrentSession();
                session.beginTransaction();
                String sql = "DROP TABLE IF EXISTS user";;
                session.createNativeQuery(sql).executeUpdate();
                session.getTransaction().commit();
            } finally {
                factory.close();
            }
        }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }



    @Override
    public void removeUserById(long id) {
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } finally {
            factory.close();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
