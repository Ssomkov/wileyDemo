package tests.api_tests;

import io.qameta.allure.Epic;
import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Every.everyItem;

@Epic("Wiley")
public class WileyApiTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://www.wiley.com/en-us/search/autocomplete/comp_00001H9J";
    }

    @Test
    public void checkSuggestionsBodyTest() {
        when()
                .request("GET", "?term=Java")
                .then()
                .statusCode(200)
                .and()
                .body("suggestions.term.size()", is(4))
                .and()
                .body("suggestions.term", everyItem(startsWith("<span class=\"search-highlight\">java</span>")));
    }

    @Test
    public void checkTitleTest() {
        when()
                .request("GET", "?term=Java")
                .then()
                .statusCode(200)
                .and()
                .body("pages.title", CoreMatchers.hasItems(containsString("Wiley")));
    }
}
