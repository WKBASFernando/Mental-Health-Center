package com.ijse.orm.mentalhealthcenter.dao.custom.impl;

import com.ijse.orm.mentalhealthcenter.bo.exeption.DuplicateException;
import com.ijse.orm.mentalhealthcenter.bo.exeption.NotFoundException;
import com.ijse.orm.mentalhealthcenter.config.FactoryConfiguration;
import com.ijse.orm.mentalhealthcenter.dao.custom.TherapyProgramDAO;
import com.ijse.orm.mentalhealthcenter.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TherapyProgramDAOImpl implements TherapyProgramDAO {
    @Override
    public boolean save(TherapyProgram therapyProgram, Session session) throws SQLException {
        try{
            session.persist(therapyProgram);
            return true;
        }catch (DuplicateException e){
            throw new DuplicateException("Therapy already exists in therapistDAOImpl" + e.getMessage());
        }catch (Exception e){
            throw new SQLException("Therapy save failed in programDAOImpl" + e.getMessage());
        }
    }

    @Override
    public boolean update(TherapyProgram therapyProgram, Session session) throws SQLException, ClassNotFoundException {
        try {
            session.merge(therapyProgram);
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TherapyProgram> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            Query<TherapyProgram> query = session.createQuery("from TherapyProgram ", TherapyProgram.class);
            return query.list();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }finally{
            session.close();
        }
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        Session session =FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            TherapyProgram tPrograms = session.get(TherapyProgram.class, pk);
            if (tPrograms == null) {
                throw new NotFoundException("Program not found");
            }
            session.remove(tPrograms);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<TherapyProgram> findByPK(String pk, Session session) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            String lastPk = session
                    .createQuery("SELECT t.id FROM TherapyProgram t ORDER BY t.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
            return Optional.ofNullable(lastPk);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }
}
