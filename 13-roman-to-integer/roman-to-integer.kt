class Solution {

    private val valueMap = mapOf(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )
    
    fun romanToInt(s: String): Int {
        var total = 0
        var previousValue = 0

        for (ch in s.reversed()) {
            val currentValue = valueMap[ch] ?: 0
            if (currentValue < previousValue) {
                total -= currentValue 
            } else {
                total += currentValue
            }
            previousValue = currentValue
        }
        return total 
    }
}