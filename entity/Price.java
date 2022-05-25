package com.example.binance.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@IdClass(PriceId.class)
@Getter
@Setter
@NoArgsConstructor
@Table(name = "prices")
public class Price
        implements Comparable<Price> {


    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "code_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "code_key"))
    @JsonBackReference
    private Code code;

    @Id
    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "val")
    private BigInteger val;

    @Override
    public int compareTo(Price o) {
        return o.getTime().compareTo(time);
    }


}
