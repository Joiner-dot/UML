package com.KozKuzCompany;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            UI.engage();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
