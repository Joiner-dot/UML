package com.KozKuzCompany.services.finance;

import com.KozKuzCompany.entities.Document;
import com.KozKuzCompany.entities.Transaction;

import static com.KozKuzCompany.services.finance.BenefitsService.calculatePaymentBenefits;

public abstract class FinanceManagementService {

    public static Transaction confirmTransaction(Document document) {

        Transaction transaction = new Transaction(document.getId(),
                calculatePaymentBenefits(document.getStudentId()),
                document.getDescription() + " - completed",
                document.getTitle());
        transaction.setApproved(true);
        transaction.setConfirmed(true);

        return transaction;
    }
}
