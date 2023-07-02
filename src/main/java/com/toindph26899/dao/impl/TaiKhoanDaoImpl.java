package com.toindph26899.dao.impl;

import com.toindph26899.dao.TaiKhoanDao;
import com.toindph26899.entity.KhachHang;
import com.toindph26899.entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaiKhoanDaoImpl implements TaiKhoanDao {

    private EntityManager entityManager;

    public TaiKhoanDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public TaiKhoan findByUser(String uname) {

        TypedQuery<TaiKhoan> userTypedQuery = entityManager.createQuery(
                "from TaiKhoan tk " +
                        "where tk.username =:uname", TaiKhoan.class
        );
        userTypedQuery.setParameter("uname", uname);

        TaiKhoan taiKhoan = null;

        try {
            taiKhoan = userTypedQuery.getSingleResult();
        } catch (Exception e) {
            taiKhoan = null;
        }

        return taiKhoan;
    }

    @Override
    public KhachHang findByCustomer(String uname) {
        return null;
    }
}
