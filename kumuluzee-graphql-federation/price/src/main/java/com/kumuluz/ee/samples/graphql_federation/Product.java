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

import com.kumuluz.ee.samples.graphql_federation.directive.CustomDirective;
import io.smallrye.graphql.api.federation.FieldSet;
import io.smallrye.graphql.api.federation.Key;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import org.eclipse.microprofile.graphql.Input;
import org.eclipse.microprofile.graphql.NonNull;

import java.io.Serializable;

/**
 * @author Rok Miklavčič
 * @since 5.0.0
 */
@Entity
@Table(name = "product")
@NamedQueries({
        @NamedQuery(
                name = "Product.findProducts",
                query = "SELECT p " +
                        "FROM Product p"
        )
})
@Key(fields = @FieldSet("id"))
@Input("ProductPriceInput")
@CustomDirective
public class Product implements Serializable {

    @jakarta.persistence.Id
    @org.eclipse.microprofile.graphql.Id
    @NonNull
    private String id;
    @Column
    private Float price;

    public Product() {
    }

    public Product(String id, Float price) {
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
