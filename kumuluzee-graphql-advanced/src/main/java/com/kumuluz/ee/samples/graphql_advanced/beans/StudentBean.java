package com.kumuluz.ee.samples.graphql_advanced.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import com.kumuluz.ee.samples.graphql_advanced.entities.Student;
import com.kumuluz.ee.samples.graphql_advanced.entities.connections.StudentConnection;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.time.LocalDate;

@RequestScoped
public class StudentBean {

    @PersistenceContext
    private EntityManager em;

    public StudentConnection getStudentConnection(QueryParameters qp) {

        return new StudentConnection(JPAUtils.queryEntities(em, Student.class, qp),
                JPAUtils.queryEntitiesCount(em, Student.class, qp));
    }

    public Student getStudentById(Integer id) {

        return em.find(Student.class, id);
    }

    @Transactional
    public Student createStudent(Student s) {
        s.setEnrolled(LocalDate.now());
        em.persist(s);

        return s;
    }
}
