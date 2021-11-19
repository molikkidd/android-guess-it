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
    // The current score
     private val _score = MutableLiveData<Int>()
     val score : LiveData<Int>
        get() = _score


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

    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
//            gameFinished()
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
    init {
        Log.i("GameViewModel", "GameViewModel Created")
        resetList()
        nextWord()
//        set score
        _score.value = 0
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel was cleared")
    }
}