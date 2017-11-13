package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

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
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
    }
}
