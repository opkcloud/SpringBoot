package com.opkcloud.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@Component
public class TransactionUtil {
    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    public TransactionStatus beginTransaction() {
        return platformTransactionManager.getTransaction(transactionDefinition);
    }

    private void commit(TransactionStatus transactionStatus) {
        if (transactionStatus != null) {
            platformTransactionManager.commit(transactionStatus);
        }
    }

    private void rollback(TransactionStatus transactionStatus) {
        if (transactionStatus != null) {
            platformTransactionManager.rollback(transactionStatus);
        }
    }

    /**
     * 开启一个事务
     *
     * @param func 执行具体业务逻辑，返回 true 则提交事务，返回 false 则回滚事务
     *             业务逻辑内部出现异常也会回滚事务
     * @return 表示事务执行成功或失败
     */
    public boolean start(Supplier<Boolean> func) {
        TransactionStatus transactionStatus = null;
        try {
            transactionStatus = this.beginTransaction();
            Boolean result = func.get();
            if (result) {
                this.commit(transactionStatus);
                return true;
            }

            this.rollback(transactionStatus);
            return false;
        } catch (Exception ex) {

            if (transactionStatus != null) {
                rollback(transactionStatus);
            }
            log.error("start transaction from TransactionUtil take error.", ex);
            throw ex;
        }
    }

    public <R> R start(Function<Transaction, R> func) {
        TransactionStatus transactionStatus = null;
        try {
            transactionStatus = this.beginTransaction();
            return func.apply(new Transaction(transactionStatus));
        } catch (Exception ex) {
            if (transactionStatus != null) {
                rollback(transactionStatus);
            }
            log.error("start transaction from TransactionUtil take error.", ex);
            throw ex;
        }
    }

    public class Transaction{
        private final TransactionStatus trxtatus;
        public Transaction(TransactionStatus transactionStatus){
            trxtatus = transactionStatus;
        }
        public void commit(){
            if (trxtatus != null) {
                platformTransactionManager.commit(trxtatus);
            }
        }
        public void rollback() {
            if (trxtatus != null) {
                platformTransactionManager.rollback(trxtatus);
            }
        }
    }
}
