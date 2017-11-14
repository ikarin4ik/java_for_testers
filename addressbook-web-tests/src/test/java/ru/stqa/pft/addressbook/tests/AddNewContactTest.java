package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class AddNewContactTest extends TestBase {

    @Test
    public void testAddNewContact() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().goToAddContactPage();
        ContactData contact = new ContactData("Jane",
                "Smith",
                "743 Evergreen Terrace, Springfield, Anytown",
                "555-55-55",
                "81234567890",
                "smith@jane.org",
                "test1");
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitNewContact();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() +1);
        app.getNavigationHelper().gotoHomePage();

        int max = 0;
        for (ContactData c : after) {
            if (c.getId() > max) {
                max = c.getId();
            }
        }
        contact.setId(max);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
