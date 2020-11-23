package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.persistence.dao.AccountDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;

import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaAccountDao extends GenericJpaDao<Account> implements AccountDao<Account> {

    public JpaAccountDao(JpaSessionManager sessionManager) {
        super(sessionManager);
    }

    @Override
    public List<Account> findAll() {
        CriteriaBuilder builder = getSessionManager().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = builder.createQuery(Account.class);
        Root<Account> root = criteriaQuery.from(Account.class);
        criteriaQuery.select(root);

        return getSessionManager().getCurrentSession().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Account findById(Integer id) {
        CriteriaBuilder builder = getSessionManager().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = builder.createQuery(Account.class);
        Root<Account> root = criteriaQuery.from(Account.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                builder.equal(root.get("id"), id)
        );

        return getSessionManager().getCurrentSession().createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public Account saveOrUpdate(Account account) {
        return getSessionManager().getCurrentSession().merge(account);
    }

    @Override
    public void delete(Integer id) {
        Account account = findById(id);

        getSessionManager().getCurrentSession().remove(getSessionManager().getCurrentSession().merge(account));
    }
}
