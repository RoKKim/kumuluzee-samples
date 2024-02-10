package com.kumuluz.ee.samples.graphql_security;

import com.kumuluz.ee.graphql.GraphQLApplication;
import com.kumuluz.ee.graphql.annotations.GraphQLApplicationClass;

import jakarta.annotation.security.DeclareRoles;

@GraphQLApplicationClass
@DeclareRoles({"user", "admin"})
public class CustomerApp extends GraphQLApplication {
}
