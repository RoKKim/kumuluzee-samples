package com.kumuluz.ee.samples.graphql_advanced.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import com.kumuluz.ee.samples.graphql_advanced.entities.Assistant;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@RequestScoped
public class AssistantBean {

    @PersistenceContext
    private EntityManager em;

    public List<Assistant> getAssistants(QueryParameters qp) {

        return JPAUtils.queryEntities(em, Assistant.class, qp);
    }
}
