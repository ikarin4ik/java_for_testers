package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class EditContactTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().list().size() == 0) {
            ContactData contactData = new ContactData().withFirstName("Jane").withLastName("Smith")
                    .withAddress("743 Evergreen Terrace, Springfield, Anytown").withHomephone("555-55-55")
                    .withMobilephone("81234567890").withEmail("smith@jane.org").withGroup("test1");
            app.contact().create(contactData);
            app.goTo().homePage();
        }
    }


    @Test
    public void testEditContact(){
        Set<ContactData> before = app.contact().all();
        ContactData editContact = before.iterator().next();
        app.contact().initContactEditbyId(editContact.getId());
        ContactData contact = new ContactData()
                .withId(editContact.getId())
                .withFirstName("John").withLastName("Smith")
                .withAddress("743 Evergreen Terrace, Springfield, Anytown").withHomephone("555-55-55")
                .withMobilephone("81234567890").withEmail("smith@jane.org");
        app.contact().edit(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(editContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
