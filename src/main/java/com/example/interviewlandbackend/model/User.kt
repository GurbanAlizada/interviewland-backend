package com.example.interviewlandbackend.model

import javax.persistence.*


@Entity
@Table(name = "users")
data class User @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=0,
    val username: String,
    val password: String,

    @Enumerated(EnumType.STRING)
    val role: Role,

    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    val contents : List<Content>? = ArrayList()



)
