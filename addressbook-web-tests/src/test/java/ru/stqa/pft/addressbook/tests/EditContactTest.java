package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class EditContactTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().all().size() == 0) {
            ContactData contactData = new ContactData().withFirstName("Jane").withLastName("Smith")
                    .withAddress("743 Evergreen Terrace, Springfield, Anytown").withHomephone("555-55-55")
                    .withMobilephone("81234567890").withWorkphone("33 34 33").withEmail("smith@jane.org").withGroup("test1");
            app.contact().create(contactData);
            app.goTo().homePage();
        }
    }


    @Test
    public void testEditContact(){
        Contacts before = app.contact().all();
        ContactData editContact = before.iterator().next();
        app.contact().initContactEditbyId(editContact.getId());
        ContactData contact = new ContactData()
                .withId(editContact.getId())
                .withFirstName("John").withLastName("Smith")
                .withAddress("743 Evergreen Terrace, Springfield, Anytown").withHomephone("555-55-55")
                .withMobilephone("81234567890").withEmail("smith@jane.org");
        app.contact().edit(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(editContact).withAdded(contact)));
    }
}
