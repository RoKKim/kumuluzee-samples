# KumuluzEE JAX-RS REST service sample

> Develop a REST service using standard JAX-RS 2 API and pack it as a KumuluzEE microservice

The objective of this sample is to show how to develop a REST service using standard JAX-RS 2 API and pack it as a KumuluzEE microservice. The tutorial will guide you through the necessary steps. You will add KumuluzEE dependencies into pom.xml. To develop the rEST service, you will use the standard JAX-RS 2 API. 
Required knowledge: basic familiarity with JAX-RS 2 and basic concepts of REST and JSON.
## Requirements

In order to run this example you will need the following:

1. Java 8 (or newer), you can use any implementation:
    * If you have installed Java, you can check the version by typing the following in a command line:
        
        ```
        java -version
        ```

2. Maven 3.2.1 (or newer):
    * If you have installed Maven, you can check the version by typing the following in a command line:
        
        ```
        mvn -version
        ```
3. Git:
    * If you have installed Git, you can check the version by typing the following in a command line:
    
        ```
        git --version
        ```
    

## Prerequisites

This sample does not contain any prerequisites and can be started on its own.

## Usage

The example uses maven to build and run the microservice.

1. Build the sample using maven:

    ```bash
    $ cd jax-rs
    $ mvn clean package
    ```

2. Run the sample:

    ```bash
    $ java -cp target/classes:target/dependency/* com.kumuluz.ee.EeApplication
    ```

    in Windows environment use the command
    ```
    java -cp target/classes;target/dependency/* com.kumuluz.ee.EeApplication
    ```
    
The application/service can be accessed on the following URL:
* JAX-RS REST resource - http://localhost:8080/v1/customers

To shut down the example simply stop the processes in the foreground.

## Tutorial

This tutorial will guide you through the steps required to create a simple REST service using standard JAX-RS 2 API and pack it as a KumuluzEE microservice. 
We will develop a simple Customer REST service with the following resources:
* GET http://localhost:8080/v1/customers - list of all customers 
* GET http://localhost:8080/v1/customers/{customerId} – details of customer with ID {customerId}
* POST http://localhost:8080/v1/customers – add a customer
* DELETE http://localhost:8080/v1/customers/{customerId} – delete customer with ID {customerId}

We will follow these steps:
* Create a Maven project in the IDE of your choice (Eclipse, IntelliJ, etc.)
* Add Maven dependencies to KumuluzEE and include KumuluzEE components (Core, Servlet and JAX-RS)
* Implement the service using standard JAX-RS 2
* Build the microservice
* Run it
 

### Add Maven dependencies

Add the KumuluzEE BOM module dependency to your `pom.xml` file:
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.kumuluz.ee</groupId>
            <artifactId>kumuluzee-bom</artifactId>
            <version>${kumuluz.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

Add the `kumuluzee-core`, `kumuluzee-servlet-jetty` and `kumuluzee-jax-rs-jersey` dependencies:
```xml
<dependencies>
    <dependency>
        <groupId>com.kumuluz.ee</groupId>
         <artifactId>kumuluzee-core</artifactId>
    </dependency>
    <dependency>
        <groupId>com.kumuluz.ee</groupId>
         <artifactId>kumuluzee-servlet-jetty</artifactId>
    </dependency>
    <dependency>
         <groupId>com.kumuluz.ee</groupId>
         <artifactId>kumuluzee-jax-rs-jersey</artifactId>
    </dependency>
</dependencies>
```

Add the `maven-dependency-plugin` build plugin to copy all the necessary dependencies into target folder:

```xml
<build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <version>2.10</version>
                <executions>
                    <execution>
                        <id>copy-dependencies</id>
                        <phase>package</phase>
                        <goals>
                            <goal>copy-dependencies</goal>
                        </goals>
                        <configuration>
                            <includeScope>runtime</includeScope>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

### Implement the service

Register your module as JAX-RS service and define the application path. You could do that in web.xml or for example with `@ApplicationPath` annotation:

```java
@ApplicationPath("v1")
public class CustomerApplication extends Application {
}
```

Implement JAX-RS resource, for example, to implement resource `customers` which will return all customers by default on GET request:

```java
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("customers")
public class CustomerResource {

    @GET
    public Response getAllCustomers() {
        List<Customer> customers = Database.getCustomers();
        return Response.ok(customers).build();
    }

    //Additional resource methods and sub-resources
}
```

### Build the microservice and run it

To build the microservice and run the example, use the commands as described in previous sections.