package com.tptogiar.contactslist;


/**
 * @Author: Tptogiar
 * @Description: 用来创建联系人实例
 * @Date: 2021/4/25-16:44
 */
public class ContactInfo {
    private String name;
    private String contactId;
    private String phone;

    public ContactInfo(String name, String contactId, String phone) {
        this.name = name;
        this.contactId = contactId;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
