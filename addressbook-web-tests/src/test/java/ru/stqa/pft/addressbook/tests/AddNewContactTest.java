package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddNewContactTest extends TestBase {

    @Test
    public void testAddNewContact() {
        app.getContactHelper().goToContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Jane", "Smith", "743 Evergreen Terrace, Springfield, Anytown", "555-55-55", "81234567890", "smith@jane.org"));
        app.getContactHelper().submitNewContact();
        app.getContactHelper().returnToContactPage();
    }

}
