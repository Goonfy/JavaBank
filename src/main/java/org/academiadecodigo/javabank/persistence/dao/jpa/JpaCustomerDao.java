package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.persistence.dao.AccountDao;
import org.academiadecodigo.javabank.persistence.dao.CustomerDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;

import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaCustomerDao extends GenericJpaDao<Customer> implements CustomerDao<Customer> {

    public JpaCustomerDao(JpaSessionManager sessionManager) {
        super(sessionManager);
    }

    @Override
    public List<Customer> findAll() {
        CriteriaBuilder builder = getSessionManager().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);
        criteriaQuery.select(root);

        return getSessionManager().getCurrentSession().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Customer findById(Integer id) {
        CriteriaBuilder builder = getSessionManager().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                builder.equal(root.get("id"), id)
        );

        return getSessionManager().getCurrentSession().createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        return getSessionManager().getCurrentSession().merge(customer);
    }

    @Override
    public void delete(Integer id) {
        getSessionManager().getCurrentSession().remove(getSessionManager().getCurrentSession().merge(findById(id)));
    }

    @Override
    public Customer findByUsername(String username) {
        CriteriaBuilder builder = getSessionManager().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                builder.equal(root.get("name"), username)
        );

        return getSessionManager().getCurrentSession().createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public Customer findByEmail(String email) {
        CriteriaBuilder builder = getSessionManager().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                builder.equal(root.get("email"), email)
        );

        return getSessionManager().getCurrentSession().createQuery(criteriaQuery).getSingleResult();
    }
}
