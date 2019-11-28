package com.aval.jira.plugins.rest;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.fields.config.FieldConfigScheme;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import org.apache.commons.collections.MultiMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * A resource of message.
 */
@Path("/message")
public class BaseRestController {

    @GET
//    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public Response getMessage()
    {
       return Response.ok(new BaseRestControllerModel("Hello World from rest controller")).build();
    }



    //TODO THIS ENDPOINT WAS NOT TESTSED AS IT IS, BUT THIS JUST POC THAT WE MAY UODATE OTIONS FOR DROP-DPWN FILEDS FROM JAVA CODE
    //TODO iT WAS TESTS AS SCRIPT AS PART OF https://jira.tracking.sits.avaloq.net/browse/SRM-260 TICKET
    /**
     * This method is to be used whenever a new option is to be added to a custom field. It will add it to the top of the list.
     * @param fieldId - custom field id, e.g,. customfield_10000
     * @param optionVal - option value, e.g,. 4.1r1.24.67643_70
     * @return
     */
    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/addOptionsToCustomField")
    public Response addOptionToCustomField(@QueryParam("fieldId") String fieldId, @QueryParam("optionVal") String optionVal)
    {
        CustomFieldManager customFldMgr = ComponentAccessor.getCustomFieldManager();
        OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);

        if (fieldId == null || fieldId.trim().equals("")) {
            return Response.serverError().build();
        }

        //error checking code snipped

        CustomField customField = customFldMgr.getCustomFieldObject(fieldId);
        //error checking code snipped

        List<FieldConfigScheme> schemes = customField.getConfigurationSchemes();
        if (schemes != null && !schemes.isEmpty()) {
        FieldConfigScheme sc = schemes.get(0);
        MultiMap configs = sc.getConfigsByConfig();
        if (configs != null && !configs.isEmpty()) {
            FieldConfig config = (FieldConfig) configs.keySet().iterator().next();
            Options ops = optionsManager.getOptions(config);
            if(ops != null && ops.getOptionForValue(optionVal, null) != null)
            return Response.ok().build();
            Option op = optionsManager.createOption(config, null,
                    new Long(1),
                    optionVal);
            ops = optionsManager.getOptions(config);
            ops.moveToStartSequence(op);
        }
    }
        return Response.ok("SUCCESS").build();
    }


}