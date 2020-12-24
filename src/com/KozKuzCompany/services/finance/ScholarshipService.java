package com.KozKuzCompany.services.finance;

import com.KozKuzCompany.entities.Document;
import com.KozKuzCompany.services.finance.FinanceManagementService;

public class ScholarshipService extends FinanceManagementService {

    public static boolean hasIncreasedScholarship(int studentId) {

        return false;
    }

    public static double assignDefaultScholarship(int studentId) {

        return 0;
    }

    public static double assignIncreasedScholarship(int studentId) {

        return 0;
    }

    public static void transferScholarship(Document document) {

    }

    public static boolean hasScholarship(Document document) {

        return false;
    }
}
