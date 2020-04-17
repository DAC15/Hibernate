package dao;

import entities.Discipline;
import entities.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import java.util.List;






public class DaoImplementation implements AllFromOrToDatabase {

    private static SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    //        ⦁	List all users with a specific role name.
    public List<User> getUserByRoleName(/*String nameOfRole*/){
        Session s = null;
        Transaction t = null;
        List<User> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("from User u join fetch u.roles r where r.nameOfRole = 'Engineer' ");
            //query.setString("nameOfRole", String.valueOf(nameOfRole));
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }

// ⦁	List all users from a discipline (let’s say Applications Management).
    public List<User> getUserByDiscipline(String discipline){
        Session s = null;
        Transaction t = null;
        List<User> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("From User u Join fetch u.discipline d Where d.name =: discipline" );
            query.setString("discipline", String.valueOf(discipline));
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }
    //        ⦁	List all users that have tasks in TODO status.
    public List<User> getUsersWithTaskInTodoStatus(){
        Session s = null;
        Transaction t = null;
        List<User> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("select t.user from Task t Where t.status = 'TODO' ");
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }

    // Change the head of discipline for dev_discipline
    public void changeTheHeadOfDiscipline(User user, Discipline discipline){
        Session s = null;
        Transaction t = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            discipline.setHeadOfDiscipline(user);
            s.update(discipline);
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }

    }



    public void deleteUser(User user){
        Session s = null;
        Transaction t = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            s.delete(user);
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
    }


}


