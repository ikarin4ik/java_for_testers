package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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

    public void submit() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
        contactCache = null;
    }

    public void fillForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobilephone());
        type(By.name("work"), contactData.getWorkphone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        attach(By.name("photo"), contactData.getPhoto());
        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group")))
                        .selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        }
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactEdit(int index) {
        wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td[8]/a")).get(index).click();
    }

    public void initContactEditbyId(int id) {
        wd.findElement(By.xpath("//a[contains(@href,'edit.php?id=" + id + "')]")).click();
    }


    public void updateContactForm() {
        click(By.cssSelector("div#content form input[name='update'][value='Update']:last-of-type"));
    }

    public void select(int index) {
        wd.findElements(By.cssSelector("tbody tr td.center input")).get(index).click();

    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();

    }

    public void deleteContact() {
        click(By.cssSelector("div.left input[type='button'][value='Delete']"));
        acceptAlert();
    }

    public void create(ContactData contact) {
        goToAddContactPage();
        fillForm(contact, true);
        submit();
        contactCache = null;
    }

    public void edit(ContactData contact) {
        fillForm(contact, false);
        updateContactForm();
        contactCache = null;
        returnToContactPage();
    }

    public void delete(int index) {
        select(index);
        deleteContact();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.cssSelector("tbody tr:nth-child(2) td.center:nth-child(1) input"));
    }

    public int getContactCount() {
        return wd.findElements(By.cssSelector("tbody tr td.center input")).size();
    }


    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache !=null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String address = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
            String allPhones = element.findElement(By.cssSelector("td:nth-child(6)")).getText();
            String allEmails = element.findElement(By.cssSelector("td:nth-child(5)")).getText();
            contactCache.add(new ContactData().withId(id).withFirstName(firstName)
                    .withLastName(lastName).withAddress(address)
                    .withAllPhones(allPhones).withAllEmails(allEmails));
        }
        return new Contacts(contactCache);
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactEditbyId(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String adress = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
                .withHomephone(home).withMobilephone(mobile).withWorkphone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(adress);
    }

    public void initContactModificationById (int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }


}
