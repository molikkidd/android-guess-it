package com.example.android.guesstheword.screens.game

// import dependencies
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import  androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
//    change the store data to use mutable live data method
     private val _word = MutableLiveData<String>()
     val word : LiveData<String>
        get() = _word
    // The current score then encapsulate the mutable data
     private val _score = MutableLiveData<Int>()
     val score : LiveData<Int>
        get() = _score

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish : LiveData<Boolean>
        get() = _eventGameFinish

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }
    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }
    init {
        Log.i("GameViewModel", "GameViewModel Created")
//        set the game finish method at initialization
        _eventGameFinish.value = false
        resetList()
        nextWord()
//        set score
        _score.value = 0
    }
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
//            rest to true when no more words to use
          _eventGameFinish.value = true
        } else {
            _word.value = wordList.removeAt(0)
        }
    }
    fun onSkip() {
//        use mutable live data syntax
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
//        use mutable live data syntax
        _score.value = (score.value)?.plus(1)
        nextWord()
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel was cleared")
    }
}