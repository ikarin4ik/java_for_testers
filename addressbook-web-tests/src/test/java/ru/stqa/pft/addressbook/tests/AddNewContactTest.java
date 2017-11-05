package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddNewContactTest extends TestBase {

    @Test
    public void testAddNewContact() {
        app.getContactHelper().goToContactPage();
        ContactData contactData = new ContactData(
                "Jane",
                "Smith",
                "743 Evergreen Terrace, Springfield, Anytown",
                "555-55-55",
                "81234567890",
                "smith@jane.org",
                "test1");
        app.getContactHelper().fillContactForm(contactData, true);
        app.getContactHelper().submitNewContact();
        app.getNavigationHelper().gotoHomePage();
    }

}
