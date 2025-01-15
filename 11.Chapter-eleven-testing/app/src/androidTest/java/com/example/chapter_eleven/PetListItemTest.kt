package com.example.chapter_eleven

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.chapter_eleven.data.model.Cat
import com.example.chapter_eleven.views.PetListItem
import org.junit.Rule
import org.junit.Test

class PetListItemTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPetListItem() {
        with(composeTestRule) {
            setContent {
                PetListItem(
                    cat = Cat(
                        id = "1",
                        tags = listOf("cute", "fluffy"),
                        isFavorite = false,
                        mimetype = "Test",
                        size = 1
                    ),
                    onPetClicked = { },
                    onFavoriteClicked = {})
            }
            // Assertions using tags
            onNodeWithTag("PetListItemCard").assertExists()
            onNodeWithTag("PetListItemColumn").assertExists()
            onNodeWithTag("PetListItemFavoriteIcon").assertExists()
            // Assertions using text
            onNodeWithText("fluffy").assertIsDisplayed()
            onNodeWithContentDescription("Favorite").assertIsDisplayed()
            onNodeWithContentDescription("Cute cat").assertIsDisplayed()
            // Actions
            onNodeWithTag("PetListItemFavoriteIcon").performClick()
        }
    }
}