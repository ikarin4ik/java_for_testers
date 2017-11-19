package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class DeleteContactTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (!app.getContactHelper().isThereAContact()) {
            ContactData contactData = new ContactData(
                    "Jane", "Smith", "743 Evergreen Terrace, Springfield, Anytown",
                    "555-55-55", "81234567890", "smith@jane.org", "test1");
            app.getContactHelper().createContact(contactData);
            app.goTo().gotoHomePage();
        }
    }

    @Test
    public void testDeleteContact() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
