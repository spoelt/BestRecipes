package com.example.bestrecipes.domain.util

// generic type T is used because data will be mapped between domain model and network model
// as well as between domain models and DTO's
interface DomainMapper<T, DomainModel> {

    fun mapToDomainModel(model: T): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): T
}