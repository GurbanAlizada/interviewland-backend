package com.example.interviewlandbackend.model

import javax.persistence.*

@Entity
@Table(name = "images")
data class Image @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    val imageUrl: String,

    val publishId: String

){

    constructor (imageUrl: String , publishId: String) : this(
        id = 0,
        imageUrl = imageUrl,
        publishId = publishId

    )


}