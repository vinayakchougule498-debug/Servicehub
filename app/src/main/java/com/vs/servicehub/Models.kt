package com.vs.servicehub

data class CategoryItem(
    val name: String,
    val iconRes: Int
)

data class FeaturedService(
    val id: Int,
    val name: String,
    val price: String,
    val imageRes: Int,
    val description: String = ""
)

data class Track(
    val title: String,
    val artist: String,
    val colorRes: Int
)

data class Booking(
    val id: Int,
    val workerName: String,
    val serviceName: String,
    val date: String,
    val price: String,
    val status: String, // "Completed", "In Process", "Cancelled"
    val imageRes: Int
)

data class NotificationItem(
    val id: Int,
    val title: String,
    val message: String,
    val time: String,
    val iconRes: Int
)