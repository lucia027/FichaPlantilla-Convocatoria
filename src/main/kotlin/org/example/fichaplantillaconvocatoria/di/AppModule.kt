package org.example.fichaplantillaconvocatoria.di

import com.github.benmanes.caffeine.cache.Cache
import org.example.fichaplantillaconvocatoria.config.Config
import org.example.fichaplantillaconvocatoria.database.provideDatabaseManager
import org.example.fichaplantillaconvocatoria.plantilla.cache.providePersonalCache
import org.example.fichaplantillaconvocatoria.plantilla.dao.PlantillaDao
import org.example.fichaplantillaconvocatoria.plantilla.dao.providePlantillaDao
import org.example.fichaplantillaconvocatoria.plantilla.models.Plantilla
import org.example.fichaplantillaconvocatoria.plantilla.repositories.PlantillaRepository
import org.example.fichaplantillaconvocatoria.plantilla.repositories.PlantillaRepositoryImpl
import org.example.fichaplantillaconvocatoria.plantilla.service.PlantillaServiceImpl
import org.example.fichaplantillaconvocatoria.plantilla.viewmodel.PlantillaViewModel
import org.jdbi.v3.core.Jdbi
import org.koin.dsl.module

val appModule = module {
    single<Jdbi> { provideDatabaseManager(
        config = get()
    ) }

    single<PlantillaDao> { providePlantillaDao(
        jdbi = get()
    ) }

    single<Cache<Long, Plantilla>> { providePersonalCache(
        config = get()
    ) }

    single<Config> {
        Config()
    }

    single<PlantillaRepository> { PlantillaRepositoryImpl(
        get()
    ) }

    // Servicio
    single<PlantillaServiceImpl> {
        val repo = get<PlantillaRepository>()
        val config = get<Config>()
        val cache = get<Cache<Long, Plantilla>>()
        println("Creando PlantillaServiceImpl con repo=$repo, config=$config, cache=$cache")
        PlantillaServiceImpl(repo as PlantillaRepositoryImpl, config, cache)
    }

    // ViewModel
    single { PlantillaViewModel(servicio = get() as PlantillaServiceImpl) }
}