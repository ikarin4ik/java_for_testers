package ru.stqa.pft.addressbook;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String adress;
    private final String homephone;
    private final String mobilephone;
    private final String email;

    public ContactData(String firstname, String lastname, String adress, String homephone, String mobilephone, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.adress = adress;
        this.homephone = homephone;
        this.mobilephone = mobilephone;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAdress() {
        return adress;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public String getEmail() {
        return email;
    }
}
