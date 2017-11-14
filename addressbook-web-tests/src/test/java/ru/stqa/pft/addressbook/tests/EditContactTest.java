package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

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
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactEdit(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Jane", "Smith", "743 Evergreen Terrace, Springfield, Anytown",
                "555-55-55", "81234567890", "smith@jane.org", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().updateContactForm();
        app.getContactHelper().returnToContactPage();
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
