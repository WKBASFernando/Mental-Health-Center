package com.ijse.orm.mentalhealthcenter.dao.custom.impl;

import com.ijse.orm.mentalhealthcenter.config.FactoryConfiguration;
import com.ijse.orm.mentalhealthcenter.dao.custom.AppointmentDAO;
import com.ijse.orm.mentalhealthcenter.entity.Appointment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AppointmentDAOImpl implements AppointmentDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public boolean save(Appointment appointment, Session session) throws SQLException {
        session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(appointment);
            session.flush();
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Failed to Save Appointment" + e.getMessage());
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(Appointment appointments, Session session) throws SQLException, ClassNotFoundException {
        try{
            session.merge(appointments);
            return true;
        }catch(Exception e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public List<Appointment> getAll() throws Exception {
        return List.of();
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<Appointment> findByPK(String pk, Session session) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = factoryConfiguration.getSession();

        String lastPk = session
                .createQuery("SELECT t.id FROM Appointment t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(lastPk);
    }
}
