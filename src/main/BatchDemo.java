package main;

import entity.Student;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class BatchDemo {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        } catch (Throwable exc) {
            exc.printStackTrace();
        }

        BatchDemo batchDemo = new BatchDemo();
        batchDemo.addStudents();
        batchDemo.deleteStdents();

    }

    public void addStudents() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        for (int i = 0; i < 100000; i++) {
            Student student = new Student("name" + i, "lastname" + i, "email" + i);
            session.save(student);
            if (i % 50 == 0) {
                session.flush();
                session.clear();
            }
        }
        transaction.commit();
        session.close();
    }

    public void deleteStdents() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        ScrollableResults studentCursor = session.createQuery("from Student").scroll();
        int count = 0;

        while (studentCursor.next()) {
            Student student = (Student) studentCursor.get(0);
            session.update(student);
            if (++count % 50 == 0) {
                session.flush();
                session.clear();
            }
        }
        tx.commit();
        session.close();
    }
}
