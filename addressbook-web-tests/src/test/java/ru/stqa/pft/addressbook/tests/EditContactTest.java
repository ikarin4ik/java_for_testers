package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class EditContactTest extends TestBase {

    @Test
    public void testEditContact(){
        if (!app.getContactHelper().isThereAContact()) {
            ContactData contactData = new ContactData(
                    "Jane", "Smith", "743 Evergreen Terrace, Springfield, Anytown",
                    "555-55-55", "81234567890", "smith@jane.org", "test1");
            app.getContactHelper().createContact(contactData);
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().initContactEdit();
        ContactData contactData = new ContactData(
                "Jane", "Smith", "743 Evergreen Terrace, Springfield, Anytown",
                "555-55-55", "81234567890", "smith@jane.org", null);
        app.getContactHelper().fillContactForm(contactData, false);
        app.getContactHelper().updateContactForm();
        app.getContactHelper().returnToContactPage();
    }
}
