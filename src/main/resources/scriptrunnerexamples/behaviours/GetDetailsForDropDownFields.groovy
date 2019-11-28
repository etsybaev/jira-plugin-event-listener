//package com.aval.jira.groovyscripts
//
//import com.atlassian.jira.component.ComponentAccessor
//import com.aval.jira.plugins.api.MyPluginComponent
//import com.aval.jira.plugins.listener.CustomerDAO
//import com.onresolve.jira.groovy.user.FieldBehaviours
//import com.onresolve.scriptrunner.runner.ScriptRunnerImpl
//import com.onresolve.scriptrunner.runner.customisers.WithPlugin
//import com.atlassian.jira.issue.fields.config.FieldConfig;
//import com.atlassian.jira.issue.fields.config.FieldConfigScheme;
//import com.atlassian.jira.issue.CustomFieldManager;
//import com.atlassian.jira.issue.customfields.manager.OptionsManager;
//import com.atlassian.jira.issue.fields.CustomField;
//import com.atlassian.jira.issue.fields.config.FieldConfigScheme;
//import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
//import org.apache.commons.collections.MultiMap;
//import com.atlassian.jira.issue.customfields.option.Option;
//import com.atlassian.jira.issue.customfields.option.Options;
//
//@WithPlugin("com.aval.jira.plugins.new-listener-plugin")
//
//
//class TextCFN extends FieldBehaviours {
//
//    public run() {
//        MyPluginComponent myPluginComponent = ScriptRunnerImpl.getPluginComponent(MyPluginComponent)
//        CustomerDAO dao = myPluginComponent.getCustomerDAO()
//
//        CustomFieldManager customFldMgr = ComponentAccessor.getCustomFieldManager();
//        OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);
//
//        CustomField customField = customFldMgr.getCustomFieldObject(10465);
//
//        List<FieldConfigScheme> schemes = customField.getConfigurationSchemes();
//        FieldConfigScheme sc = schemes.get(0);
//        MultiMap configs = sc.getConfigsByConfig();
//
//
//        if(configs != null && !configs.isEmpty()){
//            FieldConfig config = (FieldConfig) configs.keySet().iterator().next();
//            Options ops = optionsManager.getOptions(config);
//
//            log.error(ops.getRootOptions().get(1))
//            log.error(ops.getRootOptions().get(1).getOptionId())
//        }
//    }
//}
