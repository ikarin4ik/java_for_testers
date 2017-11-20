package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class DeleteContactTest extends TestBase {

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
    public void testDeleteContact() {
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }
}
