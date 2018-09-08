package com.creative.cach.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Task_Cache(@PrimaryKey var id:Int,
                 var name:String,
                 var nameEN:String) {
}