package edu.washington.zhang007.quizdroid

class QuizData : TopicRepository {

    val topicData = arrayListOf<Topic>()

    override fun addTopic(t: Topic) {
        this.topicData.add(t)
    }

    override fun getRepository(): ArrayList<Topic> {
        return this.topicData
    }

    override fun removeTopic(t: Topic) {
        this.topicData.remove(t)
    }

    fun loadSampleData() {
        val mathQ1 = Question("What is 2 + 2?", arrayOf("1", "2", "3", "4"), 3)
        val mathQ2 = Question("What is 2 x 2?", arrayOf("1", "2", "3", "4"), 3)
        val physQ1 = Question("F = ", arrayOf("ma", "mc^2", "42", "meow?"), 0)
        val superQ1 = Question("What was the first Marvel movie?",
                                arrayOf("Iron Man", "Thor", "Incredible Hulk", "Avengers"), 0)
        val mathTopic = Topic("Math", "Numbers",
                    "These are numbers", arrayOf(mathQ1, mathQ2), R.drawable.ic_launcher_background)
        val physTopic = Topic("Physics", "Applied Math",
                    "Physics is math but worse", arrayOf(physQ1), R.drawable.ic_launcher_background)
        val superTopic = Topic("Marvel Superheroes", "Marvel > DC",
                    "I don't feel so good", arrayOf(superQ1), R.drawable.ic_launcher_background)

        this.addTopic(mathTopic)
        this.addTopic(physTopic)
        this.addTopic(superTopic)
    }
}