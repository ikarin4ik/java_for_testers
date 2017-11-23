package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

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
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone())
                .stream().filter((s) ->! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));

    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
