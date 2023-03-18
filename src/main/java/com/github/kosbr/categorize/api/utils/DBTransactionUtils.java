package com.github.kosbr.categorize.api.utils;

import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class DBTransactionUtils {

    /**
     * Launches the code after current transaction is completed.
     * Why do we need it?
     * See usages.
     * @param runnable
     */
    public static void executeAfterTransactionCompleted(Runnable runnable) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    runnable.run();
                }
            });
        } else {
            runnable.run();
        }
    }
}
