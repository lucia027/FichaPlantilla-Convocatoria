package org.example.fichaplantillaconvocatoria.plantilla.validator

import com.github.michaelbull.result.Result

/*
 * Interfaz que definela funcion validate
 */
interface Validator<T, E> {
    fun validate (t: T): Result<T, E>
}