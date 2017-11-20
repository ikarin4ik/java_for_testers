package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class AddNewContactTest extends TestBase {

    @Test
    public void testAddNewContact() {
        List<ContactData> before = app.contact().list();
        app.contact().goToAddContactPage();
        ContactData contact = new ContactData().withFirstName("John").withLastName("Smith")
                .withAddress("743 Evergreen Terrace, Springfield, Anytown").withHomephone("555-55-55")
                .withMobilephone("81234567890").withEmail("smith@jane.org").withGroup("test1");
        app.contact().fillForm(contact, true);
        app.contact().submit();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() +1);
        app.goTo().homePage();

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
