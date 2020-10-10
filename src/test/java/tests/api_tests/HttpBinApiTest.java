package tests.api_tests;

import io.qameta.allure.Epic;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.lessThan;

@Epic("httpbin")
public class HttpBinApiTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://httpbin.org";
    }

    @Test
    public void whenValidateGettingImage_thenSuccessTest() {
        when()
                .request("GET", "/image/png")
                .then()
                .statusCode(200);
    }

    @Test
    public void whenValidateResponseTimeInSeconds_thenSuccessTest() {
        when()
                .request("POST", "/delay/5")
                .then()
                .time(lessThan(10L), TimeUnit.SECONDS);
    }
}
