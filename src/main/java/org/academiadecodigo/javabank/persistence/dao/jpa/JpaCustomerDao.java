package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.dao.CustomerDao;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class JpaCustomerDao extends GenericJpaDao<Customer> implements CustomerDao<Customer> {

    @PersistenceContext
    private EntityManager session;

    public JpaCustomerDao() {
        super(Customer.class);
    }

    @Override
    public Customer findByUsername(String username) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                builder.equal(root.get("name"), username)
        );

        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public Customer findByEmail(String email) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                builder.equal(root.get("email"), email)
        );

        return session.createQuery(criteriaQuery).getSingleResult();
    }
}
