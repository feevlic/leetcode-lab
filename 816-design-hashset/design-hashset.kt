class MyHashSet() {

    private val present = BooleanArray(1_100_001)
    fun add(key: Int) {
        if (key in present.indices) present[key] = true
    }

    fun remove(key: Int) {
        if (key in present.indices) present[key] = false
    }

    fun contains(key: Int): Boolean {
        return key in present.indices && present [key]
    }

}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * var obj = MyHashSet()
 * obj.add(key)
 * obj.remove(key)
 * var param_3 = obj.contains(key)
 */