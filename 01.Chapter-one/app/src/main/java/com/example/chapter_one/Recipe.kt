package com.example.chapter_one

class Recipe {

    private val ingredients = mutableListOf<String>()

    fun addIngredient(name: String) {
        ingredients.add(name)
    }

    fun getIngredients(): List<String> {
        return ingredients
    }
}