package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.dao.CustomerDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;

import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class JpaCustomerDao extends GenericJpaDao<Customer> implements CustomerDao<Customer> {

    public JpaCustomerDao(JpaSessionManager sessionManager) {
        super(sessionManager, Customer.class);
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
