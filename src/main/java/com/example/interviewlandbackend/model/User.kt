package com.example.interviewlandbackend.model

import java.io.Serializable
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList


@Entity
@Table(name = "users")
data class User @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=0,

    @Column(unique = true)
    val username: String,

    val password: String,

    @Column(unique = true)
    val email: String,

    @Enumerated(EnumType.STRING)
    val role: Role,

    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    val contents : List<Content>? = ArrayList() ,

    @Column(name = "creation_date")
    val createdDate: Date?= Date(System.currentTimeMillis())


):Serializable
