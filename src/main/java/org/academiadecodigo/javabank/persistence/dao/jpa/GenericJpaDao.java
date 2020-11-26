package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.persistence.dao.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public abstract class GenericJpaDao<T> implements Dao<T> {

    private final Class<T> type;

    @PersistenceContext
    private EntityManager session;

    public GenericJpaDao(Class<T> type) {
        this.type = type;
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.select(root);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public T findById(Integer id) {
        return Optional.ofNullable(session.find(type, id))
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public T saveOrUpdate(T data) {
        return session.merge(data);
    }

    @Override
    public void delete(Integer id) {
        T account = findById(id);

        session.remove(session.merge(account));
    }
}
