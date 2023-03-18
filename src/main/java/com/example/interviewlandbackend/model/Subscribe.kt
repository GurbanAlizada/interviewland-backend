package com.example.interviewlandbackend.model

import javax.persistence.*


@Entity
@Table(name = "subscribes_user")
data class Subscribe @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=0,
    val email: String

)
