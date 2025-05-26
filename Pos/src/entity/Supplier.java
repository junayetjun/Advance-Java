
package entity;


public class Supplier {
    
    private int id;
    private String name;
    private String address;
    private String cell;
    private String email;
    private String contactPerson;
    private String contactPPost;

    public Supplier() {
    }

    public Supplier(int id, String name, String address, String cell, String email, String contactPerson, String contactPPost) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cell = cell;
        this.email = email;
        this.contactPerson = contactPerson;
        this.contactPPost = contactPPost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPPost() {
        return contactPPost;
    }

    public void setContactPPost(String contactPPost) {
        this.contactPPost = contactPPost;
    }
    
    
    
    
    
    
    
}
