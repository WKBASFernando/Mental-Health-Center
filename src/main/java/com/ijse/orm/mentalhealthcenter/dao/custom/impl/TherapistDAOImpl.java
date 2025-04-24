package com.ijse.orm.mentalhealthcenter.dao.custom.impl;

import com.ijse.orm.mentalhealthcenter.bo.exeption.NotFoundException;
import com.ijse.orm.mentalhealthcenter.config.FactoryConfiguration;
import com.ijse.orm.mentalhealthcenter.dao.custom.TherapistDAO;
import com.ijse.orm.mentalhealthcenter.entity.SuperEntity;
import com.ijse.orm.mentalhealthcenter.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TherapistDAOImpl implements TherapistDAO {

    @Override
    public boolean save(Therapist therapist, Session session) throws SQLException {
        try {
            session.persist(therapist);
            session.flush();
            return true;
        }catch (Exception e) {
            throw new RuntimeException("Therapist saving failed in therapistDAOImpl" + e.getMessage());
        }
    }

    @Override
    public boolean update(Therapist therapist, Session session) throws SQLException, ClassNotFoundException {
        try {
            session.merge(therapist);
            return true;
        }catch (Exception e){
            throw new RuntimeException("Therapist update failed"+e.getMessage());
        }
    }

    @Override
    public List<Therapist> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            Query<Therapist> query = session.createQuery("from Therapist ", Therapist.class);
            List<Therapist> therapists = query.list();
            return therapists;

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Therapist therapist = session.get(Therapist.class,pk);
            if(therapist == null){
                throw new NotFoundException("Therapist not found");
            }
            session.remove(therapist);
            transaction.commit();
            return true;
        } catch (NotFoundException e) {
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public Optional<Therapist> findByPK(String pk, Session session) throws SQLException {
        Therapist therapist = null;
        try {
            String sql = "SELECT * FROM therapist WHERE doctorID = :id";
            NativeQuery<Therapist> query = session.createNativeQuery(sql, Therapist.class);
            query.setParameter("id", pk);

            therapist = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(therapist);
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            String lastPk = session
                    .createQuery("SELECT t.id FROM Therapist t ORDER BY t.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
            return Optional.ofNullable(lastPk);
        }catch (Exception e) {
            throw new RuntimeException("Therapist lastPK not found"+e.getMessage());
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
