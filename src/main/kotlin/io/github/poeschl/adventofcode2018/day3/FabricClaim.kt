package io.github.poeschl.adventofcode2018.day3

import java.io.File

fun main(args: Array<String>) {
    val input = File(ClassLoader.getSystemResource("day3/input.txt").file)

    val result1 = findOverlappingClaims(input.readLines())
    println("First Part: $result1")

    val result2 = findNonOverlappingClaims(input.readLines())
    println("Second Part: $result2")
}

fun findOverlappingClaims(claims: List<String>): Int {

    val fabric = createFabric()

    var countOverride = 0

    claims.forEach {
        val claim = parseClaim(it)
        println(claim)

        if (claim != null) {
            for (y in claim.startY until claim.startY + claim.height) {
                for (x in claim.startX until claim.startX + claim.width) {
                    val inch = fabric[y][x]
                    if (inch == null) {
                        fabric[y][x] = claim.id
                    } else {
                        if (inch > 0) {
                            fabric[y][x] = -1
                            countOverride++
                        }
                    }
                }
            }
        }
    }

    return countOverride
}

fun findNonOverlappingClaims(claims: List<String>): Int? {
    val fabric = createFabric()

    claims.forEach {
        val claim = parseClaim(it)

        if (claim != null) {
            for (y in claim.startY until claim.startY + claim.height) {
                for (x in claim.startX until claim.startX + claim.width) {
                    val inch = fabric[y][x]
                    if (inch == null) {
                        fabric[y][x] = claim.id
                    } else {
                        fabric[y][x] = -1
                    }
                }
            }
        }
    }

    var notOverlappingId: Int? = null
    claims.forEach {
        val claim = parseClaim(it)
        var overlapping = false

        if (claim != null) {
            for (y in claim.startY until claim.startY + claim.height) {
                for (x in claim.startX until claim.startX + claim.width) {
                    val inch = fabric[y][x]
                    if (inch != null && inch < 0) {
                        overlapping = true
                    }
                }
            }
            if (!overlapping) {
                notOverlappingId = claim.id
            }
        }
    }

    return notOverlappingId
}

private fun createFabric(): MutableList<MutableList<Int?>> {
    val maxFabricSize = 1000
    val fabric = mutableListOf<MutableList<Int?>>()

    for (y in 0 until maxFabricSize) {
        fabric.add(mutableListOf())
        for (x in 0 until maxFabricSize) {
            fabric[y].add(null)
        }
    }
    println("width: ${fabric[0].size}, height: ${fabric.size}")
    return fabric
}

private fun parseClaim(claimString: String): Claim? {
    val result = "#(\\d+)\\s@\\s(\\d+),(\\d+):\\s(\\d+)x(\\d+)".toRegex().matchEntire(claimString)

    var claim: Claim? = null
    if (result != null) {
        val groups = result.groupValues

        claim = Claim(groups[1].toInt(), groups[2].toInt(), groups[3].toInt(), groups[4].toInt(), groups[5].toInt())
    }
    return claim
}
