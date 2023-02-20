package specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.httpclient.HttpStatus;
import org.hamcrest.Matchers;

public class ResponseSpecifications {

    public static final ResponseSpecification notFoundResponseSpec = new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_NOT_FOUND).expectBody(Matchers.empty()).build();

    public static final ResponseSpecification foundResponseSpec = new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_OK).expectContentType(ContentType.JSON).build();

    public static final ResponseSpecification createdResponseSpec = new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_CREATED).build();


}
