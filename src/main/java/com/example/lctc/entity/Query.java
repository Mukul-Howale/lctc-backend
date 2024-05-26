package com.example.lctc.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "query")
public class Query {

    @Id
    @Column(name = "query_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queryId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "query")
    private String query;
}
