package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.domain.account.AbstractAccount;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.persistence.dao.AccountDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;

import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaAccountDao extends GenericJpaDao<AbstractAccount> implements AccountDao<AbstractAccount> {

    public JpaAccountDao(JpaSessionManager sessionManager) {
        super(sessionManager, AbstractAccount.class);
    }
}
