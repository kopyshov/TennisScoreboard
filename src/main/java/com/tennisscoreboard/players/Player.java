package com.tennisscoreboard.players;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.io.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "PLAYER")
@Getter
@Setter
@NoArgsConstructor
public class Player implements Serializable {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "PLAYER_ID")
    private UUID id;
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Transient
    private UUID currentId;

    public static Player getEmptyPlayer() {
        Player emptyPlayer = new Player();
        emptyPlayer.setName("-");
        return emptyPlayer;
    }

    @Override
    public boolean equals(Object o) {
        Player player = (Player) o;
        return Objects.equals(this.name, player.name);
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
