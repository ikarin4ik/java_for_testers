package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class EditContactTest extends TestBase {

    @Test
    public void testEditContact(){
        app.getContactHelper().initContactEdit();
        app.getContactHelper().fillContactForm(new ContactData("Jane", "Smith", "743 Evergreen Terrace, Springfield, Anytown", "555-55-55", "81234567890", "smith@jane.org"));
        app.getContactHelper().updateContactForm();
        app.getContactHelper().returnToContactPage();
    }
}
