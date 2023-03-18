package com.example.interviewlandbackend.model

import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "contents")
data class Content @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = 0,

    var contentName: String,

    var description: String,

    @OneToMany(
        mappedBy = "content" ,
        cascade = arrayOf(CascadeType.ALL)
    )
    val sectionList: List<Section>? = ArrayList(),

    @OneToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "image_id")
    var image: Image,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User?


):Serializable{





}
