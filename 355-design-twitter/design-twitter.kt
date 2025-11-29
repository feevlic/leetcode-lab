class Twitter() {

    private var timestampCounter = 0
    private val following = mutableMapOf<Int, MutableSet<Int>>()
    private val userTweets = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()

    private fun ensureUserExists(userId: Int) {
        if (!following.containsKey(userId)) following[userId] = mutableSetOf(userId)
        if (!userTweets.containsKey(userId)) userTweets[userId] = mutableListOf()
    }

    fun postTweet(userId: Int, tweetId: Int) {
        ensureUserExists(userId)
        timestampCounter++
        userTweets[userId]!!.add(0, Pair(timestampCounter, tweetId))
    }

    fun getNewsFeed(userId: Int): List<Int> {
        ensureUserExists(userId)
        data class HeapEntry(val timestamp: Int, val tweetId: Int, val userId: Int, val index: Int)
        val heap = java.util.PriorityQueue(compareByDescending<HeapEntry> { it.timestamp })
        val result = mutableListOf<Int>()
        val followees = following[userId] ?: setOf(userId)
        for (followee in followees) {
            val tweets = userTweets[followee]
            if (!tweets.isNullOrEmpty()) {
                val (ts, id) = tweets[0]
                heap.add(HeapEntry(ts, id, followee, 0))
            }
        }
        while (heap.isNotEmpty() && result.size < 10) {
            val entry = heap.poll()
            result.add(entry.tweetId)

            val nextIndex = entry.index + 1
            val tweetsOfUser = userTweets[entry.userId]!!
            if (nextIndex < tweetsOfUser.size) {
                val (nextTs, nextId) = tweetsOfUser[nextIndex]
                heap.add(HeapEntry(nextTs, nextId, entry.userId, nextIndex))
            }
        }

        return result
    }

    fun follow(followerId: Int, followeeId: Int) {
        if (followerId == followeeId) return
        ensureUserExists(followerId)
        ensureUserExists(followeeId)
        following[followerId]!!.add(followeeId)
    }

    fun unfollow(followerId: Int, followeeId: Int) {
        if (followerId == followeeId) return
        following[followerId]?.remove(followeeId)
    }

}

/**
 * Your Twitter object will be instantiated and called as such:
 * var obj = Twitter()
 * obj.postTweet(userId,tweetId)
 * var param_2 = obj.getNewsFeed(userId)
 * obj.follow(followerId,followeeId)
 * obj.unfollow(followerId,followeeId)
 */