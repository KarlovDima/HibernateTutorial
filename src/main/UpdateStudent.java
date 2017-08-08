package main;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudent {

    public static void main(String[] args) {
        try(SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory()){
            Session session=sessionFactory.getCurrentSession();
            session.beginTransaction();

            //Update one student

            Student student = session.get(Student.class, 1);
            student.setLastName("NePisa");

            //Update all students

            session.createQuery("update Student set email='luv3code@gmail.com' where id=1").executeUpdate();

            session.getTransaction().commit();
        }
    }
}
