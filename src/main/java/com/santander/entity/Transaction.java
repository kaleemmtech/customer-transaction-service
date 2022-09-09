package com.santander.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Transaction {

    @Id
    private String Id;

    @Column(name = "sender_id")
    private String senderId;
    @Column(name = "receiver_id")
    private String receiverId;

    @Column
    private double amout;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amout, amout) == 0 && Id.equals(that.Id) && senderId.equals(that.senderId) && receiverId.equals(that.receiverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, senderId, receiverId, amout);
    }
}
