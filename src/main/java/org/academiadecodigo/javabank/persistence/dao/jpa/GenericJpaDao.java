package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.persistence.dao.Dao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public abstract class GenericJpaDao<T> implements Dao<T> {

    private final JpaSessionManager sessionManager;
    private final Class<T> type;

    public GenericJpaDao(JpaSessionManager sessionManager, Class<T> type) {
        this.type = type;
        this.sessionManager = sessionManager;
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder builder = getSessionManager().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.select(root);

        return getSessionManager().getCurrentSession().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public T findById(Integer id) {
        return Optional.ofNullable(getSessionManager().getCurrentSession().find(type, id))
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public T saveOrUpdate(T data) {
        return getSessionManager().getCurrentSession().merge(data);
    }

    @Override
    public void delete(Integer id) {
        T account = findById(id);

        getSessionManager().getCurrentSession().remove(getSessionManager().getCurrentSession().merge(account));
    }

    public JpaSessionManager getSessionManager() {
        return sessionManager;
    }
}
