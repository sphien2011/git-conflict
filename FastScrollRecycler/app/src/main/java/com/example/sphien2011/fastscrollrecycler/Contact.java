package com.example.sphien2011.fastscrollrecycler;

/**
 * Created by sphien2011 on 30/10/2016.
 */
public class Contact implements Comparable<Contact> {
    private String name;

    public Contact(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Contact contact) {
        return Utils.getFirstChar(name).compareTo(Utils.getFirstChar(contact.name));
    }
}
