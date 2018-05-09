package edu.washington.zhang007.quizdroid

import android.os.Environment
import org.json.JSONArray
import java.io.*
import java.util.*

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

    fun loadJSONData() {
        val sdcard = Environment.getExternalStorageDirectory()
        println(sdcard)

        val file = File(sdcard, "questions.json")
        if(file.exists()) println("questionsFile exists!")

        val input = BufferedInputStream(file.inputStream()).use { it.reader().use { reader -> reader.readText() } }

        val obj = JSONArray(input)
        for (i in 0..(obj.length() - 1)) {
            val topic = obj.getJSONObject(i)
            val title = topic.getString("title")
            val desc = topic.getString("desc")
            val questions = topic.getJSONArray("questions")
            val question = arrayListOf<Question>()
            for (j in 0..(questions.length() - 1)) {
                val questionObj = questions.getJSONObject(j)
                val text = questionObj.getString("text")
                // JSON file index begins at 1
                val correctIndex = questionObj.getInt("answer") - 1
                val jsonAnswers = questionObj.getJSONArray("answers")
                val answerOptions = Array(jsonAnswers.length()) { "" }
                for (k in 0..(jsonAnswers.length() - 1)) {
                    answerOptions[k] = jsonAnswers[k].toString()
                }
                question.add(Question(text, answerOptions, correctIndex))
            }
            val questionPassingArr = question.toArray()
            // This gets around CastException
            val questionArr = Arrays.copyOf(questionPassingArr, questionPassingArr.size, Array<Question>::class.java)

            this.addTopic(Topic(title, desc, desc, questionArr, R.drawable.ic_launcher_background))
        }
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