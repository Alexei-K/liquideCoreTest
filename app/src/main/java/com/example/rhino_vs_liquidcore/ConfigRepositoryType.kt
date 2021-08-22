package com.example.rhino_vs_liquidcore

interface ConfigRepositoryType {
    fun getConfig(name: String): String
}