package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

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
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
