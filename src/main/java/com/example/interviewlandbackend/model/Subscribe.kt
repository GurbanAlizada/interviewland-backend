package com.example.interviewlandbackend.model

import java.io.Serializable
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "subscribes_user")
data class Subscribe @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=0,

    val email: String,

    @ManyToOne
    @JoinColumn(name = "contentId")
    val content: Content,

    val date: Date?= Date(System.currentTimeMillis())


):Serializable{


}
