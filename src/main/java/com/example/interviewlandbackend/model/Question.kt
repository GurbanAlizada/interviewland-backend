package com.example.interviewlandbackend.model

import javax.persistence.*

@Entity
@Table(name = "questions")
data class Question @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=0,

    var questionTitle: String,

    @Column(columnDefinition = "TEXT")
    @Lob
    var description: String,

    @Column(columnDefinition = "TEXT")
    @Lob
    var sourceCode: String,

    @ManyToOne
    @JoinColumn(name = "section_id")
    var section: Section


)
