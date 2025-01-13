package com.example.chapter_nine.data

sealed class PermissionAction {
    data object PermissionGranted : PermissionAction()
    data object PermissionDenied : PermissionAction()
}
