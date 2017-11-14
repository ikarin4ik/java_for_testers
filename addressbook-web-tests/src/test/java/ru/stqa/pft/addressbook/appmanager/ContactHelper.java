package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void goToAddContactPage() {
        click(By.linkText("add new"));
    }

    public void returnToContactPage() {
        click(By.linkText("home page"));
    }

    public void submitNewContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobilephone());
        type(By.name("email"), contactData.getEmail());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactEdit(int index) {
        wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td[8]/a")).get(index).click();
    }

    public void updateContactForm() {
        click(By.cssSelector("div#content form input[name='update'][value='Update']:last-of-type"));
    }

    public void selectContact(int index) {
        wd.findElements(By.cssSelector("tbody tr td.center input")).get(index).click();

    }

    public void deleteSelectedContact() {
        click(By.cssSelector("div.left input[type='button'][value='Delete']"));
        acceptAlert();
    }

    public void createContact(ContactData contact) {
        goToAddContactPage();
        fillContactForm(contact, true);
        submitNewContact();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.cssSelector("tbody tr:nth-child(2) td.center:nth-child(1) input"));
    }

    public int getContactCount() {
        return wd.findElements(By.cssSelector("tbody tr td.center input")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstName, lastName, null, null,
                    null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
