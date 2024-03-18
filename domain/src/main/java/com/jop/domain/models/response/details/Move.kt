package com.jop.domain.models.response.details

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)