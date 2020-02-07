package com.mobigod.avin.ui.flights

/**Created by: Emmanuel Ozibo
//on: 06, 2020-02-06
//at: 22:10*/

/**
 * Well, this class helps to track which textbox the user is currently typing on,
 * can be any of the two states, OriginState, or DestinationState
 */
sealed class EditTextTypeState {
    object OriginState: EditTextTypeState()
    object DestinationState: EditTextTypeState()
}