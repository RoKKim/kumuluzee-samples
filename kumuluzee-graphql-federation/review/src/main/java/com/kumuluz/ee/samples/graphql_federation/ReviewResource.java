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
package com.kumuluz.ee.samples.graphql_federation;

import io.smallrye.graphql.api.federation.Shareable;
import io.smallrye.graphql.api.federation.link.Import;
import io.smallrye.graphql.api.federation.link.Link;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import static io.smallrye.graphql.api.federation.link.Link.FEDERATION_SPEC_LATEST_URL;

/**
 * @author Rok Miklavčič
 * @since 5.0.0
 */
@RequestScoped
@GraphQLApi
@Link(url = FEDERATION_SPEC_LATEST_URL, _import = {
        @Import(name = "@key"),
        @Import(name = "@shareable"),
        @Import(name = "FieldSet")
})
public class ReviewResource {

    @Inject
    private ReviewService reviewBean;

    @Query
    @Shareable
    public Product getProduct(@Name("productId") String productId) {
        return reviewBean.getProduct(productId);
    }
}
