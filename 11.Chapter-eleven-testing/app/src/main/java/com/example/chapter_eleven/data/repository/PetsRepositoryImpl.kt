package com.example.chapter_eleven.data.repository

import com.example.chapter_eleven.data.db.dao.CatDAO
import com.example.chapter_eleven.data.db.entities.CatEntity
import com.example.chapter_eleven.data.model.Cat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

class PetsRepositoryImpl(
    private val catsAPI: CatsAPI,
    private val dispatcher: CoroutineDispatcher,
    private val catDAO: CatDAO
) : PetsRepository {
    override suspend fun getPets(): Flow<List<Cat>> {
        return withContext(dispatcher) {
            catDAO.getCats()
                .map { petsCached ->
                    petsCached.map { catEntity ->
                        Cat(
                            id = catEntity.id,
                            mimetype = catEntity.mimetype,
                            size = catEntity.size,
                            tags = catEntity.tags,
                            isFavorite = catEntity.isFavorite
                        )
                    }
                }
                .onEach {
                    if (it.isEmpty()) {
                        fetchRemotePets()
                    }
                }
        }
    }

    override suspend fun fetchRemotePets() {
        withContext(dispatcher) {
            val response = catsAPI.fetchCats("cute")
            if (response.isSuccessful) {
                response.body()!!.map {
                    catDAO.insert(
                        CatEntity(
                            id = it.id,
                            mimetype = it.mimetype,
                            size = it.size,
                            tags = it.tags,
                            isFavorite = it.isFavorite
                        )
                    )
                }
            }
        }
    }

    override suspend fun updatePets(cat: Cat) {
        withContext(dispatcher) {
            catDAO.update(
                CatEntity(
                    id = cat.id,
                    mimetype = cat.mimetype,
                    size = cat.size,
                    tags = cat.tags,
                    isFavorite = cat.isFavorite
                )
            )
        }
    }

    override suspend fun getFavoritePets(): Flow<List<Cat>> {
        return withContext(dispatcher) {
            catDAO.getFavoriteCats()
                .map { petsCached ->
                    petsCached.map { catEntity ->
                        Cat(
                            id = catEntity.id,
                            mimetype = catEntity.mimetype,
                            size = catEntity.size,
                            tags = catEntity.tags,
                            isFavorite = catEntity.isFavorite
                        )
                    }
                }
        }
    }
}