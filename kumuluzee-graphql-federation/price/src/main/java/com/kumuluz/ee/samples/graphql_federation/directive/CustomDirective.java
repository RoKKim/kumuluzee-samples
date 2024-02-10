package com.kumuluz.ee.samples.graphql_federation.directive;

import io.smallrye.graphql.api.Directive;
import org.eclipse.microprofile.graphql.Name;

import static io.smallrye.graphql.api.DirectiveLocation.OBJECT;

@Directive(on = {OBJECT})
@Name("custom")
public @interface CustomDirective {
}
