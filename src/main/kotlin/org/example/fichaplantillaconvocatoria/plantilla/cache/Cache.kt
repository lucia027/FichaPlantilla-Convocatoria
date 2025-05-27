package org.example.fichaplantillaconvocatoria.plantilla.cache

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import org.example.fichaplantillaconvocatoria.config.Config
import org.example.fichaplantillaconvocatoria.plantilla.models.Plantilla
import org.lighthousegames.logging.logging
import java.util.concurrent.TimeUnit

fun providePersonalCache(config : Config): Cache<Long, Plantilla> {
    return Caffeine.newBuilder()
        .maximumSize(config.cacheCapacity)
        .expireAfterWrite(config.cacheExpiration, TimeUnit.SECONDS).build()
}