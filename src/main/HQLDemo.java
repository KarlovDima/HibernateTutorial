package main;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HQLDemo {
    public static void main(String[] args) {
        try(SessionFactory sessionFactory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory()){
            Session session=sessionFactory.getCurrentSession();
            session.beginTransaction();

            List<Student> students=session.createQuery("from Student").getResultList();
            for(Student student:students)
                System.out.println(student);

            students=session.createQuery("from Student s  where s.lastName='Aslan'").getResultList();
            for(Student student:students)
                System.out.println(student);

            students=session.createQuery("from Student s where s.email like '%box%'").getResultList();
            for(Student student:students)
                System.out.println(student);

            session.getTransaction().commit();
        }
    }

}
