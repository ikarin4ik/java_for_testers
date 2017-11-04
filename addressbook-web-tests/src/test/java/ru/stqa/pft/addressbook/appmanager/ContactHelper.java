package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void goToContactPage() {
        click(By.linkText("add new"));
    }

    public void returnToContactPage() {
        click(By.linkText("home page"));
    }

    public void submitNewContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("address"), contactData.getAdress());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobilephone());
        type(By.name("email"), contactData.getEmail());
    }

    public void initContactEdit() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a"));
    }

    public void updateContactForm() {
        click(By.cssSelector("div#content form input[name='update'][value='Update']:last-of-type"));
    }

    public void selectContact() {
        click(By.cssSelector("tbody tr:nth-child(2) td.center:nth-child(1) input"));

    }

    public void deleteSelectedContact() {
        click(By.cssSelector("div.left input[type='button'][value='Delete']"));
        acceptAlert();
    }


    public void returnToHome() {
        click(By.linkText("home"));
    }
}
