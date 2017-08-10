package main;

import entity.Student;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class NativeSQLDemo {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        } catch (Throwable exc) {
            exc.printStackTrace();
        }

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            String sql = "SELECT * FROM student WHERE id=:student_id";
            NativeQuery query = session.createNativeQuery(sql).addEntity(Student.class).setParameter("student_id",2);
            List<Student> data = query.getResultList();
            for (Student student : data)
                System.out.println(student);

            transaction.commit();
        } catch (HibernateException exc) {
            if (transaction != null) transaction.rollback();
            exc.printStackTrace();
        } finally {
            session.close();
        }

    }
}
