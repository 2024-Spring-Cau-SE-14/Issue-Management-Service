package com.se14.repository.db_impl;

import com.se14.domain.Issue;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        DBInitializer.DatabaseObjects Databases = DBInitializer.initializeDatabase();

        IssueDB issueDB = Databases.getIssueDB();

        Optional<Issue> issue = issueDB.findById(123);


        System.out.println("successful");
    }
}
