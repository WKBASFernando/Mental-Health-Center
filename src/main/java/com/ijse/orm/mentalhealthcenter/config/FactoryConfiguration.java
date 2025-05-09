package com.ijse.orm.mentalhealthcenter.config;

import com.ijse.orm.mentalhealthcenter.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;
    private FactoryConfiguration() {
        Configuration configuration = new Configuration().configure()
                .addAnnotatedClass(Patient.class)
                .addAnnotatedClass(Appointment.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(TherapySession.class)
                .addAnnotatedClass(Therapist.class)
                .addAnnotatedClass(TherapyProgram.class);
        sessionFactory = configuration.buildSessionFactory();

    }
    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
