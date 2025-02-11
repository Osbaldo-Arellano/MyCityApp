package com.example.assignment4.data

class DataSource {
    val categories = listOf("Coffee Shops", "Parks", "Restaurants")

    val places: Map<String, List<String>> = mapOf(
        "Coffee Shops" to listOf("Café La Onda", "Starbucks", "Dutch Bros", "Panera"),
        "Parks" to listOf("Legion Park", "Heritage Park", "Willamette Mission State Park", "Settlemier Park"),
        "Restaurants" to listOf("Casa De Caldos", "Las Islas", "Luis's Taqueria", "The Pierogi Place")
    )

    val placesDetail: Map<String, String> = mapOf(
        // Coffee Shops
        "Café La Onda" to "A cafe shop that serves as the hub of diverse stories, shared experiences, and the heartbeat of Woodburn's culture.",
        "Starbucks" to "A popular coffee shop chain offering a variety of drinks and pastries.",
        "Dutch Bros" to "A drive-thru coffee chain wel-known in the PNW.",
        "Panera" to "A bakery-cafe shop serving sandwiches, soups, and bread.",

        // Parks
        "Legion Park" to "A scenic park with walking trails, picnic areas, and a playground.",
        "Heritage Park" to "A peaceful park featuring open green spaces.",
        "Willamette Mission State Park" to "A large park with hiking trails, kayaking spots, and scenic views.",
        "Settlemier Park" to "A community park with sports fields, a playground, and picnic shelters.",

        // Restaurants
        "Casa De Caldos" to "A Mexican restaurant specializing in soups and traditional Mexican dishes.",
        "Las Islas" to "A seafood restaurant offering fresh fish tacos and ceviche.",
        "Luis's Taqueria" to "A local favorite serving authentic tacos, burritos, and salsas.",
        "The Pierogi Place" to "A Polish eatery known for its handmade pierogis and Eastern European food."
    )
}
