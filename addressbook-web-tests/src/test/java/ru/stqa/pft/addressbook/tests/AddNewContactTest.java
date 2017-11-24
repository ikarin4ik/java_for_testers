package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewContactTest extends TestBase {

    @Test
    public void testAddNewContact() {
        Contacts before = app.contact().all();
        app.contact().goToAddContactPage();
        File photo = new File("src/test/resources/pic.jpg");
        ContactData contact = new ContactData().withFirstName("John").withLastName("Smith")
                .withAddress("743 Evergreen Terrace, Springfield, Anytown").withHomephone("555-55-55")
                .withMobilephone("81234567890").withWorkphone("33 34 33").withEmail("smith@jane.org")
                .withGroup("test1").withPhoto(photo);
        app.contact().fillForm(contact, true);
        app.contact().submit();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() +1));
        app.goTo().homePage();

        assertThat(after, equalTo(before
                .withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

//    @Test
//    public void testCurrentDir() {
//        File currentDir = new File(".");
//        System.out.println(currentDir.getAbsolutePath());
//        File photo = new File("src/test/resources/pic.jpg");
//        System.out.println(photo.getAbsolutePath());
//        System.out.println(photo.exists());
//    }


}
