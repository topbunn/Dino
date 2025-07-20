package ru.topbun.minecraft_mods_pe.presentation.screens.instruction

import kotlinx.serialization.Serializable

@Serializable
data class InstructionEntity(
    val titleRes: Int,
    val imageRes: Int,
){

    companion object{

        fun getAddonInstruction() = listOf(
            InstructionEntity(ru.topbun.ui.R.string.instr_addon_1, ru.topbun.ui.R.drawable.instr_addon_1),
            InstructionEntity(ru.topbun.ui.R.string.instr_addon_2, ru.topbun.ui.R.drawable.instr_addon_2),
            InstructionEntity(ru.topbun.ui.R.string.instr_addon_3, ru.topbun.ui.R.drawable.instr_addon_3),
            InstructionEntity(ru.topbun.ui.R.string.instr_addon_4, ru.topbun.ui.R.drawable.instr_addon_4),
        )

        fun getWorldInstruction() = listOf(
            InstructionEntity(ru.topbun.ui.R.string.instr_world_1, ru.topbun.ui.R.drawable.instr_world_1),
            InstructionEntity(ru.topbun.ui.R.string.instr_world_2, ru.topbun.ui.R.drawable.instr_world_2),
            InstructionEntity(ru.topbun.ui.R.string.instr_world_3, ru.topbun.ui.R.drawable.instr_world_3),
            InstructionEntity(ru.topbun.ui.R.string.instr_addon_4, ru.topbun.ui.R.drawable.instr_addon_4),
        )


    }

}