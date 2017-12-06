package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;

import static org.testng.Assert.assertEquals;

public class DeleteContactFromGroupTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
        if (app.db().contacts().size() == 0) {
            createContact();
        }
    }

    private ContactData createContact() {
        app.goTo().homePage();
        ContactData contactData = new ContactData().withFirstName("Jane").withLastName("Smith")
                .withAddress("743 Evergreen Terrace, Springfield, Anytown").withHomephone("555-55-55")
                .withMobilephone("81234567890").withWorkphone("33 34 33").withEmail("smith@jane.org");
        app.contact().create(contactData);
        return contactData;
    }


    @Test
    public void testAddContactToGroup(){
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        GroupData group = null;
        ContactData contact;
        boolean foundContactInGroup = false;
        Iterator<ContactData> contactsIterator = contacts.iterator();
        while ((contact = contactsIterator.next()) != null) {
            Iterator<GroupData> groupsIterator = groups.iterator();
            while((group = groupsIterator.next()) != null) {
                if (contact.getGroups().contains(group)){
                    foundContactInGroup = true;
                    break;
                }
            }
            if(foundContactInGroup){
                break;
            }
        }

        group = group != null ? group : groups.iterator().next();
        if(!foundContactInGroup) {
            contact = createContact();
            app.goTo().homePage();
            app.contact().addToGroup(contact, group);
        }
        app.goTo().homePage();
        app.contact().removeFromGroup(contact, group);
        ContactData finalContact = contact;
        ContactData contactFromDb = app.db().contacts().stream()
                .filter(c -> c.getId() == finalContact.getId())
                .findFirst().orElse(null);
        assertEquals(false, contactFromDb.getGroups().contains(group));

    }
}
