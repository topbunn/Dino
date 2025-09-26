package ru.topbun.domain.entity

enum class ModSortType {

    NAME,
    USED_COUNT,
    COMMENT_COUNTS,
    RATING;

    override fun toString() = when(this){
        NAME -> "name"
        USED_COUNT -> "usedCount"
        COMMENT_COUNTS -> "commentCounts"
        RATING -> "rating"
    }
}