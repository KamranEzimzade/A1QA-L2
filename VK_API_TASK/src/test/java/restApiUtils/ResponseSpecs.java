package restApiUtils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

public class ResponseSpecs {

    public static final ResponseSpecification OK = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public static final ResponseSpecification NOT_FOUND = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_NOT_FOUND)
            .expectBody(Matchers.is("{}"))
            .build();

    public static final ResponseSpecification CREATED = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_CREATED)
            .build();
}
