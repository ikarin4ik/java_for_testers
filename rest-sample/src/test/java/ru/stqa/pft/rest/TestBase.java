package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;

import java.util.Set;


public class TestBase {

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("28accbe43ea112d9feb328d2c00b3eed", "");
    }


    public boolean isIssueOpen(int issueId) {
        String json = RestAssured.get("http://demo.bugify.com/api/issues/" + issueId + ".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issueSet = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
        Issue issue = issueSet.stream().findFirst().orElse(null);
        return 0 == issue.getState();
    }


    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
