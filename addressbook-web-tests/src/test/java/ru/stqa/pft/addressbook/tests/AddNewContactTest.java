package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class AddNewContactTest extends TestBase {

    @Test
    public void testAddNewContact() {
        Set<ContactData> before = app.contact().all();
        app.contact().goToAddContactPage();
        ContactData contact = new ContactData().withFirstName("John").withLastName("Smith")
                .withAddress("743 Evergreen Terrace, Springfield, Anytown").withHomephone("555-55-55")
                .withMobilephone("81234567890").withEmail("smith@jane.org").withGroup("test1");
        app.contact().fillForm(contact, true);
        app.contact().submit();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() +1);
        app.goTo().homePage();

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }

}
