package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

public class YandexDiskTest {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String PARAM_LIMIT = "limit";
    private static final String PARAM_FIELDS = "fields";

    @Test
    public void listAcceptedDirsAndFilesTest() {
        Response response = RestAssured.given()
                .header(HEADER_AUTHORIZATION, String.format("OAuth %s", ConfProperties.getProperty("OAuth_token")))
                .param(PARAM_LIMIT, 1000)
                .param(PARAM_FIELDS, "name,type")
                .when().get("https://cloud-api.yandex.net/v1/disk/resources/files");

        response.then().assertThat().statusCode(200);

        System.out.println("Доступные папки и файлы на диске:");
        System.out.println(response.getBody().print());
    }

}
