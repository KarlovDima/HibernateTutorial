package main;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudent {

    public static void main(String[] args) {
        try(SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory()){
            Session session=sessionFactory.getCurrentSession();
            session.beginTransaction();

            Student student = session.get(Student.class, 1);
            System.out.println(student);


            session.getTransaction().commit();
        }
    }
}