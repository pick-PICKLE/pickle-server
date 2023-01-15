
package com.pickle.server.dress.domain;


import com.pickle.server.common.Timestamped;
import com.pickle.server.store.domain.Store;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Dress extends Timestamped {

    @Id
    @Column(name ="dress_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String category;

    @Column
    private String name;

    @Column
    private Integer price;

    @OneToMany(mappedBy = "dress")
    private List<DressImage> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToOne
    @JoinColumn(name = "dress_option_id", nullable = false)
    private DressOption option1;

    @OneToOne
    @JoinColumn(name = "dress_option_id", nullable = false)
    private DressOption option2;


    @OneToMany(mappedBy = "dress")
    private List<DressStock> dressStocks = new ArrayList<>();

}