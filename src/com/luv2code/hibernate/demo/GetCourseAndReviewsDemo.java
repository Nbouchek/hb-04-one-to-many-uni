package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCourseAndReviewsDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {

            // start a transaction
            System.out.println("Starting transaction ... ");
            session.beginTransaction();

            // get the course
            int theId = 10;
            Course tempCourse = session.get(Course.class, theId);
            System.out.println("\n\n");
            // print the course
            System.out.println(tempCourse);

            // print the course reviews
            System.out.println(tempCourse.getReviews());
            System.out.println("\n\n");

            // commit transaction
            System.out.println("Committing transaction ... ");
            session.getTransaction().commit();
            System.out.println("Done :)");
        } finally {
            // add clean up code
            session.close();
            factory.close();
        }
    }
}
