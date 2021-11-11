package com.example.dto;

public class RecipientAndRequisiteDTO {

    private final long id;

    private final String name;

    private final String note;

    public RecipientAndRequisiteDTO(long id, String name, String note) {
        this.id = id;
        this.name = name;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "RecipientAndRequisiteDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
