/*
 * DataSource - Provides static data for categories, places, and place details.
 *
 * This object is as a simple database containing categories,
 * lists of recommended places, and detailed information about each place.
 *
 * Structure:
 * - `categories`: A list of available recommendation categories.
 * - `places`: A Map linking categories to lists of recommended places.
 * - `placesDetail`: A Map storing a description and address for each place.
 *
 * Developed as part of Assignment 4.
 */


package com.example.assignment4.data

object DataSource {
    val categories = listOf("Coffee Shops", "Parks", "Restaurants")

    val places: Map<String, List<String>> = mapOf(
        "Coffee Shops" to listOf("Café La Onda", "Starbucks", "Dutch Bros", "Panera"),
        "Parks" to listOf("Legion Park", "Heritage Park", "Willamette Mission State Park", "Settlemier Park"),
        "Restaurants" to listOf("Casa De Caldos", "Las Islas", "Luis's Taqueria", "The Pierogi Place")
    )

    val placesDetail: Map<String, Pair<String, String>> = mapOf(
        // Coffee Shops
        "Café La Onda" to Pair(
            "A cafe shop that serves as the hub of diverse stories, shared experiences, and the heartbeat of Woodburn's culture.",
            "123 Main St, Woodburn, OR 97071"
        ),
        "Starbucks" to Pair(
            "A popular coffee shop chain offering a variety of drinks and pastries.",
            "456 Oak Ave, Woodburn, OR 97071"
        ),
        "Dutch Bros" to Pair(
            "A drive-thru coffee chain well-known in the PNW.",
            "789 Pine St, Woodburn, OR 97071"
        ),
        "Panera" to Pair(
            "A bakery-cafe shop serving sandwiches, soups, and bread.",
            "101 Maple Rd, Woodburn, OR 97071"
        ),

        // Parks
        "Legion Park" to Pair(
            "A scenic park with walking trails, picnic areas, and a playground.",
            "202 Birch St, Woodburn, OR 97071"
        ),
        "Heritage Park" to Pair(
            "A peaceful park featuring open green spaces.",
            "303 Cedar Ave, Woodburn, OR 97071"
        ),
        "Willamette Mission State Park" to Pair(
            "A large park with hiking trails, kayaking spots, and scenic views.",
            "404 Douglas Fir Ln, Woodburn, OR 97071"
        ),
        "Settlemier Park" to Pair(
            "A community park with sports fields, a playground, and picnic shelters.",
            "505 Elm St, Woodburn, OR 97071"
        ),

        // Restaurants
        "Casa De Caldos" to Pair(
            "A Mexican restaurant specializing in soups and traditional Mexican dishes.",
            "606 Spruce Ave, Woodburn, OR 97071"
        ),
        "Las Islas" to Pair(
            "A seafood restaurant offering fresh fish tacos and ceviche.",
            "707 Redwood Blvd, Woodburn, OR 97071"
        ),
        "Luis's Taqueria" to Pair(
            "A local favorite serving authentic tacos, burritos, and salsas.",
            "808 Ash St, Woodburn, OR 97071"
        ),
        "The Pierogi Place" to Pair(
            "A Polish eatery known for its handmade pierogis and Eastern European food.",
            "909 Chestnut Ct, Woodburn, OR 97071"
        )
    )

}
