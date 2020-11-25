package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.persistence.dao.AccountDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;

public class JpaAccountDao extends GenericJpaDao<AbstractAccount> implements AccountDao<AbstractAccount> {

    public JpaAccountDao() {
        super(AbstractAccount.class);
    }
}
