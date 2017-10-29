package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests  extends TestBase {

    @Test
    public void testGroupCreation() {

        gotoGroupPage();
        initGroupCreatoin();
        fillGroupForm(new GroupData("test1", "test2", "test3"));
        submitGroupCreatoin();
        returnToGroupPage();
    }

}
