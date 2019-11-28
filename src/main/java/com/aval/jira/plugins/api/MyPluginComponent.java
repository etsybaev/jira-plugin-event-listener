package com.aval.jira.plugins.api;

import com.aval.jira.plugins.listener.CustomerDAO;

public interface MyPluginComponent
{
    String getName();
    String getSomeDebugHelloText();
    CustomerDAO getCustomerDAO();
}