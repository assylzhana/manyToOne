package com.bitlab.manyToOne.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "applications")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    // это значит что несколько курсов могут относиться к 1 юсеру
    private String courseName;
    private String commentary;
    private String phone;
    @Column(name = "handled", columnDefinition = "boolean default false")
    private boolean handled;
}