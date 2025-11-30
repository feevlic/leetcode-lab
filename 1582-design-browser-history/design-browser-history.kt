class BrowserHistory(homepage: String) {

    private val history = ArrayList<String>()
    private var index = 0

    init {
        history.add(homepage)
    }

    fun visit(url: String) {
        while (history.size > index + 1) history.removeAt(history.size - 1)
        history.add(url)
        index = history.size - 1
    }

    fun back(steps: Int): String {
        index = maxOf(0, index - steps)
        return history[index]
    }

    fun forward(steps: Int): String {
        index = minOf(history.size - 1, index + steps)
        return history[index]
    }

}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * var obj = BrowserHistory(homepage)
 * obj.visit(url)
 * var param_2 = obj.back(steps)
 * var param_3 = obj.forward(steps)
 */