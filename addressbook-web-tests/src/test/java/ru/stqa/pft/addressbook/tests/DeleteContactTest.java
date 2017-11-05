package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class DeleteContactTest extends TestBase {

    @Test
    public void testDeleteContact() {
        if (!app.getContactHelper().isThereAContact()) {
            ContactData contactData = new ContactData(
                    "Jane",
                    "Smith",
                    "743 Evergreen Terrace, Springfield, Anytown",
                    "555-55-55",
                    "81234567890",
                    "smith@jane.org",
                    "test1");
            app.getContactHelper().createContact(contactData);
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
