package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.User;

import java.util.ArrayList;
import java.util.List;

public class ResetPassHelper extends HelperBase {

    public ResetPassHelper(ApplicationManager app) {
        super(app);
    }

    public void resetPassword(String userId) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        selectUserById(userId);
        wd.findElement(By.cssSelector("input[value='Сбросить пароль']")).click();
    }

    public void selectUserById(String userId) {
        wd.findElement(By.xpath("//a[@href='manage_user_edit_page.php?user_id=" + userId + "']")).click();
    }

    public void finish(String passwordResetLink, String password) {
        wd.get(passwordResetLink);
        wd.findElement(By.name("password")).sendKeys(password);
        wd.findElement(By.name("password_confirm")).sendKeys(password);
        wd.findElement(By.cssSelector("button[type='submit']")).click();
    }

    public List<User> users() {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        List<WebElement> elements = wd.findElements(By.cssSelector(".table tbody tr"));
        List<User> users = new ArrayList<>();
        for (WebElement element : elements) {
            String id = element.findElement(By.cssSelector("td:nth-child(1) a")).getAttribute("href");
            id = id.substring(id.lastIndexOf("=") + 1);
            String login = element.findElement(By.cssSelector("td:nth-child(1) a")).getText();
            String email = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            users.add(new User(id, email, login));
        }
        return users;
    }
}
