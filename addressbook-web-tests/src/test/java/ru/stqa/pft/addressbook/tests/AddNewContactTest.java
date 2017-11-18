package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class AddNewContactTest extends TestBase {

    @Test
    public void testAddNewContact() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().goToAddContactPage();
        ContactData contact = new ContactData("John",
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
        app.goTo().gotoHomePage();

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
