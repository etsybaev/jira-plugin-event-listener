package com.aval.jira.plugins.impl;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.ApplicationProperties;
import com.aval.jira.plugins.api.MyPluginComponent;
import com.aval.jira.plugins.listener.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.atlassian.jira.config.util.JiraHome;

import javax.inject.Inject;
import javax.inject.Named;

@Component
@ExportAsService({MyPluginComponent.class})
@Named ("myPluginComponent")
public class MyPluginComponentImpl implements MyPluginComponent
{
    @ComponentImport
    private final ApplicationProperties applicationProperties;
    @ComponentImport
    private JiraHome jiraHome; //this may be used as workaround to save scripts to jirajome'scripts folder and then us it in script runner behaviours

    @Autowired
    private CustomerDAO customerDAO;

    @Inject
    public MyPluginComponentImpl(final ApplicationProperties applicationProperties, JiraHome jiraHome)
    {
        this.applicationProperties = applicationProperties;
        this.jiraHome = jiraHome;
    }

    public String getName()
    {
        if(null != applicationProperties)
        {
            return "myComponent:" + applicationProperties.getDisplayName();
        }
        
        return "myComponent";
    }

    @Override
    public String getSomeDebugHelloText() {
        return "Hi from getSomeDebugHelloText";
    }


    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public JiraHome getJiraHome(){return jiraHome;}
}