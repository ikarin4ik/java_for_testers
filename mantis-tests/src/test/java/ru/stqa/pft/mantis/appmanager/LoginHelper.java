package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

    public LoginHelper (ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        wd.findElement(By.name("username")).sendKeys(username);
        wd.findElement(By.name("password")).sendKeys(password);
        wd.findElement(By.cssSelector("input[value='Войти']")).click();
    }

}
