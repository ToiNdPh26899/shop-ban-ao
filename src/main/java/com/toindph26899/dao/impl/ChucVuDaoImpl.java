package com.toindph26899.dao.impl;

import com.toindph26899.dao.ChucVuDao;
import com.toindph26899.entity.ChucVu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ChucVuDaoImpl implements ChucVuDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<ChucVu> findAllWithUname(String uname) {
        return null;
    }

    @Override
    public ChucVu findRoleByName(String roleEmployee) {

        TypedQuery<ChucVu> chucVuTypedQuery = entityManager.createQuery(
                "from ChucVu cv where cv.role =:roleName", ChucVu.class
        ).setParameter("roleName", roleEmployee);

        ChucVu chucVu = null;

        try {
            chucVu = chucVuTypedQuery.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return chucVu;
    }
}
