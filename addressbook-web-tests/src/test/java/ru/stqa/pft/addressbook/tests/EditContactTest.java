package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class EditContactTest extends TestBase {

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
    public void testEditContact(){
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        app.getContactHelper().initContactEdit(index);
        ContactData contact = new ContactData(before.get(index).getId(), "Jane", "Smith", "743 Evergreen Terrace, Springfield, Anytown",
                "555-55-55", "81234567890", "smith@jane.org", null);
        app.getContactHelper().editContact(index, contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
