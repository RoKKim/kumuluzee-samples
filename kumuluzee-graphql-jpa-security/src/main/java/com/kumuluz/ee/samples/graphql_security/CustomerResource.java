/*
 *  Copyright (c) 2014-2017 Kumuluz and/or its affiliates
 *  and other contributors as indicated by the @author tags and
 *  the contributor list.
 *
 *  Licensed under the MIT License (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://opensource.org/licenses/MIT
 *
 *  The software is provided "AS IS", WITHOUT WARRANTY OF ANY KIND, express or
 *  implied, including but not limited to the warranties of merchantability,
 *  fitness for a particular purpose and noninfringement. in no event shall the
 *  authors or copyright holders be liable for any claim, damages or other
 *  liability, whether in an action of contract, tort or otherwise, arising from,
 *  out of or in connection with the software or the use or other dealings in the
 *  software. See the License for the specific language governing permissions and
 *  limitations under the License.
*/
package com.kumuluz.ee.samples.graphql_security;

import com.kumuluz.ee.security.annotations.Secure;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;

/**
 * @author Benjamin Kastelic
 * @since 2.3.0
 */
@RequestScoped
@GraphQLApi
@Secure
public class CustomerResource {

    @Inject
    private CustomerService customerBean;

    @Query
    @PermitAll
    public List<Customer> getAllCustomers() {
       return customerBean.getCustomers();
    }

    @Query
    @RolesAllowed({"user", "admin"})
    public Customer getCustomer(@Name("customerId") String customerId) {
        return customerBean.getCustomer(customerId);
    }

    @Mutation
    @RolesAllowed("admin")
    public Customer addNewCustomer(@Name("customer") Customer customer) {
        customerBean.saveCustomer(customer);
        return customer;
    }

    @Mutation
    @DenyAll
    public Customer deleteCustomer(@Name("customerId") String customerId) {
        return customerBean.deleteCustomer(customerId);
    }
}
