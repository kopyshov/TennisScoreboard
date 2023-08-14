package com.kharizma.tennisscoreboard.players;

import jakarta.persistence.*;

import java.io.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "PLAYER")
public class Player implements Serializable {
    @Id
    @Column(name = "PLAYER_ID", columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;
    public Player() {
    }
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void generateId() {
        id = UUID.randomUUID();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (!Objects.equals(id, player.id)) return false;
        return Objects.equals(name, player.name);
    }
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
