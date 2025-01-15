package com.example.chapter_eleven

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.chapter_eleven.data.db.CatDatabase
import com.example.chapter_eleven.data.db.dao.CatDAO
import com.example.chapter_eleven.data.db.entities.CatEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatDaoTest {
    private lateinit var database: CatDatabase
    private lateinit var catDao: CatDAO

    @Before
    fun createDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CatDatabase::class.java
        ).allowMainThreadQueries().build()
        catDao = database.catDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun testInsertAndReadCat() = runTest {
        // Given a cat
        val cat = CatEntity(
            id = "1",
            mimetype = "Test",
            tags = listOf("cute", "fluffy"),
            isFavorite = false,
            size = 2
        )
        // Insert the cat to the database
        catDao.insert(cat)
        // Then the cat is in the database
        val cats = catDao.getCats()
        assert(cats.first().contains(cat))
    }

    @Test
    fun testAddCatToFavorites() = runTest {
        // Given a cat
        val cat = CatEntity(
            id = "1",
            tags = listOf("cute", "fluffy"),
            isFavorite = false,
            mimetype = "Test",
            size = 1
        )
        catDao.insert(cat)
        // Favorite the cat
        catDao.update(cat.copy(isFavorite = true))

        // Assert that the cat is in the favorite list
        val favoriteCats = catDao.getFavoriteCats()
        assert(favoriteCats.first().contains(cat.copy(isFavorite = true)))
    }
}
