package ru.topbun.data.mappers

import ru.topbun.data.database.entity.FavoriteDBO
import ru.topbun.domain.entity.FavoriteEntity

internal fun FavoriteDBO.toEntity() = FavoriteEntity(
    id = id,
    modId = modId,
    status = status
)

internal fun FavoriteEntity.toDBO() = FavoriteDBO(
    id = id,
    modId = modId,
    status = status
)

