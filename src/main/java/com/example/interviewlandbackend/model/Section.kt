package com.example.interviewlandbackend.model

import java.io.Serializable
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList


@Entity
@Table(name = "sections")
data class Section @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=0,

    var sectionName: String,

    @ManyToOne
    @JoinColumn(name = "content_id")
    var content: Content ,


    @OneToMany(mappedBy = "section" , cascade = arrayOf(CascadeType.ALL) )
    val questions: List<Question>? = ArrayList(),

    val date: Date?= Date(System.currentTimeMillis())


):Serializable
