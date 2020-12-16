/**
 * Program that implements classes for different kinds of dwellings
 * Shows how to:
 * Create class hierarchy, variables and methods with inheritance,
 * abstract class, overriding, and private vs.public variables.
 * */


import kotlin.math.PI
import kotlin.math.sqrt


fun main() {
    val squareCabin = SquareCabin(6, 50.0)
    val roundHut = RoundHut(3, 10.0)
    val roundTower = RoundTower(4, 15.5)
    with(squareCabin) {
        println("-----Square Cabin-----")
        println("Capacity: $capacity")
        println("Material: $buildingMaterial")
        println("Has room? ${hasRoom()}")
        println("Floor area: %.2f".format(floorArea()))
    }
    with(roundHut) {
        println("\n-----Round Hut-----")
        println("Capacity: $capacity")
        println("Material: $buildingMaterial")
        println("Has room? ${hasRoom()}")
        getRoom()
        println("Has room? ${hasRoom()}")
        getRoom()
        println("Floor area: %.2f".format(floorArea()))
        println("Carpet size: %.2f".format(calculateMaxCarpetSize()))
    }
    with(roundTower) {
        println("\n-----Round Tower-----")
        println("Capacity: $capacity")
        println("Material: $buildingMaterial")
        println("Has room? ${hasRoom()}")
        println("Floor area: %.2f".format(floorArea()))
        println("Carpet size: %.2f".format(calculateMaxCarpetSize()))
    }
}

/**
 * Defines properties common to all dwellings.
 * @param residents Current number of residents*/
abstract class Dwelling(private var residents: Int) {
    abstract val buildingMaterial: String
    abstract var capacity: Int

    /**
    * Checks whether there is room for another resident.
    * @return true if room available, false otherwise
    * */
    fun hasRoom(): Boolean {
        return residents < capacity
    }

    /**
     * Calculates the floor area of the dwelling.
     * Implemented by subclasses where shape is determined.
     *
     * @return floor area*/
    abstract fun floorArea(): Double

    /**
     * Compares the capacity to the number of residents and
     * if capacity is larger than number of residents,
     * add resident by increasing the number of residents.
     * Print the results*/
    fun getRoom() {
        if (hasRoom()) {
            residents++
            println("You got a room!")
        } else {
            println("Sorry sweetheart, at capacity and no rooms left.")
        }
    }


}

/**
 * A square cabin dwelling
 *
 * @param residents Current number of residents
 * @param length Length*/
class SquareCabin(private val residents: Int, private val length: Double) : Dwelling(residents) {
    override val buildingMaterial = "Wood"
    override var capacity = 6

    /**
     * Calculates the floor area for a square dwelling
     *
     * @return floor area*/
    override fun floorArea(): Double {
        return length * length
    }
}

/**
 * A round hut dwelling with circular floorspace
 *
 * @param residents Current number of residents
 * @param radius Radius
 * */
open class RoundHut(residents: Int, val radius: Double) : Dwelling(residents) {
    override val buildingMaterial = "Straw"
    override var capacity = 4
    override fun floorArea(): Double {
        return PI * radius * radius
    }

    /**
     * Calculates the Maximum Carpet size for a round hut dwelling
     * @return length of the carpet*/
    fun calculateMaxCarpetSize(): Double {
        val diameter = 2 * radius
        return sqrt(diameter * diameter / 2)
    }
}

/**
 * A round tower hut dwelling with floors
 *
 * @param residents Current number of residents
 * @param radius Radius
 * @param floors Number of floors, defaults to 2*/
class RoundTower(residents: Int, radius: Double, private val floors: Int = 2) :
    RoundHut(residents, radius) {
    override val buildingMaterial = "Stone"
    override var capacity = 4 * floors

    /**
     * Calculates the total floor area for a tower dwelling
     * with multiple floors
     *
     * @return floor area*/
    override fun floorArea(): Double {
        return PI * radius * radius * floors
    }
}