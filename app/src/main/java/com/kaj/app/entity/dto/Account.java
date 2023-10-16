package com.kaj.app.entity.dto;

import java.util.HashMap;
import java.util.Map;

public class Account {
    private Map<String, String> accounts;

    public Account() {
        accounts = new HashMap<>();
    }

    public void add(String username, String password) {
        accounts.put(username, password);
    }

    public Map<String, String> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, String> accounts) {
        this.accounts = accounts;
    }
}
