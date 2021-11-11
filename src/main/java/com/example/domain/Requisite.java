package com.example.domain;

import javax.persistence.*;

@Entity
@Table(name = "requisite")
public class Requisite {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String bic;

    @Column
    private String bill;

    @Column
    private String note;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Recipient recipient;

    public Requisite() {
    }

    public Requisite(String bic, String bill, String note, Recipient recipient) {
        this.bic = bic;
        this.bill = bill;
        this.note = note;
        this.recipient = recipient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "\n\tRequisite{" +
                "id=" + id +
                ", bic='" + bic + '\'' +
                ", bill='" + bill + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
